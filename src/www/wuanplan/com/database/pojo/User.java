package www.wuanplan.com.database.pojo;

/**
 * user表的持久化类
 * 
 * @author hp
 *
 */
public class User {
	private Integer id;// 主键id
	private String username;// 用户名
	private String password;// 密码的MD5值
	private Integer admin;// 1为管理员，0为普通用户
	private String gmtCreate;// 创建时间的时间戳
	private String gmtModified;// 上此修改时间的时间戳
	private Integer isDeleted;// 1为存在，0为被删除

	public Integer getId() {
		return id;
	}

	public User(Integer id, String username, String password, Integer admin, String gmtCreate, String gmtModified,
			Integer isDeleted) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.isDeleted = isDeleted;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
