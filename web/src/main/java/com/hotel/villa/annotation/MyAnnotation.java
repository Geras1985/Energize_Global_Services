package com.hotel.villa.annotation;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Getter;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String name();
    boolean lazyLoad() default false;


}
