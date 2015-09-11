package aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * MethodCache对应的aop
 * @author reniaL
 */
@Aspect
@Component("methodCacheAspectJ")
public class MethodCacheAspectJ {
    
    private static final Log log = LogFactory.getLog(MethodCacheAspectJ.class);
    
    @Pointcut("@annotation(cache.MethodCache)")
    public void methodCachePointcut() {
    }
    
    @Around("methodCachePointcut()")
    public Object getCache(ProceedingJoinPoint jp) throws Throwable {
        log.info("this: " + jp.getThis());
        log.info("target: " + jp.getTarget());
        Object result = jp.proceed(jp.getArgs());
        log.info("after proceeding.");
        return result;
    }
    
}
