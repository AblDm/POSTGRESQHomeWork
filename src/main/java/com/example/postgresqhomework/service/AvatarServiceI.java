package com.example.postgresqhomework.service;

import com.example.postgresqhomework.model.Avatar;
import com.example.postgresqhomework.model.Student;
import com.example.postgresqhomework.repository.AvatarRepository;
import com.example.postgresqhomework.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceI implements AvatarService{
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;


    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;


    public AvatarServiceI(StudentService studentService,
                          AvatarRepository avatarRepository,
                          StudentRepository studentRepository) {

        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(AvatarServiceI.class);

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        logger.info("Was invoked method \" save new picture\"");
        logger.error("There is no such directory to save");

            Student student = studentRepository.getById(studentId);
            Path filePath = Path.of(avatarsDir, studentId +"."+ getExtension(file.getOriginalFilename()));
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);

            try (InputStream is = file.getInputStream();
                 OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                 BufferedInputStream bis = new BufferedInputStream(is, 1024);
                 BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ) {
                bis.transferTo(bos);
            }

            Avatar avatar = findAvatar(studentId);
            avatar.setStudent(student);
            avatar.setFilePath(filePath.toString());
            avatar.setFileSize(file.getSize());
            avatar.setMediaType(file.getContentType());
            avatar.setData(generateDataForDB(filePath));
            avatarRepository.save(avatar);
        }


    @Override
    public Avatar findAvatar(long studentId) {
        logger.info("Was invoked method for search the avatar by id");
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }


    private byte[] generateDataForDB(Path filePath) throws IOException{

        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ){
            BufferedImage image = ImageIO.read(bis) ;

            int height = image.getHeight() / (image.getWidth()/100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image,0,0,100,height,null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();

        }

    }


    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".")+1);
    }

    public Page<Avatar> getAllAvatars(Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return avatarRepository.findAll(pageRequest);
    }
}