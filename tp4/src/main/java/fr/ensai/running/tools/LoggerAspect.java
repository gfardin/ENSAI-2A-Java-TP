package fr.ensai.running.tools;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method calls.
 */
@Aspect
@Component
public class LoggerAspect {

    private static final String POINTCUT_STR = "execution(* fr.ensai..*(..)) || this(org.springframework.data.repository.CrudRepository+) && execution(* *(..))";
    private static final int MAX_ARG_LENGTH = 100;

    private static int nbIdent = 0;

    /**
     * Get method name and arguments
     */
    private String getMethodName(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        String argsStr = Arrays.stream(args)
                .map(String::valueOf)
                .collect(Collectors.collectingAndThen(
                        Collectors.joining(", "),
                        joined -> joined.length() > MAX_ARG_LENGTH ? joined.substring(0, MAX_ARG_LENGTH) + "..."
                                : joined));

        return methodName + "(" + argsStr + ")";
    }

    /**
     * Logs the method call before execution.
     */
    @Before(POINTCUT_STR)
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Logger log = LoggerFactory.getLogger(className);

        log.debug("{} - BEGIN", " ".repeat(nbIdent * 4) + this.getMethodName(joinPoint));
        nbIdent++;
    }

    /**
     * Logs the method call after returning from execution.
     */
    @AfterReturning(pointcut = POINTCUT_STR, returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Logger log = LoggerFactory.getLogger(className);

        nbIdent--;

        if (result != null) {
            String resultStr = String.valueOf(result);
            if (resultStr.length() > MAX_ARG_LENGTH) {
                resultStr = resultStr.substring(0, MAX_ARG_LENGTH) + "...";
            }

            log.debug(" ".repeat(nbIdent * 4) + "  └─> Output: {}", resultStr);
        }

        log.debug("{} - END", " ".repeat(nbIdent * 4) + this.getMethodName(joinPoint));
    }
}
