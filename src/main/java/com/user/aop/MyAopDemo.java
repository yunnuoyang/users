package com.user.aop;

import com.user.controller.UserController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

//声明这是一个组件
@Component
//声明这是一个切面Bean
@Aspect
public class MyAopDemo {
//    Logger logger = LoggerFactory.getLogger(UserController.class);

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

//    @AfterThrowing(throwing = "ex",pointcut = "execution(* com.user.controller.UserController.doupdate(..))")
//    public void afterThrowing(Exception ex){
//        System.out.println(ex.getMessage()+"////////////////////");
//    }


    @Around(value = "execution(* com.user.controller.UserController.doupdate(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            System.out.println(o.toString());
            if (o instanceof BindingResult) {
                BindingResult result = (BindingResult) o;
                if (result.hasErrors()) {
                    System.out.println("aop......");
                    System.out.println(result.getFieldErrors().get(0).getDefaultMessage());
//                    logger.error("erro","redirect:userlist");
                    return "redirect:userlist";
                }
            }
        }
        return joinPoint.proceed();

    }
//    @Pointcut(value = "execution(* com.user.controller.UserController.doupdate())")
//    public void message() {
//
//    }

//    @Around(value = "message()")
//    public void around(ProceedingJoinPoint joinPoint) {
//        System.out.println("错误验证....");
//        Object[] args = joinPoint.getArgs();
//        for (Object o : args) {
//            if (o instanceof BindingResult) {
//                BindingResult result = (BindingResult) o;
//                if (result.hasErrors()) {
//                    System.out.println("87789879898798");
//                    System.out.println( result.getFieldErrors().get(0).getDefaultMessage());
//                }
//            }
//        }
//
//    }
}
