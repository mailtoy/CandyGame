package application;

import java.util.ArrayList;
import java.util.List;

public class LoginVerifyUser {
	public String id;
	public String password;
	public int score;
	public List<String> idList = new ArrayList<>();
	public List<String> passwordList = new ArrayList<>();
	public int isUser;
	
	/** Singleton **/
	private static LoginVerifyUser instance;

	private LoginVerifyUser() {

	}

	public static LoginVerifyUser getInstance() {
		if (instance == null) {
			instance = new LoginVerifyUser();
		}
		return instance;
	}
	
	public void addUser(String id){
		idList.add(id);
	}

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public int verifyUser(String id, String password) {
		if (idList.contains(id) && passwordList.contains(password)) {
			return 1;
		} else if (!idList.contains(id) && passwordList.contains(password)
				|| idList.contains(id) && !passwordList.contains(password)) {
			return 2;
		}
		return 0;
	}

}
