package com.itrjp.demo.valid;

import com.itrjp.demo.exception.BizException;
import com.itrjp.demo.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;

/**
 * 自定义注解
 *
 * @author renjp
 * @date 2022/4/27 23:00
 */
@Slf4j
public class FilledValidator implements ConstraintValidator<Filled, Object> {
    HttpServletRequest request;
    private Filled annotation;

    @Override
    public void initialize(Filled annotation) {
        ConstraintValidator.super.initialize(annotation);
        this.annotation = annotation;
        this.request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        log.info("FilledValidator#initialize, request: {}", this.request);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        log.info("FilledValidator#isValid, request: {}", this.request);
        // 字段名称
        String fieldName = annotation.value();
        // 检查请求参数中是否存在改字段
        if (RequestUtils.checkParamIsExist(request, fieldName) && isNullOrEmpty(value)) {
            throw new BizException(annotation.errorCode(), annotation.message());
        }
        return true;
    }

    private boolean isNullOrEmpty(Object data) {
        if (data instanceof String) {
            return ((String) data).length() == 0;
        } else if (data instanceof List) {
            return ((List<?>) data).isEmpty();
        } else if (data instanceof Map) {
            return ((Map<?, ?>) data).isEmpty();
        }
        return true;
    }
}
