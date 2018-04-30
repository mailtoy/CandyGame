package backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;

public class UserConnectData {
	private static UserConnectData databaseConnect = null;
	private static ConnectionSource connectionSource = null;

	private static final String URL = "jdbc:mysql://localhost:3306/names";
	private Dao<DataTable, String> userDao;
	private List<DataTable> getDetailUser;
	private UpdateBuilder<DataTable, String> updateBuilder;

	/**
	 * set start connection source to be null
	 */
	private UserConnectData() {

		try {
			connectionSource = new JdbcConnectionSource(URL, "root", "");
			userDao = DaoManager.createDao(connectionSource, DataTable.class);
			updateBuilder = userDao.updateBuilder();
			// TableUtils.createTableIfNotExists(connectionSource,DataTable.class);

		} catch (SQLException e1) {
			System.out.println("create connection error");
			e1.printStackTrace();
		}

	}

	/**
	 * start and get the connection source
	 * 
	 * @return the connection source
	 * @throws SQLException
	 */
	public static UserConnectData getInstance() {
		if (databaseConnect == null)
			databaseConnect = new UserConnectData();
		return databaseConnect;
	}

	/**
	 * close the connection source
	 * 
	 * @throws IOException
	 */
	public void closeConnect() throws IOException {
		connectionSource.close();
	}

	/**
	 * get all users data in database.
	 * 
	 * @throws SQLException
	 *             when application can't connect to the database
	 */
	public List<DataTable> pullAllUserdata() {
		try {
			getDetailUser = userDao.queryForAll();
		} catch (SQLException e) {
			System.out.println("pull data error");
		}
		return getDetailUser;
	}

	/**
	 * Checking an input user account is already exist, or not.
	 * 
	 * @param id
	 *            is id of account (id is username)
	 * @return true if the input user account is already exist, otherwise return
	 *         false.
	 */
	public boolean isUserExist(String username) {
		DataTable userTable = null;
		try {
			userTable = userDao.queryForId(username);

		} catch (SQLException e) {
			System.out.println("User exist error");
		}
		return userTable != null;
	}

	/**
	 * Creating input account in the database.
	 * 
	 * @param userToAdd
	 *            is new account to create in the database.
	 */
	public void createUser(DataTable userToAdd) {
		try {
			userDao.createIfNotExists(userToAdd);
		} catch (SQLException e) {
			System.out.println("create user error");
		}
	}
	
	/**
	 * update this user data on the database
	 * 
	 * @param user
	 */
	public void updateUserData(DataTable user) {
		try {
			double wpm = 0;
			// find target row
			updateBuilder.where().eq("Username", user.getUsername());
			// update values
			updateBuilder.updateColumnValue("WPM", wpm);
			
			updateBuilder.update();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
