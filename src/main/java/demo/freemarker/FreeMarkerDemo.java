package demo.freemarker;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import freemarker.template.Configuration;

/**
 * @author huanran.chen
 */
public class FreeMarkerDemo {
    
    private static final String TEMPLATE_DIR = "";
    
    public FreeMarkerDemo() {
        Configuration conf = new Configuration();
        try {
            conf.setDirectoryForTemplateLoading(new File(Thread.currentThread().getContextClassLoader()
                    .getResource(TEMPLATE_DIR).toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public void processTemplate(String templatePath, Map<Object, Object> data) {
        
    }
    
}
