package www.wuanplan.com.database.pojo;

/**
 * user��ĳ־û���
 * 
 * @author hp
 *
 */
public class User {
	private Integer id;// ����id
	private String username;// �û���
	private String password;// �����MD5ֵ
	private Integer admin;// 1Ϊ����Ա��0Ϊ��ͨ�û�
	private String gmtCreate;// ����ʱ���ʱ���
	private String gmtModified;// �ϴ��޸�ʱ���ʱ���
	private Integer isDeleted;// 1Ϊ���ڣ�0Ϊ��ɾ��

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
