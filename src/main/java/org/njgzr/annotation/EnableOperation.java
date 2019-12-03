package org.njgzr.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.njgzr.config.OperationConfig;
import org.springframework.context.annotation.Import;

/**
*@author Mr Gu
*@description （一句话描述作用）
*@version 2019年12月3日下午2:05:33
*/
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Import({OperationConfig.class})
public @interface EnableOperation {
	
	
	
}
