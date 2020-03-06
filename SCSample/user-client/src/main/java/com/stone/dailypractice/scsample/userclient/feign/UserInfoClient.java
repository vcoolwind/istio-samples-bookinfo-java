package com.stone.dailypractice.scsample.userclient.feign;

import com.stone.dailypractice.scsample.userclient.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("provider-user")
public interface UserInfoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    UserInfo findByID(@PathVariable("id") Long id);
}


