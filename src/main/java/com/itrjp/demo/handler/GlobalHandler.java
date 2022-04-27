package com.itrjp.demo.handler;

import com.itrjp.demo.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author renjp
 * @date 2022/4/27 23:12
 */
@Slf4j
@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler({BizException.class})
    public ResponseEntity<String> handlerBizException(BizException exception) {
        log.error("BizException, message: {}", exception.getMessage());
        return ResponseEntity.status(400).body(exception.getMsg());
    }
}
