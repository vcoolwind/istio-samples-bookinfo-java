package com.stone.dailypractice.bookinfo.reviews.configreload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfigReloadClientFallback implements ConfigReloadClient {


    @Override
    public RemoteConfig getRemoteConfig() {
        return new RemoteConfig();
    }
}
