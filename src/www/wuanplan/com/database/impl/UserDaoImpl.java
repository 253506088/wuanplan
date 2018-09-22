package www.wuanplan.com.database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import www.wuanplan.com.database.dao.UserDao;
import www.wuanplan.com.database.pojo.User;
import www.wuanplan.com.database.toll.DataBaseTool;

/**
 * UserDao的实现类
 * 
 * @author hp
 *
 */
public class UserDaoImpl implements UserDao {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public User login(User user) {
		User buffer = this.getUserByUsername(user.getUsername());
		if (buffer != null && buffer.getPassword().equals(user.getPassword())) {
			return buffer;
		}
		return null;
	}

	@Override
	public Integer count() {
		String sql = "SELECT COUNT(1) FROM `user`";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Integer count = null;
		try {
			conn = DataBaseTool.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = DataBaseTool.execute(conn, pstm, null, sql, null);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			logger.error("查询用户总数出错!", ex);
		} finally {
			try {
				DataBaseTool.close(conn, pstm, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public List<User> getUserList(Integer from, Integer limt) {
		String sql = "SELECT * FROM `user` ORDER BY id DESC LIMIT ?,?;";
		Object[] params = { from, limt };
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			conn = DataBaseTool.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = DataBaseTool.execute(conn, pstm, null, sql, params);
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getInt("admin"), rs.getString("gmtCreate"), rs.getString("gmtModified"),
						rs.getInt("isDeleted"));
				users.add(user);
			}
		} catch (Exception ex) {
			logger.error("分页查询用户出错!", ex);
		} finally {
			try {
				DataBaseTool.close(conn, pstm, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM `user` WHERE `username` = ?";
		Object[] params = { username };
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DataBaseTool.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = DataBaseTool.execute(conn, pstm, null, sql, params);
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("admin"),
						rs.getString("gmtCreate"), rs.getString("gmtModified"), rs.getInt("isDeleted"));
			}
		} catch (Exception ex) {
			logger.error("查询用户出错!", ex);
		} finally {
			try {
				DataBaseTool.close(conn, pstm, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public Boolean modifyPssword(User user) {
		String sql = "UPDATE `user` SET `password` = ? ,gmtModified = ? WHERE id = ?";
		Object[] params = { user.getPassword(), new Date().getTime(), user.getId() };
		Connection conn = null;
		PreparedStatement pstm = null;
		Boolean flag = null;
		try {
			conn = DataBaseTool.getConnection();
			pstm = conn.prepareStatement(sql);
			flag = DataBaseTool.execute(conn, pstm, sql, params) > 0 ? true : false;
		} catch (Exception ex) {
			logger.error("修改用户出错!", ex);
		} finally {
			try {
				DataBaseTool.close(conn, pstm, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public Boolean modifyAdmin(User user) {
		String sql = "UPDATE `user` SET `admin` = ? WHERE id = ?";
		Object[] params = { user.getAdmin(), user.getId() };
		Connection conn = null;
		PreparedStatement pstm = null;
		Boolean flag = null;
		try {
			conn = DataBaseTool.getConnection();
			pstm = conn.prepareStatement(sql);
			flag = DataBaseTool.execute(conn, pstm, sql, params) > 0 ? true : false;
		} catch (Exception ex) {
			logger.error("修改用户权限出错!", ex);
		} finally {
			try {
				DataBaseTool.close(conn, pstm, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public Boolean addUser(User user) {
		String sql = "INSERT INTO `user`(`username`,`password`,`gmtCreate`,`gmtModified`) VALUES(?,?,?,?)";
		String date = String.valueOf(new Date().getTime());// 保证更新与修改时间戳一致
		Object[] params = { user.getUsername(), user.getPassword(), date, date };
		Connection conn = null;
		PreparedStatement pstm = null;
		Boolean flag = null;
		try {
			conn = DataBaseTool.getConnection();
			pstm = conn.prepareStatement(sql);
			flag = DataBaseTool.execute(conn, pstm, sql, params) > 0 ? true : false;
		} catch (Exception ex) {
			logger.error("新增用户出错!", ex);
		} finally {
			try {
				DataBaseTool.close(conn, pstm, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return flag;
	}
}
