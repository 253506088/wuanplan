package www.wuanplan.com.database.dao;

import java.util.List;

import www.wuanplan.com.database.pojo.User;

/**
 * 对user表进行增删改查操作的接口
 * 
 * @author hp
 *
 */
public interface UserDao {
	/**
	 * 用户登陆
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * 查询用户总数
	 * 
	 * @return
	 */
	public Integer count();

	/**
	 * 分页获取用户列表
	 * 
	 * @param from
	 * @param limt
	 * @return
	 */
	public List<User> getUserList(Integer from, Integer limt);

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);

	/**
	 * 根据id修改用户密码
	 * 
	 * @param user
	 * @return
	 */
	public Boolean modifyPssword(User user);

	/**
	 * 根据id修改用户权限
	 * 
	 * @param user
	 * @return
	 */
	public Boolean modifyAdmin(User user);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Boolean addUser(User user) throws Exception;
}
