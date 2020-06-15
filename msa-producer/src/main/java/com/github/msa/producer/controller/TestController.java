package com.github.msa.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(value = "/test")
    public String test() {
            return "This is MSA-Producer's test controller@test function speaking, what can i do for you? ";
    }
}
