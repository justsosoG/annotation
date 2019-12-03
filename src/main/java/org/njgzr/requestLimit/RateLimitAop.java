package org.njgzr.requestLimit;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.njgzr.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;


/**
*@author Mr Gu
*@description 接口限流
*@version 2019年12月3日 下午3:08:43
*/
@Component
@Scope
@Aspect
public class RateLimitAop {
	
	@Autowired
    private HttpServletResponse response;
	
	@Pointcut("@annotation(org.njgzr.requestLimit.RateLimitAspect)")
    public void serviceLimit() {
    	
    }
	
    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) {
    	RateLimitAspect limit = MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getAnnotation(RateLimitAspect.class);
    	RateLimiter rateLimiter = RateLimiter.create(limit.count());
    	Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            }else{
                String result = Result.fail("请求频繁，请重试！").toJson();
                output(response, result);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
	
}
