package com.itrjp.demo.config;

/**
 * WebConfiguration
 *
 * @author renjp
 * @date 2022/4/27 23:49
 */

import com.itrjp.demo.interceptor.ParamCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final ParamCheckInterceptor paramCheckInterceptor;

    public WebConfiguration(ParamCheckInterceptor paramCheckInterceptor) {
        this.paramCheckInterceptor = paramCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(paramCheckInterceptor)
                .addPathPatterns("/api/**");
    }
}
