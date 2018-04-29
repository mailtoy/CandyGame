package application;

import java.util.List;

public class LoginVerifyUser {
	public String id;
	public String password;
	public List<String> idList;
	public List<String> passwordList;
	public int isUser;
	
	public LoginVerifyUser(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}

	
	public int verifyUser() {
		for (String aId : idList) {
			for (String aPassword : passwordList) {
				if (id.equals(aId) && password.equals(aPassword) ) {
					return 1;
				} if (id.equals(aId) && !password.equals(aPassword) || !id.equals(aId) && !password.equals(aPassword)) {
					return 2;
				}
			}
		}
		return 0;
	}
	

}
