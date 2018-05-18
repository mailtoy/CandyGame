package backend;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

public class UserConnectData {
	private static UserConnectData databaseConnect = null;
	private static ConnectionSource connectionSource = null;
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("ip");
	private static final String NAME = bundle.getString("jdbc.username");
	private static final String URL = bundle.getString("jdbc.url");
	private static final String PW = bundle.getString("jdbc.password");
	
	private Dao<DataTable, String> userDao;
	private List<DataTable> getDetailUser;
	private UpdateBuilder<DataTable, String> updateBuilder;

	/**
	 * set start connection source to be null
	 */
	private UserConnectData() {
		try {
			connectionSource = new JdbcConnectionSource(URL, NAME, PW);
			userDao = DaoManager.createDao(connectionSource, DataTable.class);
			updateBuilder = userDao.updateBuilder();
		} catch (SQLException e1) {
			System.out.println("create connection error");
			e1.printStackTrace();
		}
	}

	public static UserConnectData getInstance() {
		if (databaseConnect == null)
			databaseConnect = new UserConnectData();
		return databaseConnect;
	}

	public void closeConnect() throws IOException {
		connectionSource.close();
	}

	public List<DataTable> pullAllUserdata() {
		try {
			getDetailUser = userDao.queryForAll();
		} catch (SQLException e) {
			System.out.println("pull data error");
		}
		return getDetailUser;
	}

	public boolean isUserExist(String username) {
		DataTable userTable = null;
		System.out.println("Username: "+username);
		try {
			userTable = userDao.queryForId(username);

		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("User exist error");
		}
		return userTable != null;
	}

	public void createUser(DataTable userToAdd) {
		try {
			userDao.createIfNotExists(userToAdd);
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("create user error");
		}
	}
	
	public void updateUserData(String user, int score){
		try {
			// find target row
			updateBuilder.where().eq("Username", user).and().le("highScore", score);
			// update values
			updateBuilder.updateColumnValue("highScore", score);
			updateBuilder.update();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
