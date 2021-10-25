package com.example.s3asyncexamples;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class S3Service {

    private AmazonS3 amazonS3;

    public S3Service() {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider())
                .build();
    }

    public List<String> listObjectKeys(String bucketName, String prefix) {
        return amazonS3.listObjectsV2(bucketName, prefix)
                .getObjectSummaries().stream()
                .filter(object -> !object.getKey().endsWith("/"))
                .map(object -> object.getKey())
                .collect(Collectors.toList());
    }

    @Async
    public CompletableFuture<S3Content> fetchAsync(String bucketName, String objectKey) {
        S3Object object = amazonS3.getObject(new GetObjectRequest(bucketName, objectKey));
        return CompletableFuture.completedFuture(new S3Content(object));
    }

}
