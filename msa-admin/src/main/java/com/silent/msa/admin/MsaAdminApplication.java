package com.silent.msa.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.silent.msa"})
public class MsaAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaAdminApplication.class, args);
    }
}
