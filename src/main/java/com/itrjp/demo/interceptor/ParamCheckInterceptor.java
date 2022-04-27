package com.itrjp.demo.interceptor;

import com.itrjp.demo.exception.BizException;
import com.itrjp.demo.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 通用参数检查
 *
 * @author renjp
 * @date 2022/4/27 22:49
 */
@Slf4j
@Component
public class ParamCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ParamCheckInterceptor#preHandle, request: {}, response: {}", request, response);
        Map<String, String> query = RequestUtils.getParamMap(request);
        String appId = query.get("appId");
        if (appId == null || appId.length() == 0) {
            throw new BizException(10001, "appId 不能为空!");
        }
        return true;
    }

}
