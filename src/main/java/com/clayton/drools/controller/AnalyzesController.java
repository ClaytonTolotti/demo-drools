package com.clayton.drools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clayton.drools.dto.AnalyzesDTO;
import com.clayton.drools.dto.DemoRequest;
import com.clayton.drools.service.interfaces.AnalyzesService;

@RestController
public class AnalyzesController {

    @Autowired
    private AnalyzesService service;

    @PostMapping(value = "/v1/analyzes")
    public ResponseEntity<AnalyzesDTO> analyzeProfile(@RequestBody DemoRequest body){

        AnalyzesDTO response = service.analyzeProfile(body);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
