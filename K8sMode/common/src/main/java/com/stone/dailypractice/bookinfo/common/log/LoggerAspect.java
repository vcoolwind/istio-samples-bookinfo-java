package com.stone.dailypractice.bookinfo.common.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LoggerAspect {
    private static final String ORIGIN_REQUEST_ID = "x-request-id";
    private static final String ORIGIN_TRACE_ID = "x-b3-traceid";
    private static final String ORIGIN_SPAN_ID = "x-b3-spanid";

    private static final String REQUEST_ID = "requestID";
    private static final String TRACE_ID = "traceID";
    private static final String SPAN_ID = "spanID";


    @Around("execution(public * com.stone..*.*(..))")
    public Object doFilter(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
            if (httpServletRequest != null) {
                assembleMDC(httpServletRequest, ORIGIN_REQUEST_ID);
                assembleMDC(httpServletRequest, ORIGIN_TRACE_ID);
                assembleMDC(httpServletRequest, ORIGIN_SPAN_ID);
            }
        }
        return joinPoint.proceed();
    }

    private void assembleMDC(HttpServletRequest request, String key) {
        String flowID = request.getHeader(key);
        if (StringUtils.isEmpty(flowID)) {
            flowID = "#";
        }
        //log.info("{} --> {}",key,flowID);
        switch (key) {
            case ORIGIN_REQUEST_ID:
                MDC.put(REQUEST_ID, flowID);
                break;
            case ORIGIN_TRACE_ID:
                MDC.put(TRACE_ID, flowID);
                break;
            case ORIGIN_SPAN_ID:
                MDC.put(SPAN_ID, flowID);
                break;
            default:
                log.info("invalid key:" + key);
        }
    }
}
