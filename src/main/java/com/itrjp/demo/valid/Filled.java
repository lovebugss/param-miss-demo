package com.itrjp.demo.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Filled 校验.
 * <p>
 * 当请求参数中存在某个字段时, 该值不能为空. 如果请求参数中不存在该字段, 则忽略次校验.
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FilledValidator.class})
@Documented
@Repeatable(Filled.List.class)
public @interface Filled {
    String value();

    String message() default "网络异常，请联系管理员";

    int errorCode() default 500;

    /**
     * 分组
     *
     * @return
     */
    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

    @Target({FIELD, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Filled[] value();
    }
}
