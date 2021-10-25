package com.example.s3asyncexamples;

import com.amazonaws.services.s3.model.S3Object;

import java.io.IOException;

public class S3Content {

    private S3Object s3Object;

    public S3Content(S3Object object) {
        this.s3Object = object;
    }

    public byte[] data() throws IOException {
        return s3Object.getObjectContent().readAllBytes();
    }

    public String filename() {
        return s3Object.getKey().substring(s3Object.getKey().lastIndexOf("/") + 1);
    }

}
