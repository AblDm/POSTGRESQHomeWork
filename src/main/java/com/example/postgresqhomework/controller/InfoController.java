package com.example.postgresqhomework.controller;

import com.example.postgresqhomework.service.InfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping(path = "/getPort")   //GET http://localhost:8080/getPort
    public ResponseEntity<Integer> getPortInfo() {
        return ResponseEntity.ok(infoService.portNumber());
    }


}
