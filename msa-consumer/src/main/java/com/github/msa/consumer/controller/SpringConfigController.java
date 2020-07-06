package com.github.msa.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
/**
 * [ Spring Cloud Config Service Test ]
 * get the sprcifice config file's item from config service.
 */
public class SpringConfigController {
    @Value("${consumer.hello}")
    private String hello;

    @RequestMapping("/hello")
    public String from() {
        return this.hello;
    }
}
