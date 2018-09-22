package www.wuanplan.com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import www.wuanplan.com.database.dao.UserDao;
import www.wuanplan.com.database.impl.UserDaoImpl;
import www.wuanplan.com.database.pojo.User;

/**
 * ����Ա��ҳ�鿴�û���Ϣ
 * 
 * @author hp
 *
 */
public class UserList extends HttpServlet {
	private UserDao userDao;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��֤������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null)
			return;
		User user = (User) session.getAttribute("user");
		if (user.getAdmin() != 1)
			return;

		// װ�����
		this.into();
		Integer pageNumber = 1;
		if (request.getParameter("pageNumber") != null)
			pageNumber = new Integer(request.getParameter("pageNumber"));
		Integer pageSize = 10;
		Integer countPageNumber = this.getPageAllNumber(pageSize);
		if (pageNumber > countPageNumber || pageNumber < 1)
			pageNumber = 1;
		// ��ҳ��ѯ
		request.setAttribute("userList", this.getUserList(pageNumber, pageSize, countPageNumber));
		request.setAttribute("countPageNumber", countPageNumber);
		request.setAttribute("pageNumber", pageNumber);

		RequestDispatcher rd = request.getRequestDispatcher("userList.jsp");
		rd.forward(request, response);
	}

	/**
	 * ����ҳ��������ȡ��ҳ��
	 * 
	 * @param pageSize
	 * @return
	 */
	private Integer getPageAllNumber(Integer pageSize) {
		Integer countPageNumber = null;

		Integer count = userDao.count();
		if (count % pageSize != 0) {
			countPageNumber = 1 + (count / pageSize);
		} else {
			countPageNumber = count / pageSize;
		}
		return countPageNumber;
	}

	/**
	 * �����ҳ��ѯ�û��б�
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param countPageNumber
	 * @return
	 */
	private List<User> getUserList(Integer pageNumber, Integer pageSize, Integer countPageNumber) {
		// ������ӵڼ������в�ѯ
		Integer from = null;
		if (pageNumber > 1) {
			from = (pageSize * (pageNumber - 1));
		} else {
			from = 0;
		}
		return userDao.getUserList(from, pageSize);
	}

	/**
	 * ��ʼ��userDao
	 */
	private void into() {
		if (this.userDao == null)
			userDao = new UserDaoImpl();
	}
}
