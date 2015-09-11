package demo.jse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import util.WgetUtil;

/**
 * @author reniaL
 */
public class Dom4jDemo {
    
    public static void main(String[] args) throws IOException, DocumentException, SAXException {
        // String text = "<?xml version='1.0' encoding='GBK'?><return><msg_code>0</msg_code><msg>操作成功</msg></return>";
        // testParse(text);
        testReadFile();
        // testWrite();
    }
    
    private static final String PATH = "/home/test.xml";
    
    /**
     * 读 url
     */
    public static void testReadUrl() throws IOException, DocumentException, SAXException {
        String urlStr = "";
        URL url;
        HttpURLConnection conn;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // System.out.println("response code:" + conn.getResponseCode());
        System.out.println(conn.getURL().toString());
        if (urlStr.equals(conn.getURL().toString())) {
            String pageStr = WgetUtil.wgetTxt(conn);
            Document doc = DocumentHelper.parseText(pageStr);
            Element root = doc.getRootElement();
            System.out.println("size: " + root.elements().size());
        } else {
            System.out.println("redirect");
        }
    }
    
    /**
     * 读文件
     */
    @SuppressWarnings("unchecked")
    public static void testReadFile() throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new FileInputStream(PATH));
        Element root = document.getRootElement();
        for (Iterator<Element> iter = root.elementIterator(); iter.hasNext();) {
            Element element = iter.next();
            System.out.println("user: " + element.getText());
            System.out.println("name: " + element.attributeValue("name"));
            System.out.println("blog: " + element.attributeValue("blog"));
            System.out.println("u can't see: " + element.attributeValue("notexists"));
        }
    }
    
    /**
     * 写文件
     */
    public static void testWrite() throws IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        Element element1 = root.addElement("user");
        element1.addAttribute("name", "reniaL").addAttribute("blog", "无忧岛").addAttribute("email", "renial@163.com")
                .addText("renial@163.com\\ngot it?");
        
        // 输出到文件
        OutputFormat format = new OutputFormat("    ", true, "GBK");
        XMLWriter writerToFile = new XMLWriter(new FileOutputStream(PATH), format);
        writerToFile.write(document);
        writerToFile.close();
        
        // 输出到 StringWriter
        StringWriter stringWriter = new StringWriter();
        XMLWriter writerToByteArray = new XMLWriter(stringWriter, format);
        writerToByteArray.write(document);
        writerToByteArray.close();
        System.out.println(stringWriter.toString()); // ByteArrayOutputStream 无需 close
    }
    
    public static void testParse(String text) {
        try {
            Document doc = DocumentHelper.parseText(text);
            System.out.println(doc.asXML());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
