package util;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class SystemUtil {
    
    private SystemUtil() {
    }
    
    /**
     * 将文本内容复制到系统粘贴板
     */
    public static void copyToClipboard(String content) {
        StringSelection selection = new StringSelection(content);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }
    
}
