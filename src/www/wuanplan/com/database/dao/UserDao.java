package www.wuanplan.com.database.dao;

import java.util.List;

import www.wuanplan.com.database.pojo.User;

/**
 * ��user�������ɾ�Ĳ�����Ľӿ�
 * 
 * @author hp
 *
 */
public interface UserDao {
	/**
	 * �û���½
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * ��ѯ�û�����
	 * 
	 * @return
	 */
	public Integer count();

	/**
	 * ��ҳ��ȡ�û��б�
	 * 
	 * @param from
	 * @param limt
	 * @return
	 */
	public List<User> getUserList(Integer from, Integer limt);

	/**
	 * �����û�����ѯ�û�
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);

	/**
	 * ����id�޸��û�����
	 * 
	 * @param user
	 * @return
	 */
	public Boolean modifyPssword(User user);

	/**
	 * ����id�޸��û�Ȩ��
	 * 
	 * @param user
	 * @return
	 */
	public Boolean modifyAdmin(User user);

	/**
	 * �����û�
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Boolean addUser(User user) throws Exception;
}
