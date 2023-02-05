package com.example.postgresqhomework;

import com.example.postgresqhomework.controller.StudentController;
import com.example.postgresqhomework.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentWithJupiterTests {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    Student student= new Student();

    final String name = "testBook";
    final int age = 12;
    final long id = 1;
    @Test
    void contextLoads()  throws Exception  {
        Assertions
                .assertThat(studentController).isNotNull();
    }
    @Test
    public void testDefaultMessage()  throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+ port +"/",String.class))
                .isEqualTo("web app is working");
    }
    @Test
    public void testDefaultGetBooks()  throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:"+ port +"/student", String.class))
                .isNotNull();
    }

    @Test
    public void testPostBooks() throws Exception {


        student.setName(name);
        student.setAge(age);
        student.setId(id);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();

    }


}
