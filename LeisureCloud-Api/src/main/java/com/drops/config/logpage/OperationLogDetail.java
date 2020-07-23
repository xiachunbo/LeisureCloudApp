package com.drops.config.logpage;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogDetail {
    String detail() default "";

    int level() default 0;

    OperationType operationType() default OperationType.UNKNOWN;

    OperationUnit operationUnit() default OperationUnit.UNKNOWN;
}