package ibatis.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import ibatis.IUserDao;

import java.util.Calendar;

import net.sf.json.JSONObject;

import org.junit.Test;

import demo.spring.MyFacade;
import domain.User;

public class UserDaoTest {
    
    IUserDao dao = MyFacade.getUserDao();
    
    @Test
    public void testGetUserById() {
        assertNotNull(dao);
        int id = 3;
        User user = dao.getUserById(id);
        assertNotNull(user);
        System.out.println(JSONObject.fromObject(user));
    }
    
    @Test
    public void testPutUser() {
        User user = new User();
        user.setName("Sophia");
        user.setAge(10);
        user.setGender("female");
        Calendar cal = Calendar.getInstance();
        cal.set(2001, 12, 25);
        user.setBirthday(cal.getTime());
        
        int id = dao.putUser(user);
        assertTrue(id != -1);
        System.out.println("result id: " + id);
    }
    
}
