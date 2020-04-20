package com.stone.dailypractice.bookinfo.configreload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * show runtime info
 */
@Slf4j
@Component
public class ReloadConfigStatus {
    @Autowired
    private DynamicConfig dynamicConfig;

    @Autowired
    private DummyConfig dummyConfig;

    @Autowired
    private MongoDBConfig mongoDBConfig;

    @Scheduled(fixedDelay = 15000)
    public void showConfig() {
        log.info("The value of myConfig is: " + this.dynamicConfig);
        log.info("The value of secretConfig is: " + this.mongoDBConfig);
        log.info("The other message is: " + this.dummyConfig.getMessage());
    }
}
