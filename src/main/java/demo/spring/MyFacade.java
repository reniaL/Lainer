package demo.spring;

import ibatis.IUserDao;
import ibatis.dao.UserDao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rits.cloning.Cloner;

public class MyFacade {
    
    private static BeanFactory BF;
    
    // 初始化BeanFactory
    static {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" });
        BF = appContext;
    }
    
    public static IUserDao getUserDao() {
        return (UserDao) BF.getBean("userDao");
    }
    
    public static Cloner getCloner() {
        return (Cloner) BF.getBean("cloner");
    }
    
}
