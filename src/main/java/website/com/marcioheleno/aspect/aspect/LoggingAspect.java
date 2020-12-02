package website.com.marcioheleno.aspect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Run before the method execution.
     * @param joinPoint
     */
    @Before("execution(* website.com.marcioheleno.aspect.services.EmployeeService.addEmployee(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("logBefore running .....");
        log.info("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * Run after the method returned a result.
     * @param joinPoint
     */
    @After("execution(* website.com.marcioheleno.aspect.services.EmployeeService.addEmployee(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("logAfter running .....");
        log.info("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * Run after the method returned a result, intercept the returned result as well.
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "execution(* website.com.marcioheleno.aspect.services.EmployeeService.deleteEmployee(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.debug("logAfterReturning running .....");
        log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * Run around the method execution.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* website.com.marcioheleno.aspect.services.EmployeeService.getEmployeeById(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("logAround running .....");
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }

    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param error        exception
     */

    @AfterThrowing(pointcut = "execution(* website.com.marcioheleno.aspect.services.EmployeeService.updateEmployee(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.debug("logAfterThrowing running .....");
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), error.getCause() != null ? error.getCause() : "NULL");
    }
}
