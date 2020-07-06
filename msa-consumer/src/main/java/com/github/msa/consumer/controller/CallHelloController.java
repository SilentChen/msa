package com.github.msa.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

public class CallHelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/call")
    public String call() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("msa-producer");
        System.out.println("Service Address : " + serviceInstance.getUri());
        System.out.println("Service Name    : " + serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString()+ "/hello", String.class);
        System.out.println(callServiceResult);

        return callServiceResult;
    }
}
