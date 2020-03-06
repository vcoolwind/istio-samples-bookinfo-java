package com.stone.dailypractice.bookinfo.review.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class FeginInterceptor implements RequestInterceptor {

    // HTTP headers to propagate for distributed tracing are documented at
    // https://istio.io/docs/tasks/telemetry/distributed-tracing/overview/#trace-context-propagation
    private final static String[] HEADERS_TO_PROAGATE = {"x-request-id", "x-b3-traceid", "x-b3-spanid", "x-b3-sampled", "x-b3-flags",
            "x-ot-span-context", "x-datadog-trace-id", "x-datadog-parent-id", "x-datadog-sampled", "end-user", "user-agent"};


    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String,String> headers = getHeaders(getHttpServletRequest());
        for(String headerName : headers.keySet()){
            requestTemplate.header(headerName, getHeaders(getHttpServletRequest()).get(headerName));
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        for (String header : HEADERS_TO_PROAGATE) {
            String value = request.getHeader(header);
            if (value != null) {
                map.put(header, value);
            }
        }
        return map;
    }

}