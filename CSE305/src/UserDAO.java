import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.User;

public class UserDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public User getUser(String username, String password) {
		String query = "SELECT * FROM user WHERE username='" + username
				+ "' AND password='" + password + "'";
		User user = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setSsn(rs.getInt("SSN"));
				user.setLastName(rs.getString("LastName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setAddress(rs.getString("Address"));
				user.setCity(rs.getString("City"));
				user.setState(rs.getString("State"));
				user.setZipCode(rs.getInt("ZipCode"));
				user.setPhoneNumber(rs.getLong("PhoneNumber"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
}
