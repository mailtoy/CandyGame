package backend;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "datatable")
public class DataTable {

	@DatabaseField(id = true)
	private String Username;
	@DatabaseField(canBeNull = false)
	private String Password;
	@DatabaseField
	private int highScore;

	/**
	 * no-arg constructor for ORMLite
	 */
	public DataTable() {
		// ORMLite needs a no-arg constructor
	}

	/**
	 * creating new user
	 * 
	 * @param name
	 *            = user name
	 * @param password
	 *            = password
	 */
	public DataTable(String name, String password) {
		this.Username = name;
		this.Password = password;
	}

	// all getter and setter methods of all fields.
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highscore) {
		highScore = highscore;

	}
	
	@Override
	public String toString() {
		return getUsername()+" "+getHighScore();
	}

}
