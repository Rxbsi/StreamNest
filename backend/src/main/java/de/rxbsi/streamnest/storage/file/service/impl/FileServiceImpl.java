package de.rxbsi.streamnest.storage.file.service.impl;

import de.rxbsi.streamnest.storage.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final S3Client s3Client;

    @Override
    public ResponseEntity<String> uploadFile(MultipartFile file, String bucketName) {
        String fileName = file.getOriginalFilename();

        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            s3Client.putObject(putObjectRequest, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, file.getSize()));

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
