package com.github.msa.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "msa-producer", fallback = MsaProducerHystrix.class)
public interface MsaProducerService {
    @RequestMapping("/hello")
    public String hello();
}
