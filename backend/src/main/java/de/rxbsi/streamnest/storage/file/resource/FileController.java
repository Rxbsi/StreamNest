package de.rxbsi.streamnest.storage.file.resource;

import de.rxbsi.streamnest.storage.file.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        return fileService.uploadFile(file, bucketName);
    }

}

