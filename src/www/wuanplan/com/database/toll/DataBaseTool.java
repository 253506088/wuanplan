package www.wuanplan.com.database.toll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC+C3P0����MySQL������
 * 
 * @author hp
 *
 */
public class DataBaseTool {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DataBaseTool.class.getName());

	// ���ݿ����ӳ�
	static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

	/**
	 * �����ӳ���ȡ��һ������
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		try {
			return dataSource.getConnection();
		} catch (Exception ex) {
			logger.error("��ȡ���ݿ����ӳ���!", ex);
			throw new Exception("��ȡ���ݿ����ӳ���!", ex);
		}
	}

	/**
	 * �ͷ����ӻ����ӳ�
	 * 
	 * @param conn
	 * @param pst
	 * @param rs
	 * @throws Exception
	 */
	public static void close(Connection conn, PreparedStatement pstm, ResultSet rs) throws Exception {
		String closeError = "���ݿ����ӹس���!";
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
	 * ��ѯ����
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
	 * ���²���,����Ӱ������
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
