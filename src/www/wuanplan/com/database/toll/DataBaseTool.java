package www.wuanplan.com.database.toll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC+C3P0操作MySQL工具类
 * 
 * @author hp
 *
 */
public class DataBaseTool {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DataBaseTool.class.getName());

	// 数据库连接池
	static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

	/**
	 * 从连接池中取用一个连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		try {
			return dataSource.getConnection();
		} catch (Exception ex) {
			logger.error("获取数据库连接出错!", ex);
			throw new Exception("获取数据库连接出错!", ex);
		}
	}

	/**
	 * 释放连接回连接池
	 * 
	 * @param conn
	 * @param pst
	 * @param rs
	 * @throws Exception
	 */
	public static void close(Connection conn, PreparedStatement pstm, ResultSet rs) throws Exception {
		String closeError = "数据库连接关出错!";
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				logger.error(closeError, ex);
				throw new Exception(closeError, ex);
			}
		}

		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException ex) {
				logger.error(closeError, ex);
				throw new Exception(closeError, ex);
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				logger.error(closeError, ex);
				throw new Exception(closeError, ex);
			}
		}
	}

	/**
	 * 查询操作
	 * 
	 * @param conn
	 * @param pstm
	 * @param rs
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static ResultSet execute(Connection conn, PreparedStatement pstm, ResultSet rs, String sql, Object[] params)
			throws Exception {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				pstm.setObject(i + 1, params[i]);
			}
		}
		rs = pstm.executeQuery();
		return rs;
	}

	/**
	 * 更新操作,返回影响行数
	 * 
	 * @param conn
	 * @param pstm
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static Integer execute(Connection conn, PreparedStatement pstm, String sql, Object[] params)
			throws Exception {
		int updateLine = 0;
		for (int i = 0; i < params.length; i++) {
			pstm.setObject(i + 1, params[i]);
		}
		updateLine = pstm.executeUpdate();
		return updateLine;
	}
}
