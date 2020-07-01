package pl.dreem.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import pl.dreem.util.HasLogger;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect implements HasLogger {

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void beanAnnotatedWithService() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Before("publicMethod() && beanAnnotatedWithService()")
    public void logInput(JoinPoint joinPoint) {
        getLogger().info("Service name: " + joinPoint.getSignature().getDeclaringType().getSimpleName());
        getLogger().info("Function name: " + joinPoint.getSignature().getName());
        getLogger().info("Function args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "publicMethod() && beanAnnotatedWithService()", returning = "result")
    public void logResult(JoinPoint joinPoint, Object result) {
        getLogger().info("Service name: " + joinPoint.getSignature().getDeclaringType().getSimpleName());
        getLogger().info("Function name: " + joinPoint.getSignature().getName());
        if (result == null) {
            getLogger().info("Function result: empty - probably empty optional.");
        } else {
            getLogger().info("Function result: " + result.toString());
        }
    }

    @AfterThrowing(pointcut = "publicMethod() && beanAnnotatedWithService()", throwing = "ex")
    public void logError(JoinPoint joinPoint, Exception ex) {
        getLogger().info("Service name: " + joinPoint.getSignature().getDeclaringType().getSimpleName());
        getLogger().info("Function name: " + joinPoint.getSignature().getName());
        getLogger().error("Function returned exception: " + ex.getMessage());
    }

}
