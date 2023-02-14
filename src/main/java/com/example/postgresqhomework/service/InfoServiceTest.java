package com.example.postgresqhomework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!production")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InfoServiceTest implements InfoService{

    @Value("${server.port}")
    private Integer serverPort;
    @Override
    public Integer portNumber() {
        return serverPort;
    }
}
