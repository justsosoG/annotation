package org.njgzr.operation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.njgzr.interfaces.OperationHandle;
import org.njgzr.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
*@author Mr Gu
*@description （一句话描述作用）
*@version 2019年12月3日下午3:12:42
*/
@Component
@Scope
@Aspect
public class OperationAop {
	
	@Pointcut("@annotation(org.njgzr.operation.OperationAspect)")
    public void operation() {
    	
    }
	
	@Autowired
	private OperationHandle operationHandleImpl;
	
	@Around("operation()")
	public Object around(ProceedingJoinPoint joinPoint) {
		OperationAspect operation = MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getAnnotation(OperationAspect.class);
		Object obj = null;
		try {
			String args = JSON.toJSONString(joinPoint.getArgs());
			obj = joinPoint.proceed();
			if(operation.enable()) {
				operationHandleImpl.requestOperation(args);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	
	Result toResult(Object val) throws Exception{
		if(val instanceof Result)
			return (Result)val;
		if(val instanceof Exception)
			return Result.fail(500,(Exception)val);
		throw new Exception("expect 'com.jincloud.base.domain.Result' Reslt but:"+val.getClass());
	}
	
}
