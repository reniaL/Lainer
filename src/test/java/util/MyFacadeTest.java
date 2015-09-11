package util;

import static org.junit.Assert.assertNotNull;
import ibatis.IUserDao;

import org.junit.Test;

import demo.spring.MyFacade;

public class MyFacadeTest {
    
    private IUserDao userDao = MyFacade.getUserDao();
    
    @Test
    public void testGetUserDao() {
        assertNotNull(userDao);
    }
    
}
