package com.microservice.readwriteseparat.aop;

import com.microservice.readwriteseparat.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
    /**
     * 只读：
     * 不是Master注解的对象或方法  && select开头的方法  ||  get开头的方法
     */
    @Pointcut("!@annotation(com.microservice.readwriteseparat.annotation.Master) " +
            "&& (execution(* com.microservice.readwriteseparat.service..*.select*(..)) " +
            "|| execution(* com.microservice.readwriteseparat.service..*.get*(..)))")
    public void readPointcut() {

    }

    /**
     * 写：
     * Master注解的对象或方法 || insert开头的方法  ||  add开头的方法 || update开头的方法
     * || edlt开头的方法 || delete开头的方法 || remove开头的方法
     */
    @Pointcut("@annotation(com.microservice.readwriteseparat.annotation.Master) " +
            "|| execution(* com.microservice.readwriteseparat.service..*.insert*(..)) " +
            "|| execution(* com.microservice.readwriteseparat.service..*.add*(..)) " +
            "|| execution(* com.microservice.readwriteseparat.service..*.update*(..)) " +
            "|| execution(* com.microservice.readwriteseparat.service..*.edit*(..)) " +
            "|| execution(* com.microservice.readwriteseparat.service..*.delete*(..)) " +
            "|| execution(* com.microservice.readwriteseparat..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
