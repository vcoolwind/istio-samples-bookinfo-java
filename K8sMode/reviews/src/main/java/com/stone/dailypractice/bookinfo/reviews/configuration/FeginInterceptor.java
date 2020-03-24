package com.stone.dailypractice.bookinfo.reviews.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class FeginInterceptor implements RequestInterceptor {

    // HTTP headers to propagate for distributed tracing are documented at
    // https://istio.io/docs/tasks/telemetry/distributed-tracing/overview/#trace-context-propagation
    private final static String[] HEADERS_TO_PROAGATE = {"x-request-id", "x-b3-traceid", "x-b3-spanid", "x-b3-sampled", "x-b3-flags",
            "x-ot-span-context", "x-datadog-trace-id", "x-datadog-parent-id", "x-datadog-sampled", "end-user", "user-agent"};


    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, String> headers = getHeaders(getHttpServletRequest());
        for (String headerName : headers.keySet()) {
            requestTemplate.header(headerName, getHeaders(getHttpServletRequest()).get(headerName));
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                return ((ServletRequestAttributes) attributes).getRequest();
            } else {
                log.warn("RequestContextHolder.getRequestAttributes() is null");
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        if (request != null) {
            for (String header : HEADERS_TO_PROAGATE) {
                String value = request.getHeader(header);
                if (value != null) {
                    map.put(header, value);
                }
            }
        } else {
            log.warn("request is null");
        }
        return map;
    }

}