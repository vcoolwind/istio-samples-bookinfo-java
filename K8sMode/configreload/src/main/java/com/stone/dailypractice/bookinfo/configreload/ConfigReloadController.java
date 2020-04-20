package com.stone.dailypractice.bookinfo.configreload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigReloadController {

    @Autowired
    DynamicConfig dynamicConfig;

    @GetMapping(value = "/")
    public String root() {
        return "hello ConfigReload";
    }


    @GetMapping(value = "/info")
    public DynamicConfig createRating() {
        return dynamicConfig;
    }
}