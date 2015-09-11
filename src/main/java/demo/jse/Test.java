package demo.jse;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.xml.sax.InputSource;

import util.DateUtil;
import util.MyUtil;

import com.rits.cloning.Cloner;

import demo.spring.MyFacade;
import domain.House;
import domain.User;

@SuppressWarnings("restriction")
public class Test {
    
    public static void main(String args[]) {
        dateParseTest();
    }
    
    public static int[] bubbleSort(int[] nums) {
        int[] result = nums.clone();
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j] < result[j + 1]) {
                    int tmp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = tmp;
                }
            }
        }
        return result;
    }
    
    public static void dateFormatTest() {
        int size = 1000000;
        String str = "2014-06-30 12:34:56";
        Date date = null;
        
        long time1 = System.currentTimeMillis();
        // DateFormat df = DateUtil.getFormatDateTimeNormal();
        // try {
        // for (int i = 0; i < size; i++) {
        // date = df.parse(str);
        // }
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        long time2 = System.currentTimeMillis();
        
        for (int i = 0; i < size; i++) {
            date = DateUtil.parseDateTimeNormal(str);
        }
        long time3 = System.currentTimeMillis();
        
        System.out.println(date);
        System.out.println(String.format("%d VS. %d", (time2 - time1), (time3 - time2)));
    }
    
    public static void problemSorting() {
        List<String> list = MyUtil.toList("上海", "云南", "内蒙古", "北京", "吉林", "四川", "天津", "宁夏", "安徽", "山东", "山西", "广东",
                "广西", "新疆", "江苏", "江西", "河北", "河南", "浙江", "海南", "湖北", "湖南", "甘肃", "福建", "西藏", "贵州", "辽宁", "重庆", "陕西",
                "青海", "黑龙江");
        Collections.sort(list);
        System.out.println(list);
        list = MyUtil.toList("广东", "北京", "福建");
        Collections.sort(list);
        System.out.println(list);
    }
    
    public static void cloningTest() {
        Cloner cloner = MyFacade.getCloner();
        
        // 克隆 List
        List<String> lista = MyUtil.toList("a", "b");
        List<String> listb = cloner.deepClone(lista);
        System.out.println(listb);
        listb.add("c");
        System.out.println(listb);
        System.out.println(lista);
        
        // 设置不进行克隆的类
        cloner.dontClone(User.class);
        User usera = new User("Walt", "man", 10);
        User userb = cloner.deepClone(usera); // 会返回原对象
        System.out.println(userb.getId() + ", " + userb.getName());
        userb.setName("Sayid");
        System.out.println(usera.getId() + ", " + usera.getName());
        
        // 设置不进行克隆的类及子类
        cloner.dontCloneInstanceOf(List.class);
        listb = cloner.deepClone(lista);
        listb.add("z");
        System.out.println("not clone class: " + lista);
        
        // 数组
        String[] arra = { "a", "b" };
        String[] arrb = cloner.deepClone(arra);
        System.out.println(StringUtils.join(arrb));
        arrb[0] = "c";
        System.out.println(StringUtils.join(arrb));
        System.out.println(StringUtils.join(arra));
    }
    
    /**
     * 测试手动克隆对象
     */
    @SuppressWarnings("unchecked")
    public static void cloneTest() {
        try {
            // BeanUtils.cloneBean() 无法正确克隆 List
            List<String> lista = MyUtil.toList("a", "b");
            List<String> listb = (List<String>) BeanUtils.cloneBean(lista);
            System.out.println(lista);
            System.out.println(listb);
            listb.add("c");
            System.out.println(lista);
            System.out.println(listb);
            
            // BeanUtils.cloneBean() 可以 克隆 Date 对象
            Date datea = DateUtil.parseDateNormal("2001-01-01");
            Date dateb = (Date) BeanUtils.cloneBean(datea);
            System.out.println(dateb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void jsonTest2() {
        String content;
        try {
            File file = new File("E:\\channels.txt");
            content = FileUtils.readFileToString(file, "UTF-8");
            long start = System.currentTimeMillis();
            JSONArray json = JSONArray.fromObject(content);
            long end = System.currentTimeMillis();
            System.out.println(json.get(100));
            System.out.println("size: " + json.size());
            System.out.println("time: " + (end - start) + " ms.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    // This method returns all available services types
    public static String[] getServiceTypes() {
        Set<String> result = new HashSet<String>();
        
        // All all providers
        Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            // Get services provided by each provider
            Set<Object> keys = providers[i].keySet();
            for (Iterator<Object> it = keys.iterator(); it.hasNext();) {
                String key = (String) it.next();
                key = key.split(" ")[0];
                
                if (key.startsWith("Alg.Alias.")) {
                    // Strip the alias
                    key = key.substring(10);
                }
                int ix = key.indexOf('.');
                result.add(key.substring(0, ix));
            }
        }
        return result.toArray(new String[result.size()]);
    }
    
    // This method returns the available implementations for a service type
    public static String[] getCryptoImpls(String serviceType) {
        Set<String> result = new HashSet<String>();
        
        // All all providers
        Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            // Get services provided by each provider
            Set<Object> keys = providers[i].keySet();
            for (Iterator<Object> it = keys.iterator(); it.hasNext();) {
                String key = (String) it.next();
                key = key.split(" ")[0];
                
                if (key.startsWith(serviceType + ".")) {
                    result.add(key.substring(serviceType.length() + 1));
                } else if (key.startsWith("Alg.Alias." + serviceType + ".")) {
                    // This is an alias
                    result.add(key.substring(serviceType.length() + 11));
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
    
    public static void cipherTest() {
        String algorithm = "aes";
        String text = "00112233445566778899aabbccddeeff";
        String keyStr = "000102030405060708090a0b0c0d0e0f";
        try {
            byte[] key = Hex.decodeHex(keyStr.toCharArray());
            SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
            Cipher cipherToEncrypt = Cipher.getInstance(algorithm);
            Cipher cipherToDecrypt = Cipher.getInstance(algorithm);
            cipherToEncrypt.init(Cipher.ENCRYPT_MODE, keySpec);
            cipherToDecrypt.init(Cipher.DECRYPT_MODE, keySpec);
            
            byte[] encryptedBytes = cipherToEncrypt.doFinal(text.getBytes("UTF-8"));
            String encryptedText = new String(encryptedBytes, "UTF-8");
            System.out.println("encrypted: " + encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void messageDigestTest() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 第一种方法，通过update添加数据
            md.update("computer".getBytes());
            md.update("networks".getBytes());
            System.out.println(new String(md.digest())); // digest() 会重置数据
            
            // 第二种方法，不用 update，效果跟第一种相同
            System.out.println(new String(md.digest("computernetworks".getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void randTest() {
        byte[] bytes = new byte[10];
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            rand.nextBytes(bytes);
            System.out.println(JSONArray.fromObject(bytes));
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.println(rand.nextInt(1));
        }
    }
    
    /**
     * 压缩
     */
    public static void zipTest() {
        try {
            // 压缩
            FileOutputStream fos = new FileOutputStream("/home/test.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry("a1.txt")); // 为 zip 添加一个名为 a.txt 的文件
            zos.write("just a test".getBytes()); // 写入内容到上述文件
            zos.write("\r\nand u are here~".getBytes());
            zos.putNextEntry(new ZipEntry("cc.txt")); // 添加另一个文件
            zos.write("another file~".getBytes());
            zos.setComment("i can zip it~"); // 添加注释
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 解压
     */
    public static void unzipTest() {
        try {
            FileInputStream fis = new FileInputStream("ispDownLoad_7jnD7a_20110313225359_return_content.zip");
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) { // 读取 zip
                                                        // 中下一个文件，并将流定位到该文件数据的开始位置
                System.out.println(ze);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int length;
                byte[] bytes = new byte[512];
                while ((length = zis.read(bytes)) != -1) { // 读取直至当前文件结束
                    baos.write(bytes, 0, length);
                }
                baos.close();
                System.out.println(baos.toString());
            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void enumTest() {
        for (EsfType esfType : EsfType.values()) {
            System.out.println(esfType + ":" + esfType.name() + "," + esfType.ordinal() + "," + esfType.getName());
        }
        System.out.println();
        // 用EnumSet.range()方法指定枚举的范围
        for (EsfType esfType : EnumSet.range(EsfType.JOINRENT, EsfType.REQUEST)) {
            System.out.println(esfType);
        }
        System.out.println();
        System.out.println(EsfType.valueOf("SALERENT"));
        
        // switch 中使用枚举
        System.out.println();
        EsfType type = EsfType.JOINRENT;
        switch (type) {
            case SALERENT: // 不能加 EsfType 前缀
                System.out.println("got salerent");
                break;
            
            case JOINRENT:
                System.out.println("got join");
                break;
            
            default:
                System.out.println("got request");
                break;
        }
    }
    
    public static void scriptEngineTest() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> list = manager.getEngineFactories();
        for (ScriptEngineFactory factory : list) { // 列出所有支持的 script 引擎，默认只支持
                                                   // JavaScript
            System.out.println(factory.getLanguageName() + ", " + factory.getLanguageVersion() + ", "
                    + factory.getNames());
        }
        
        ScriptEngine engine = manager.getEngineByName("javascript");
        System.out.println(engine.eval("encodeURIComponent(' 中');"));
        long start = System.currentTimeMillis();
        System.out.println(engine.eval("decodeURIComponent('%20%E4%B8%AD');"));
        System.out.println("time: " + (System.currentTimeMillis() - start) + " ms.");
    }
    
    public static void escapteUtilsTest() throws UnsupportedEncodingException {
        String str = "\u4E2D";
        String str2 = "中";
        System.out.println(StringEscapeUtils.unescapeJavaScript(str));
        System.out.println(StringEscapeUtils.escapeJavaScript(str2));
        
        String str3 = " /.-*_~!()'";
        String str4 = "%E6%B5%B7%E7%8F%A0";
        System.out.println(URLEncoder.encode(str3, "UTF-8").replaceAll("\\+", "%2B"));
        long start = System.currentTimeMillis();
        System.out.println(URLDecoder.decode(str4, "GB2312"));
        System.out.println("time: " + (System.currentTimeMillis() - start) + " ms.");
    }
    
    public static void jsonConfigTest() {
        User user1 = new User("reniaL", "male", 25);
        User user2 = new User("vivian", "female", 25);
        User user3 = new User("ruby", "female", 20);
        Map<String, User> map = new HashMap<String, User>();
        map.put("reniaL", user1);
        map.put("vivian", user2);
        map.put("age", user3);
        map.put("gg", user3);
        
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[] { "age" }); // 排除在外的属性
        JSONObject json = JSONObject.fromObject(map, config);
        System.out.println(json);
    }
    
    public static void stringTest() {
        String s1 = "abc"; // 字符串常量会被放进字符串池中，供后续引用
        String s2 = "abc"; // 会引用上面已经放进串池中的地址
        String s3 = new String("abc"); // 使用构造函数则一定会新建一个对象，地址独立
        String s4 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s3 == s4);
        System.out.println("abc" == "abc");
        
        System.out.println();
        String t3 = s1 + s2; // 运行时才能确定的字符串，不会到串池中去搜索
        String t4 = s1 + s2;
        System.out.println(t3 == t4);
        
        t3 = t3.intern(); // 如果串池中已包含等于该String的字符串（使用equals()方法确定），则返回该字符串。否则将该String对象添加到串池中，并返回
        t4 = t4.intern();
        System.out.println(t3 == t4);
        
        final String t1 = "abc"; // 编译时可以确定的字符串，会到串池中搜索
        final String t2 = "abc";
        t3 = t1 + t2;
        t4 = t1 + t2;
        System.out.println(t3 == t4);
    }
    
    @SuppressWarnings("unchecked")
    public static void dateutilsTest() {
        Calendar cal = Calendar.getInstance();
        Iterator<GregorianCalendar> iterator = DateUtils.iterator(cal, DateUtils.RANGE_WEEK_CENTER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (iterator.hasNext()) {
            GregorianCalendar gc = iterator.next();
            System.out.println(sdf.format(gc.getTime()));
        }
    }
    
    public static void printLib() {
        String path = "/workspace/diy/renial/lib";
        File libDir = new File(path);
        if (libDir.isDirectory()) {
            File[] files = libDir.listFiles();
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }
    
    /**
     * 测试IO时文件不存在的情况
     */
    public static void fileNotFoundTest() {
        String path = "/home/abcde.txt";
        try {
            InputStream is = new FileInputStream(path); // 文件不存在时会抛出FileNotFoundException
            byte[] b = new byte[1024];
            int length = is.read(b);
            System.out.println(new String(ArrayUtils.subarray(b, 0, length)));
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void staticTest(long time) {
        try {
            System.out.println("before sleep: " + time);
            Thread.sleep(5000);
            System.out.println("after sleep: " + time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void bomTest() {
        String path = "/home/abc.txt";
        // File file = new File(path);
        // String content;
        try {
            InputStream is = new FileInputStream(path);
            InputSource source = new InputSource(is);
            System.out.println(source.getEncoding());
            
            InputStreamReader isr = new InputStreamReader(is);
            source = new InputSource(isr);
            System.out.println(source.getEncoding());
            // content = FileUtils.readFileToString(file);
            // System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void arrayTest() {
        int[] arr = { 1, 2, 5 };
        Integer[] objArr = ArrayUtils.toObject(arr); // primitive to object
        System.out.println(StringUtils.join(objArr, ", ")); // array element join
        
        // addAll()
        String[] arr1 = { "a1", "a2" };
        String[] arr2 = { "b1", "b2", "b3" };
        String[] arr3 = (String[]) ArrayUtils.addAll(arr2, arr1);
        System.out.println(JSONArray.fromObject(arr3));
        
        // add()
        String[] strs = { "a", "b", "c" };
        System.out.println(String.format("%s, %s, %s, %s", ArrayUtils.add(strs, 2, "can i?")));
        System.out.println(JSONArray.fromObject(strs));
        
        // isEmpty()
        arr = null;
        System.out.println("null is empty? " + ArrayUtils.isEmpty(arr));
        arr = new int[0];
        System.out.println("0 size is empty? " + ArrayUtils.isEmpty(arr));
    }
    
    public void testClass() {
        System.out.println(Test.class);
        System.out.println(getClass());
    }
    
    public static void instanceofTest() {
        Object[] objs = { new House("renisL", "Just a test."), "a string" };
        for (Object obj : objs) {
            if (obj instanceof House) {
                House house = (House) obj;
                System.out.println("house:" + house.getName() + "," + house.getTitle());
            } else {
                System.out.println("not a house");
            }
        }
    }
    
    public static void listTest() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (int i = 1; i < list.size(); i += 2) {
            list.remove(i);
        }
        System.out.println(list);
        list = list.subList(0, list.size() - 1);
        System.out.println(list);
        
        // array to List
        Integer[] arr = { 1, 3, 5 };
        list = Arrays.asList(arr);
        System.out.println(list);
        
        // simple way to create list
        list = Arrays.asList(2, 4, 6);
        System.out.println(list);
        
        // equal test
        List<Integer> list2 = Arrays.asList(2, 4, 6);
        System.out.println("list.equals(list2): " + list.equals(list2));
    }
    
    public static void objectParamTest(House h) {
        System.out.println("in objectParamTest before:" + h.getName());
        h.setName("Vivian");
        System.out.println("in objectParamTest after:" + h.getName());
    }
    
    @SuppressWarnings("unchecked")
    public static void genericArrayTest() throws Exception {
        // Map<Object, Object>[] ms = new HashMap<Object, Object>[10]; // 不能实例化泛型数组
        Map<String, String>[] maps = new HashMap[10]; // 不是泛型就可以，但会有warning，依然不建议使用
        System.out.println(maps);
    }
    
    public static void regexTest() {
        // Pattern p = Pattern.compile("xa(?=b)y");
        // Matcher m = p.matcher("xaacyxabyxaby");
        // while(m.find()) {
        // System.out.println(m.group()+", start="+m.start()+", end="+m.end());
        // }
        String str = "xay xaay xby xaby xabcy xbay xacy";
        String regex = "x((a(?!bc))*|[^a]*)y";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        System.out.println();
        
        str = "    location {\n         name          \"CHN-SH-ZJ\" it does!\n         ttl           120\n         max_ip        2\n\n         address       61.164.154.249 都可以～\n         address       11.164.154.111 ratio 2 lqbz\n         address       121.207.233.15\n    }\n\n}\n\n";
        regex = "^([ \t]+\\S+)([ \t]+)\"?([\\S&&[^{\"]]+)\"?.*?$";
        pattern = Pattern.compile(regex, Pattern.MULTILINE);
        matcher = pattern.matcher(str);
        String newStr = matcher.replaceAll("$1:$2\"$3\",").replaceAll("\\{", ":{").replaceAll("}", "},");
        System.out.println("before:\n" + str);
        System.out.println("after:\n" + newStr);
        System.out.println();
        
        String[] strs = { "CHN-GZ-GD-SZ", "CHN-GZ-GD", "CHN-GZ-GD SZ", "CHN-GZ-GD-SZ A" };
        regex = "^([A-Z]+)-[A-Z]+-([A-Z]+)(?:-[A-Z]+)?$";
        for (String s : strs) {
            System.out.println(s + ": " + s.matches(regex));
        }
        System.out.println();
        
        strs = new String[] { "12345678901", "123", "12345678901234567890", "aaaaaaaaaaaaaa", "a1234567890123",
                "12345678901b" };
        regex = "^\\d{11,256}$";
        for (String s : strs) {
            System.out.println(s + ": " + s.matches(regex));
        }
    }
    
    public static void xmlCharTest() {
        int i = 0;
        for (char c = 0x0; c <= 0x19; c++) {
            System.out.println(i + " = " + c);
            i++;
        }
    }
    
    public static void inputStreamReaderTest() {
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream("/home/test.txt"), "UTF-8");
            System.out.println(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void fileTest() {
        String path = "/home/abcd.txt";
        File file = new File(path);
        System.out.println("file: " + file);
        System.out.println("name: " + file.getName());
        System.out.println("isfile:" + file.isFile());
        if (file.exists()) { // 文件存在
            if (file.isFile()) { // 是文件
                System.out.println("status: file");
                try { // 读取并输出文件内容
                    String str = FileUtils.readFileToString(file);
                    System.out.println("content: \n" + str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else { // 不是文件，是目录
                System.out.println("status: dir");
                
                // 输出目录中的文件
                File[] children = file.listFiles();
                if (children != null) {
                    for (File f : children) {
                        if (f.isFile()) {
                            System.out.print("file:");
                        } else if (f.isDirectory()) {
                            System.out.print("dir:");
                        }
                        System.out.println(f.getAbsolutePath());
                    }
                } else {
                    System.out.println("no children~");
                }
            }
        } else { // 文件不存在
            System.out.println("status: lost");
            System.out.println("delete on lost: " + file.delete()); // 文件不存在的情况下进行删除
        }
    }
    
    /**
     * 测试 org.apache.commons.io.FileUtils
     */
    public static void fileUtilsTest() {
        long start = System.currentTimeMillis();
        String path = "/home/file/product_house/test/";
        File dir = new File(path);
        try {
            FileUtils.cleanDirectory(dir); // 只删除目录中的内容，不删除目录
            // FileUtils.deleteDirectory(dir); // 删除目录内容和目录
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("time: " + (System.currentTimeMillis() - start) + " ms.");
    }
    
    @SuppressWarnings("unchecked")
    public static void jsonTest() {
        // string to json
        String str = "// comment? \n'广汕':['天河区', '龙洞广汕'], '石牌':['天河区', '石牌'], '天河北':['天河区', '天河北'], query: {name: 'qq', pass: 'asd', }, 'count': '5', 'count': '5', 'status': 'true'}";
        JSONObject json = JSONObject.fromObject(str);
        System.out.println(json);
        System.out.println("count: "
                + new ArrayList<String>(JSONArray.toCollection(json.getJSONArray("count"), String.class)));
        System.out.println(json.optJSONArray("query")); // no exception
        System.out.println(json.optBoolean("status"));
        try {
            System.out.println(json.getJSONArray("query")); // net.sf.json.JSONException:
                                                            // JSONObject["query"] is
                                                            // not a JSONArray.
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // list to json
        System.out.println("\n\n=========list to json========");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        JSONArray ja = JSONArray.fromObject(list);
        System.out.println(ja);
        ja = null;
        System.out.println(JSONArray.fromObject(ja)); // 输出[null]
    }
    
    public static void mkdirTest() {
        String path = "E:/home/resource/model/product_house/model_bak/e/esf_foot/123_456";
        File file = new File(path);
        if (!file.exists()) {
            boolean result = file.getParentFile().mkdirs();
            System.out.println(result);
        }
    }
    
    public static void readFileTest() {
        try {
            String fileName = "output.xml";
            // 使用InputStreamReader可以指定文件编码，用FileReader则不行
            InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String oneLine;
            while ((oneLine = br.readLine()) != null) {
                System.out.println(oneLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void listToArrayTest() {
        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("cc");
        list.add("bb");
        String strs[] = list.toArray(new String[0]);
        for (String str : strs) {
            System.out.println(str);
        }
    }
    
    public static void mapTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "aa");
        map.put("b", "b2");
        System.out.println(map.containsKey(null)); // false
        System.out.println(map.containsValue(null)); // false
        System.out.println(map.get("c")); // null
        System.out.println(map.get(null)); // null
        
        for (Entry<String, String> entry : map.entrySet()) { // iterate
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        map.put(null, "null can be key");
        System.out.println(map.get(null));
    }
    
    public static void mapToBeanTest() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "reniaL");
        map.put("age", 24);
        map.put("gender", "m");
        map.put("birthdate", new Date());
        User bean = new User();
        try {
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("name:" + bean.getName());
        System.out.println("age:" + bean.getAge());
        System.out.println("gender: " + bean.getGender());
        System.out.println("birthdate: " + bean.getBirthday());
    }
    
    public static void fastDateFormatTest() {
        FastDateFormat fdf = DateFormatUtils.ISO_DATE_FORMAT;
        FastDateFormat fdf2 = FastDateFormat.getInstance("yyyy锟斤拷MM锟斤拷dd锟斤拷");
        Date date = new Date();
        System.out.println(fdf.format(date));
        System.out.println(fdf2.format(date));
    }
    
    public static void dayOfWeekTest() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.WEEK_OF_MONTH));
        System.out.println(cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
    }
    
    public static void timeTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
        for (int i = 0; i < 12; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, i);
            cal.add(Calendar.MONTH, 1);
            String thisMonth = sdf.format(cal.getTime());
            
            cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, i);
            cal.add(Calendar.MONTH, 2);
            String nextMonth = sdf.format(cal.getTime());
            
            cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, i);
            cal.add(Calendar.MONTH, (5 - cal.get(Calendar.MONTH) % 3));
            String nextSeason = sdf.format(cal.getTime());
            
            cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, i);
            cal.add(Calendar.MONTH, (8 - cal.get(Calendar.MONTH) % 3));
            String nextHalfYear = sdf.format(cal.getTime());
            
            System.out.println((i + 1) + ": \t" + thisMonth + "\t" + nextMonth + "\t" + nextSeason + "\t"
                    + nextHalfYear);
        }
    }
    
    public static void timeTest2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
        for (int i = 0; i < 12; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DATE, 18);
            cal.set(Calendar.MONTH, i);
            
            if (cal.get(Calendar.WEEK_OF_MONTH) > 3) {
                cal.add(Calendar.MONTH, 1);
            }
            String thisMonth = sdf.format(cal.getTime());
            
            cal.add(Calendar.MONTH, 1);
            String nextMonth = sdf.format(cal.getTime());
            
            cal.add(Calendar.MONTH, (3 - (cal.get(Calendar.MONTH) + 1) % 3));
            String nextSeason = sdf.format(cal.getTime());
            
            cal.add(Calendar.MONTH, 3);
            String nextHalfYear = sdf.format(cal.getTime());
            
            System.out.println((i + 1) + ": \t" + thisMonth + "\t" + nextMonth + "\t" + nextSeason + "\t"
                    + nextHalfYear);
        }
    }
    
    public static double toScale(double d, int scale) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    
    public static void ObjInListTest() {
        House house = new House();
        house.setTitle("MR.");
        List<House> list = new ArrayList<House>();
        list.add(house);
        System.out.println(list.get(0).getName());
        
        House h2 = list.get(0);
        h2.setName("Peter");
        System.out.println(list.get(0).getName());
        
        List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "aa");
        list2.add(map);
        map.put("b", "bb");
        System.out.println(JSONArray.fromObject(list2));
    }
    
    /**
     * 测试各种parse（数字、日期等）
     */
    public static void parseTest() {
        String str = null;
        int i = Integer.parseInt(str);
        System.out.println(i);
    }
    
    /**
     * 修改从Map中取出的对象，是否会影响原来Map中的对象？
     */
    public static void ObjectInMapTest() {
        // 测试String：重新赋值，不会影响
        Map<Integer, String> strMap = new HashMap<Integer, String>();
        strMap.put(1, "aa");
        System.out.println(strMap.get(1));
        @SuppressWarnings("unused")
        String str = strMap.get(1);
        str = "bb";
        System.out.println(strMap.get(1));
        
        // 测试自定义bean：不赋值只修改，会影响
        Map<String, User> beanMap = new HashMap<String, User>();
        User bean = new User("reniaL", "m", 25);
        beanMap.put("a", bean);
        System.out.println(beanMap.get("a").getName());
        User bean2 = beanMap.get("a");
        bean2.setName("Peter");
        System.out.println(beanMap.get("a").getName());
        
        Map<String, String> map1 = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        Map<String, String> curMap = new HashMap<String, String>();
        curMap = map1;
        curMap.put("a", "a1");
        curMap = map2;
        curMap.put("b", "b2");
        System.out.println("map1:");
        for (String key : map1.keySet()) {
            System.out.println(map1.get(key));
        }
        System.out.println("map2:");
        for (String key : map2.keySet()) {
            System.out.println(map2.get(key));
        }
    }
    
    /**
     * 测试Calendar的field
     */
    public static void calFieldTest() {
        System.out.println(String.format("%3d%3d%3d", Calendar.MONDAY, Calendar.WEDNESDAY, Calendar.SUNDAY));
        Calendar cal = Calendar.getInstance();
        // cal.set(2010, 7, 21);
        System.out.println(cal.getTime());
        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(cal.get(Calendar.DAY_OF_YEAR));
        System.out.println(cal.get(Calendar.WEEK_OF_MONTH)); // 自然周
        System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
        System.out.println(cal.get(Calendar.DAY_OF_WEEK_IN_MONTH)); // 非自然周，每个月的1-7日为第一周，8-14日为第二周，依此类推
        
        System.out.println();
        System.out.println("min date: " + cal.getMinimum(Calendar.DATE)); // 每月最小日期数
        System.out.println("max date: " + cal.getMaximum(Calendar.DATE)); // 每月最大日期书
        System.out.println("actual min date: " + cal.getActualMinimum(Calendar.DATE)); // 当月最小日期数
        System.out.println("actual max date: " + cal.getActualMaximum(Calendar.DATE)); // 当月最大日期数
        System.out.println("actual min day of week: " + cal.getActualMinimum(Calendar.DAY_OF_WEEK));
        System.out.println("actual min month: " + cal.getActualMinimum(Calendar.MONTH));
        System.out.println();
        
        // 上月最后一天
        cal.set(Calendar.DATE, 0);
        System.out.println(cal.getTime());
        
        cal.set(2010, 7, 31);
        cal.add(Calendar.MONTH, -2);
        System.out.println(cal.getTime());
    }
    
    /**
     * 测试 Calendar.roll(int field, int amount)
     */
    public static void calRollTest() {
        Calendar cal = Calendar.getInstance();
        int rollValue = 0;
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                rollValue = -2;
                break;
            case 7:
                rollValue = -1;
                break;
        }
        cal.roll(Calendar.DATE, rollValue);
        
        System.out.println(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
    }
    
    public static void timezoneTest() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));
        System.out.println(cal.get(Calendar.HOUR));
        DateFormat df = DateFormat.getDateTimeInstance();
        System.out.println(df.format(cal.getTime()));
        df.setTimeZone(TimeZone.getTimeZone("EST"));
        System.out.println(df.format(cal.getTime()));
    }
    
    public static void doubleCompTest() {
        Double d1 = 2.51;
        Object d2 = null;
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d1.toString().equals(d2));
        System.out.println(d1.equals(d2));
    }
    
    public static void splitTest() {
        String str = "ab##cd##ef";
        String[] arr = StringUtils.split(str, "#");
        System.out.println("size:" + arr.length);
        for (String s : arr) {
            System.out.println(s);
        }
        
        str = "ab cd, ef gh";
        arr = str.split(",");
        System.out.println("size:" + arr.length);
        for (String s : arr) {
            System.out.println(s);
        }
    }
    
    public static void strFormatTest() {
        System.out.println(String.format("%-20s%5d", "testt测试", 20));
    }
    
    public static void strSubTest() {
        String str = "abc/def.jpg";
        System.out.println(str.substring(0, str.lastIndexOf("/") + 1) + "t_" + str.substring(str.lastIndexOf("/") + 1));
    }
    
    public static void switchTest() {
        char ch = 'd';
        
        switch (ch) {
        
            default:
                System.out.println("in default");
                // break;
            case 'f':
                System.out.println("fw");
                // break;
            case 'm':
                System.out.println("mth");
        }
    }
    
    public static void decimalFormatTest() {
        DecimalFormat df1 = new DecimalFormat("0000000.0");
        DecimalFormat df2 = new DecimalFormat("#.#");
        DecimalFormat df3 = new DecimalFormat("000.000");
        DecimalFormat df4 = new DecimalFormat("###.###");
        DecimalFormat df5 = new DecimalFormat("0.000%");
        DecimalFormat df6 = new DecimalFormat(",###");
        
        System.out.println(df1.format(12.34));
        System.out.println(df2.format(12.34));
        System.out.println(df3.format(12.345578));
        System.out.println(df4.format(12.34));
        System.out.println(df5.format(.0012));
        System.out.println(df6.format(1234567.89));
        
        BigDecimal bd = new BigDecimal(12345.67890);
        System.out.println(df6.format(bd));
    }
    
    public static void decimalScaleTest() {
        BigDecimal bd = new BigDecimal(12.34567890123456789);
        bd = bd.setScale(5, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd.doubleValue());
        bd = null;
    }
    
    public static void dateParseTest() {
        try {
            DateFormat df = DateFormat.getDateInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("时间：yyyy年MM月dd日");
            
            Date d1 = df.parse("2008-05-04");
            Date d2 = sdf.parse("2008-03-20asdf");
            System.out.println(d1);
            System.out.println(d2);
            System.out.println(sdf2.format(new Date()));
            
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            Date d = new Date();
            System.out.println(sdf.format(d));
            System.out.println(sdf2.format(d));
            
            // pattern 中含有字母的情况
            String str = "2009Ta0818ss174611";
            String pattern = "yyyyTaMMddssHHmmss"; // 不加单引号的话会抛异常
                                                   // java.lang.IllegalArgumentException
            sdf = new SimpleDateFormat(pattern);
            System.out.println(sdf.parseObject(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
