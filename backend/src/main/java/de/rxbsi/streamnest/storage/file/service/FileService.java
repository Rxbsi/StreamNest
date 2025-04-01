package de.rxbsi.streamnest.storage.file.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ResponseEntity<String> uploadFile(MultipartFile file, String bucketName);

}
