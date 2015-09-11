package ibatis.dao;

import ibatis.IUserDao;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import util.SqlMapClientSupport;
import cache.MethodCache;
import domain.User;

/**
 * @author reniaL
 */
@Component("userDao")
public class UserDao extends SqlMapClientSupport implements IUserDao {
    
    private static final Log log = LogFactory.getLog(UserDao.class);
    
    /*
     * (non-Javadoc)
     * @see ibatis.IUserDao#getUserById(int)
     */
    @MethodCache
    public User getUserById(int id) {
        log.info("id: " + id);
        try {
            return (User) getSqlMapClient().queryForObject("user.getUser", id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /*
     * (non-Javadoc)
     * @see ibatis.IUserDao#putUser(domain.User)
     */
    public int putUser(User user) {
        try {
            return (Integer) getSqlMapClient().insert("user.putUser", user);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}
