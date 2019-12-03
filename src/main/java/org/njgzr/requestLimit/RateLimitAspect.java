package org.njgzr.requestLimit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*@author Mr Gu
*@description 接口限流
*@version 2019年12月3日 下午3:08:43
*/
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitAspect {
	
	/**
	 * 
	 * @Title: count
	 * @Description: 最大并发数
	 * @author: Mr Gu
	 * @date 2019年12月3日 下午3:08:43
	 * @return double
	 * @version
	 */
	double count() default Double.MAX_VALUE;
	
}
