package ibatis;

import domain.User;

/**
 * @author reniaL
 */
public interface IUserDao {
    
    /**
     * 根据id获取用信息
     */
    public User getUserById(int id);
    
    /**
     * 新增一个用户
     * @return 返回用户id，失败时返回-1
     */
    public int putUser(User user);
    
}
