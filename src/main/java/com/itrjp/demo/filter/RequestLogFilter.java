package com.itrjp.demo.filter;

import com.itrjp.demo.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        Map<String, String> parameterMap = RequestUtils
                .getParamMap(request);
        filterChain.doFilter(request, response);
        logger.info("url: {}, param: {}, duration: {}", request.getRequestURL(), parameterMap, System.currentTimeMillis() - start);
    }
}
