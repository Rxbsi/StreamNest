package de.rxbsi.streamnest.storage.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

import java.net.URI;

@Configuration
public class StorageConfig {

    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secret-key}")
    private String accessSecret;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public S3Client generateS3Client() {
        AwsCredentials credentials = AwsBasicCredentials.create(accessKey, accessSecret);

        S3Client s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(() -> credentials)
                .endpointOverride(URI.create("http://minio:9000"))
                .forcePathStyle(true)
                .build();

        String bucketName = "streamnest-bucket";
        if (!s3Client.listBuckets().buckets().stream().anyMatch(b -> b.name().equals(bucketName))) {
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
            System.out.println("MinIO-Bucket " + bucketName + " wurde erstellt!");
        }

        return s3Client;
    }


}
