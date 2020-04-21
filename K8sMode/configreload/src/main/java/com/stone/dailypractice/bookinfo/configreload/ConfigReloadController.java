package com.stone.dailypractice.bookinfo.configreload;

import com.stone.dailypractice.bookinfo.configreload.model.RemoteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigReloadController {

    @Autowired
    DynamicConfig dynamicConfig;

    @Autowired
    MongoDBConfig mongoDBConfig;


    @GetMapping(value = "/")
    public String root() {
        log.info("-----------root--------------");
        return "hello ConfigReload";
    }


    @GetMapping(value = "/info")
    public RemoteConfig getDynamicConfig() {
        log.info("---------getDynamicConfig-----------[{}]", dynamicConfig);
        RemoteConfig remoteConfig = new RemoteConfig();
        BeanUtils.copyProperties(dynamicConfig, remoteConfig);
        remoteConfig.setMongoUserName(mongoDBConfig.getUsername());
        remoteConfig.setMongoPassword(mongoDBConfig.getPassword());
        return remoteConfig;
    }
}