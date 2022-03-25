package org.butu.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: BTForum
 * @description: 系统日志
 * @packagename: org.butu.common.annotation
 * @author: BUTUbird
 * @date: 2022-03-25 22:57
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLog {
    String businessName();
}
