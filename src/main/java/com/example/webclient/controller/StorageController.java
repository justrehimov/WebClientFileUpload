package com.example.webclient.controller;

import com.example.webclient.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;
    @PostMapping("/upload")
    public Object upload(@RequestPart("file")MultipartFile multipartFile){
        return storageService.upload(multipartFile);
    }
}
