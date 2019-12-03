package org.njgzr.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.njgzr.config.OperationConfig;
import org.springframework.context.annotation.Import;

/**
*@author Mr Gu
*@description ��һ�仰�������ã�
*@version 2019��12��3������2:05:33
*/
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Import({OperationConfig.class})
public @interface EnableOperation {
	
	
	
}
