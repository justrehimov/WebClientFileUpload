package com.example.webclient.service;

import com.example.webclient.response.FileResponse;
import com.example.webclient.response.StorageResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final WebClient webClient;
    @SneakyThrows
    public Object upload(MultipartFile multipartFile) {
        StorageResponse<FileResponse> fileResponseStorageResponse = new StorageResponse<>();
        String url = "https://vusalrehimov.000webhostapp.com/upload.php";
        return webClient.post()
                .uri(url)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(fromFile(multipartFile)))
                .retrieve()
                .bodyToMono(fileResponseStorageResponse.getClass())
                .block();
    }


    public MultiValueMap<String, HttpEntity<?>> fromFile(MultipartFile multipartFile) {
        File file = multipartToFile(multipartFile);
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", new FileSystemResource(file));
        return builder.build();
    }

    @SneakyThrows
    public File multipartToFile(MultipartFile multipartFile){
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
}


