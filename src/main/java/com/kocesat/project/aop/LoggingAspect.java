package com.kocesat.project.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  @Pointcut("@annotation(Log)")
  public void pointcutWithAnnotation(){}


  @Pointcut("execution(public void com.kocesat.project.aop.LoggableService.getValue())")
  public void logPointcutWithExecution(){}

  @Pointcut("within(com.kocesat.project.aop.LoggableService)")
  public void logPointcutWithin() {}

  @Before("pointcutWithAnnotation()")
  public void beforeAdvice(JoinPoint joinPoint){
    // what needs to be done
    System.out.println(joinPoint.getSignature().getName());;
  }

  @AfterReturning("pointcutWithAnnotation()")
  public void afterReturningAdvice(JoinPoint joinPoint){
    // do what needs to be done
  }

  @AfterThrowing("pointcutWithAnnotation()")
  public void afterThrowingAdvice(JoinPoint joinPoint){
    // do what needs to be done
  }

  @After("pointcutWithAnnotation()")
  public void afterAdvice(JoinPoint joinPoint){
    // do what needs to be done
  }

  @Around("pointcutWithAnnotation()")
  public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
    // do before the method runs
    joinPoint.proceed();
    // do after the method runs
  }

}
