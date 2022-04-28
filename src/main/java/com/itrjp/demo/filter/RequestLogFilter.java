package com.itrjp.demo.filter;

import com.itrjp.demo.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 记录日志
 *
 * @author renjp
 * @date 2022/4/27 22:43
 */
@Component
@WebFilter(value = "/**")
public class RequestLogFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //
//        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

        long start = System.currentTimeMillis();
        Map<String, String> parameterMap = RequestUtils
                .getParamMap(request);
        String queryParam = request.getQueryString();
        String method = request.getMethod();

        filterChain.doFilter(request, cachingResponseWrapper);
        cachingResponseWrapper.setHeader("x-request-id", MDC.get("traceId"));

        int status = cachingResponseWrapper.getStatus();

        // 响应体
        String responseBody = new String(cachingResponseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

        logger.info("url: {}, method: {}, queryStr: {}, param: {}, response statusCode: {},  result: {}, duration: {}", request.getRequestURL(), method, queryParam, parameterMap, status, responseBody, System.currentTimeMillis() - start);

        cachingResponseWrapper.copyBodyToResponse();

    }
}
