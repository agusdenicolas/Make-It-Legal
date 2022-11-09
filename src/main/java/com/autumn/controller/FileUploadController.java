package com.autumn.controller;

import com.autumn.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService service;

    @PostMapping("/files") //Todo: Cambiar ruta de POST
    public void uploadFile(@RequestParam("file")MultipartFile file, String carpeta) throws IOException {
        service.uploadFile(file, carpeta);
    }
}
