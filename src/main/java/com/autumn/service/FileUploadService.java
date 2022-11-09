package com.autumn.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {
    private final String path = "C:\\Users\\agden\\Desktop\\AGUS\\FACULTAD\\4 año\\Seminario de Integracion Profesional\\Second Round\\2do Cuatrimestre\\Codigo\\Attachments\\";

    public void uploadFile(MultipartFile file, String carpeta) throws IOException {
        file.transferTo(new File(path + carpeta + "\\" + file.getOriginalFilename()));
    }
}
