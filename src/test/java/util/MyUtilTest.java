package util;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class MyUtilTest {
    
    @Test
    public void testFormatSql() {
        String sql = "select * from(select sum(todayflat) as todayflat, sum(todayarea) as todayarea, sum(todaysalesamount) as todaysalesamount, sum(todayreturn) as todayreturn from producttrade where fetchdate between '2010-07-24' and '2010-07-30') a join (select sum(accflat) as accflat, sum(accarea) as accarea, sum(spareflat) as spareflat, sum(sparearea) as sparearea from producttrade where fetchdate = (select max(fetchdate) from producttrade_all where '2010-07-30' >= fetchdate)) b";
        System.out.println(MyUtil.formatSql(sql));
        assertTrue(true);
    }
    
    @Test
    public void testToList() {
        String[] strs = { "a", "c", "b", "z" };
        List<String> strList = MyUtil.toList(strs);
        strList.add("constant");
        strList.add(1, "god");
        System.out.println(strList);
        
        Integer[] ints = { 25, 1, 100 };
        List<Integer> intList = MyUtil.toList(ints);
        System.out.println(intList);
    }
    
}
