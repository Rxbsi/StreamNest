package de.rxbsi.streamnest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamNestApplication {

    public static final String URL = "http://localhost";

    public static void main(String[] args) {
        SpringApplication.run(StreamNestApplication.class, args);
    }

}
