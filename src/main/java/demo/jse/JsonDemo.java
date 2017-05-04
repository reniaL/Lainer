package demo.jse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import domain.User;

public class JsonDemo {
    
    public static void main(String[] args) {
        jsonConfigTest();
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
    
}
