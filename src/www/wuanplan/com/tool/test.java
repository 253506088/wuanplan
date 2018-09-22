package www.wuanplan.com.tool;

import com.alibaba.fastjson.JSONArray;

import www.wuanplan.com.database.impl.UserDaoImpl;
import www.wuanplan.com.database.pojo.User;

public class test {
	public static void main(String[] args) {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		User user = new User("123456", GetMd5.md5("123456"));
		user.setAdmin(0);
		user.setId(2);
		System.out.println(JSONArray.toJSON(userDaoImpl.getUserList(0, 3)));
	}
}
