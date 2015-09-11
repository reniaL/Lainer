package cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在方法上使用该注解，则方法会拥有双缓存
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodCache {
    
    /**
     * 短缓存名称，默认为空串
     */
    String shortCacheName() default "";
    
    /**
     * 长缓存名称，默认为空串
     */
    String longCacheName() default "";
}