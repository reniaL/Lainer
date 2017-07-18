package demo.jse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

    public static void main(String[] args) {
        matcherDemo();
    }

    public static void matcherDemo() {
        Pattern pattern = Pattern.compile("\\s+abc\\s+");
        Matcher matcher = pattern.matcher("");
        String[] arr = {" abc ", "abc", "xx  abc  xx"};
        for (String str : arr) {
            matcher.reset(str);
            System.out.println(str);
            System.out.println("\t" + matcher.matches()); // match entire string

            matcher.reset(str);
            System.out.println("\t" + matcher.find()); // match sub-sequence
        }
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
}
