package www.wuanplan.com.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;

import www.wuanplan.com.database.dao.UserDao;
import www.wuanplan.com.database.impl.UserDaoImpl;
import www.wuanplan.com.database.pojo.User;
import www.wuanplan.com.tool.GetMd5;

/**
 * 用户登录
 * 
 * @author hp
 *
 */
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 保证不乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		User user = new User(request.getParameter("username"), request.getParameter("password"));
		// 长传数据不为空才处理登录请求
		if (user.getUsername() != null && user.getPassword() != null) {
			UserDao userDao = new UserDaoImpl();
			user.setPassword(GetMd5.md5(user.getPassword()));
			HashMap<String, Boolean> map = new HashMap<String, Boolean>();
			user = userDao.login(user);
			if (user != null) {
				// 登陆成功
				map.put("flag", true);
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(30 * 60);// seession有效期30分钟
				session.setAttribute("user", user);
			} else {
				// 登录失败，账号或密码错误
				map.put("flag", false);
			}
			response.getWriter().print(JSONArray.toJSON(map));
		}
	}
}
