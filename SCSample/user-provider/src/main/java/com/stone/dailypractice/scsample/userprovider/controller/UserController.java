package com.stone.dailypractice.scsample.userprovider.controller;

import com.stone.dailypractice.scsample.userprovider.entity.UserInfo;
import com.stone.dailypractice.scsample.userprovider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public UserInfo findById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            byte[] bytes = file.getBytes();
            File f = new File(System.currentTimeMillis() + "_" + file.getOriginalFilename());
            FileCopyUtils.copy(bytes, f);
            return f.getAbsolutePath();
        } else {
            return "[error]upload file error";
        }
    }
}
