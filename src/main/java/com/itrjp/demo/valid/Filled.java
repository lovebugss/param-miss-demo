package com.itrjp.demo.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

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
