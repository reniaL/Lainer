package anno;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

/**
 * @author reniaL
 */
@Unfinished(value = "class scope", priority = Unfinished.Priority.MEDIUM)
public class UnfinishedDemo {
    
    public static void main(String[] args) {
        Method[] methods = UnfinishedDemo.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method: " + method.getName());
            if (method.isAnnotationPresent(Unfinished.class)) {
                Unfinished uf = method.getAnnotation(Unfinished.class);
                // method.setAccessible(true);
                System.out.println(String.format("owners: %s\npriority: %s\nvalue: %s",
                        StringUtils.join(uf.owners(), ","), uf.priority(), uf.value()));
            }
            System.out.println();
        }
    }
    
    @Unfinished("Constructor scope")
    public UnfinishedDemo() {
    }
    
    @Unfinished(owners = { "Jason", "Vivian" }, value = "Method scope")
    public void foo() {
    }
    
}
