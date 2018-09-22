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
 * �û���¼
 * 
 * @author hp
 *
 */
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��֤������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		User user = new User(request.getParameter("username"), request.getParameter("password"));
		// �������ݲ�Ϊ�ղŴ����¼����
		if (user.getUsername() != null && user.getPassword() != null) {
			UserDao userDao = new UserDaoImpl();
			user.setPassword(GetMd5.md5(user.getPassword()));
			HashMap<String, Boolean> map = new HashMap<String, Boolean>();
			user = userDao.login(user);
			if (user != null) {
				// ��½�ɹ�
				map.put("flag", true);
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(30 * 60);// seession��Ч��30����
				session.setAttribute("user", user);
			} else {
				// ��¼ʧ�ܣ��˺Ż��������
				map.put("flag", false);
			}
			response.getWriter().print(JSONArray.toJSON(map));
		}
	}
}
