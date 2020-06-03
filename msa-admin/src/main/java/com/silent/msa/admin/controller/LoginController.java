package com.silent.msa.admin.controller;

import com.silent.msa.admin.service.LogService;
import com.silent.msa.core.http.HttpResult;
import com.silent.msa.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class LoginController {
    @Autowired
    private LogService logService;

    @PreAuthorize("hasAutority('sys:log:view')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(logService.findPage(pageRequest));
    }
}
