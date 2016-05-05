package dao;
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
				user.setUsername(username);
				user.setPassword(password);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User getUser(int ssn) {
		String query = "SELECT * FROM user WHERE ssn=" + ssn;
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
				user.setSsn(ssn);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public void addUser(Connection conn, String username, String password, int ssn, String last, String first, String address,
			String city, String state, int zip, long phone) {
		String query = "INSERT INTO User (Username, Password, SSN, LastName, FirstName, Address, City, State, ZipCode, PhoneNumber) VALUES ('"
				+ username +"','" + password +"'," + ssn + ",'" + last + "','" + first + "','" + address + "','" + city + "','" + state + "'," + zip + "," + phone + ") "
						+ "ON DUPLICATE KEY UPDATE Username='" + username +"', Password='" + password + "', SSN=" + ssn + ", "
								+ "LastName='" + last + "', FirstName='" + first + "', Address='" + address + "', City='" + city + "', "
										+ "State='" + state + "', ZipCode=" + zip + ", PhoneNumber=" + phone;
		try {
			statement = conn.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public void deleteUser(int id) {
		String query = "DELETE FROM User WHERE SSN=" + id;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
