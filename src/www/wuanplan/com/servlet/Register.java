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
 * 注册相关Servlet
 * 
 * @author hp
 *
 */
public class Register extends HttpServlet {
	private UserDao userDao;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 保证不乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		User user = new User(request.getParameter("username"), request.getParameter("password"));
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		if (user.getUsername() != null && user.getPassword() == null) {
			// 检查是否已存在该用户
			map.put("flag", this.getUserByUsername(user.getUsername()));
		} else if (user.getUsername() != null && user.getPassword() != null) {
			// 接收并处理注册请求
			try {
				map.put("flag", this.addUser(user));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		response.getWriter().print(JSONArray.toJSON(map));// 返回JSON数据
	}

	/**
	 * 初始化
	 */
	private void into() {
		if (this.userDao == null) {
			userDao = new UserDaoImpl();
		}
	}

	/**
	 * 检查用户名称是否重复
	 * 
	 * @param username
	 * @return
	 */
	private Boolean getUserByUsername(String username) {
		this.into();
		if (userDao.getUserByUsername(username) != null) {
			return false;// 已存在
		}
		return true;// 不存在
	}

	/**
	 * 接收并处理注册请求
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