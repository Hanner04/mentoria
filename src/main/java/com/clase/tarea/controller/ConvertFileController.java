package com.clase.tarea.controller;

import com.clase.tarea.model.convertFile.FilesRequest;
import com.clase.tarea.service.ConvertFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convertFile")
public class ConvertFileController {
    @Autowired
    private  ConvertFileService convertFileService;


    @PostMapping("/convert")
    public ResponseEntity<String> convert(@RequestBody FilesRequest filesRequest) {
        convertFileService.convert(filesRequest);
        String path = filesRequest.getPath();
        String fileName = filesRequest.getFileName();

        return ResponseEntity.ok("pathDestination: " + path + ", filename: " + fileName);


    }
}
