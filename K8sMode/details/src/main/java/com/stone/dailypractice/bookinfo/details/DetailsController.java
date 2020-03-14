package com.stone.dailypractice.bookinfo.details;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsController {

    @GetMapping(value = "/")
    public String root() {
        return "hello details";
    }

    @GetMapping(value = "/details/{productId}")
    public ResponseEntity<Detail> getDetailByIsbn(@PathVariable("productId") String productId) {
        if (DetailsRepo.contains(productId)) {
            return new ResponseEntity<>(DetailsRepo.getDetail(productId), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
