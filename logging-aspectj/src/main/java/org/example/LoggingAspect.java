package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Aspect
@ConfigurationProperties("logging")
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("methodsToBeLogged()")
    public Object loggingServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        try {
            LOGGER.info("before method %s execution".formatted(methodName));
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            LOGGER.info("after method %s execution".formatted(methodName));
            LOGGER.info("Execution time of method %s %s ms".formatted(methodName, end-start));
        }
    }

    @Pointcut("execution(* org.example..*Service.*(..))")
    public void methodsToBeLogged() {}
}
