package com.baosight.security.distributed.order.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping(value = "/r1")
    @PostAuthorize("hasAnyAuthority('p1')")
    public String r1() {
        return "访问资源1";
    }
}
