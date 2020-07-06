package com.github.msa.consumer.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * [ Spring Cloud Hystrix Service Test ]
 * hystrix
 */
@Component
public class MsaProducerHystrix implements MsaProducerService {
    @RequestMapping("/hello")
    public String hello() {
        return "Sorry, Fail to call hello service";
    }
}
