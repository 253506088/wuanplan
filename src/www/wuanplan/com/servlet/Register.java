package www.wuanplan.com.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import www.wuanplan.com.database.dao.UserDao;
import www.wuanplan.com.database.impl.UserDaoImpl;
import www.wuanplan.com.database.pojo.User;
import www.wuanplan.com.tool.GetMd5;

/**
 * ע�����Servlet
 * 
 * @author hp
 *
 */
public class Register extends HttpServlet {
	private UserDao userDao;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��֤������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		User user = new User(request.getParameter("username"), request.getParameter("password"));
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		if (user.getUsername() != null && user.getPassword() == null) {
			// ����Ƿ��Ѵ��ڸ��û�
			map.put("flag", this.getUserByUsername(user.getUsername()));
		} else if (user.getUsername() != null && user.getPassword() != null) {
			// ���ղ�����ע������
			try {
				map.put("flag", this.addUser(user));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		response.getWriter().print(JSONArray.toJSON(map));// ����JSON����
	}

	/**
	 * ��ʼ��
	 */
	private void into() {
		if (this.userDao == null) {
			userDao = new UserDaoImpl();
		}
	}

	/**
	 * ����û������Ƿ��ظ�
	 * 
	 * @param username
	 * @return
	 */
	private Boolean getUserByUsername(String username) {
		this.into();
		if (userDao.getUserByUsername(username) != null) {
			return false;// �Ѵ���
		}
		return true;// ������
	}

	/**
	 * ���ղ�����ע������
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	private Boolean addUser(User user) throws Exception {
		if (!this.getUserByUsername(user.getUsername()))
			return false;
		user.setPassword(GetMd5.md5(user.getPassword()));
		if (userDao.addUser(user)) {
			return true;
		}
		return false;
	}
}