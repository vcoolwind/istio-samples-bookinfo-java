package com.stone.dailypractice.bookinfo.reviews.configreload;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfigReloadClientFallbackFactory implements FallbackFactory<ConfigReloadClient> {

    @Override
    public ConfigReloadClient create(Throwable throwable) {
        log.error("ConfigReloadClient invoke method error", throwable);
        return new ConfigReloadClientFallback() ;
    }
}