package com.user.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

//声明这是一个组件
@Component
//声明这是一个切面Bean
@Aspect
public class MyAopDemo {
    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     */
    @Before("execution(* com.user.service.serviceimpl.UserServiceImpl.getUsersList(..))")
    public void silenceCellPhones() {
        System.out.println("观众的手机静音");
    }

    @Before("execution(* com.user.service.serviceimpl.UserServiceImpl.getUsersList(..))")
    public void takeSeats() {
        System.out.println("观众落座");
    }

    @After("execution(* com.user.service.serviceimpl.UserServiceImpl.modifyUserByID(..))")
    public void after() {
        System.out.println("观众落座");
    }

    @Pointcut(value = "execution(* com.user.service.serviceimpl.UserServiceImpl.modifyUserByID(..))")
    public void message() {

    }

    @Around(value = "message()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("错误验证....");
        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            if (o instanceof BindingResult) {
                BindingResult result = (BindingResult) o;
                if (result.hasErrors()) {
                    System.out.println("87789879898798");
                    System.out.println( result.getFieldErrors().get(0).getDefaultMessage());
                }
            }
        }

    }
}
