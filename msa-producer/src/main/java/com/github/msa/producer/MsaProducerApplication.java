package com.github.msa.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.swing.*;

@EnableDiscoveryClient
@SpringBootApplication
public class MsaProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaProducerApplication.class, args);
    }
}
