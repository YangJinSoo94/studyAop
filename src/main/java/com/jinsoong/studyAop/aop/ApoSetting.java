package com.jinsoong.studyAop.aop;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class ApoSetting {

    @Pointcut("execution(* com.jinsoong.studyAop..*.*(..))")
    private void cut(){
        System.out.println("hey i'm cut");
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("before - 메도스 실행됨! "+method.getName());

        Object[] args = joinPoint.getArgs();

        for(Object obj : args){
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

    @AfterReturning(value="cut()", returning = "obj")
    public void afterReturn(JoinPoint joinpoint, Object obj){
        System.out.println("bye, i'm return obj");
        System.out.println("object : "+obj);
    }
}
