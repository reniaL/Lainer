package anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * annotation means that something is still in construction
 * @author reniaL
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Unfinished {
    
    public enum Priority {
        LOW, MEDIUM, HIGH
    }
    
    String value();
    
    String[] owners() default "";
    
    Priority priority() default Priority.MEDIUM;
}
