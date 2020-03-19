package com.stone.dailypractice.bookinfo.configreload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class ConfigReloadController {

    @Autowired
    MyConfig myConfig;

    @GetMapping(value = "/")
    public String root() {
        return "hello ConfigReload";
    }


    @GetMapping(value = "/info")
    public MyConfig createRating() {
        return myConfig;
    }
}