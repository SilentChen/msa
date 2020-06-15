package com.github.msa.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsaBackupApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaBackupApplication.class, args);
    }
}
