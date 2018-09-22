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
 * –ﬁ∏ƒ√‹¬Î
 * 
 * @author hp
 *
 */
public class ModifyPassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		HttpSession session = request.getSession();
		String oldPassword = request.getParameter("oldPassword");// æ…√‹¬Î
		String password = request.getParameter("password");// –¬√‹¬Î

		if (oldPassword == null || password == null || session.getAttribute("user") == null)
			return;
		User user = (User) session.getAttribute("user");
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		// –ﬁ∏ƒ√‹¬Î
		if (user.getPassword().equals(GetMd5.md5(oldPassword))) {
			UserDao userDao = new UserDaoImpl();
			user.setPassword(GetMd5.md5(password));
			userDao.modifyPssword(user);
			session.setAttribute("user", user);
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		response.getWriter().print(JSONArray.toJSON(map));
	}
}
