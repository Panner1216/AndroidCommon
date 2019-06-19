package com.panner.common_base.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author panzhijie
 * @version 2018-07-21 19:55
 */
@Target(ElementType.TYPE )
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemExceptionNoVessel {
}
