package com.eisgroup.layered.annotation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
    ATTRIBUTES value() default ATTRIBUTES.NULL_SKIP;

    public static enum ATTRIBUTES {

        NULL_SKIP

    }
}
