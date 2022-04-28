package com.itrjp.demo.handler;

import com.itrjp.demo.exception.BizException;
import com.itrjp.demo.result.Result;
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
    public ResponseEntity<Result<Void>> handlerBizException(BizException exception) {
        log.error("BizException, message: {}", exception.getMessage());
        return ResponseEntity
                .status(400)
                .body(Result.error(exception.getCode(), exception.getMsg()));
    }
}
