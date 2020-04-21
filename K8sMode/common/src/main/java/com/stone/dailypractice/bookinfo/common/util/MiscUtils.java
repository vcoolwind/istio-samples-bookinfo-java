package com.stone.dailypractice.bookinfo.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
public class MiscUtils {
    public static void showHeaders(HttpServletRequest request) {
        if (request != null) {
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String key = headerNames.nextElement();
                    log.info("header: {}={}", key, request.getHeader(key));
                }
                return;
            }
        }
        log.warn("request is null or headerNames is null");
    }
}
