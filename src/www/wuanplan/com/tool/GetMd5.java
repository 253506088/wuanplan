package www.wuanplan.com.tool;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * md5加密
 * 
 * @author hp
 *
 */
public class GetMd5 {
	/**
	 * md5加密
	 * 
	 * @param string
	 * @return
	 */
	public static String md5(String string) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			String md5 = new BigInteger(1, md.digest()).toString(16);
			return fillMD5(md5);
		} catch (Exception e) {
			throw new RuntimeException("MD5计算错误:" + e.getMessage(), e);
		}
	}

	private static String fillMD5(String md5) {
		return md5.length() == 32 ? md5 : fillMD5("0" + md5);
	}
}
