package com.stone.dailypractice.bookinfo.configreload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReloadConfigBean {
    @Autowired
    private MyConfig myConfig;

    @Autowired
    private DummyConfig dummyConfig;

    @Scheduled(fixedDelay = 5000)
    public void showConfig() {
        log.info("The value of myConfig is: " + this.myConfig);
        log.info("The other message is: " + this.dummyConfig.getMessage());
    }
}
