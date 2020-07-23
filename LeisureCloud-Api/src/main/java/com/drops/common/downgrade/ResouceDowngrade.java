package com.drops.common.downgrade;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResouceDowngrade {

    /**
     * 最大阈值
     * @return
     */
    int maxThreshold() default 1;

    /**
     * 限流回调实现类
     * @return
     */
    Class fallbackClass();

    /**
     * 限流回调方法名称，不填默认为被限流的方法名称
     * @return
     */
    String fallbackMethod() default "";

    /**
     * 方法资源ID，一个方法代表一个资源ID，这个id必须是唯一不可重复的值
     * @return
     */
    String resouceId();

}