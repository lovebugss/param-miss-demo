package com.itrjp.demo.filter;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * 增加traceId
 *
 * @author renjp
 * @date 2022/4/27 23:59
 */
@Configuration
@WebFilter(value = "/**")
@Order(Integer.MIN_VALUE)
public class TraceFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        MDC.put(TRACE_ID, getTraceId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
