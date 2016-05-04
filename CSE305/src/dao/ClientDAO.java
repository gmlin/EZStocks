package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
import beans.User;

public class ClientDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public Client getClient(int id) {
		String query = "SELECT * FROM client WHERE id=" + id;
		Client client = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				client = new Client();
				client.setId(id);
				client.setEmail(rs.getString("Email"));
				client.setCreditCard(rs.getLong("CC"));
				client.setRating(rs.getInt("Rating"));
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
		return client;
	}
	
	public List<Client> getClients() {
		String query = "SELECT * FROM client";
		List<Client> clients = new ArrayList<Client>();
		Client client;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				client = new Client();
				client.setId(rs.getInt("Id"));
				client.setEmail(rs.getString("Email"));
				client.setCreditCard(rs.getLong("CC"));
				client.setRating(rs.getInt("Rating"));
				clients.add(client);
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
		return clients;
	}

	public boolean addClient(String username, String password, int ssn, String first, String last,
			String address, String city, String state, int zip, long phone, String email, long cc) {
		String query = "INSERT INTO Client (Id, Email, Rating, CC) VALUES ("
				+ ssn + ",'" + email + "'," + 1 + "," + cc + ")";
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			UserDAO userDAO = new UserDAO();
			userDAO.addUser(connection, username, password, ssn, last, first, address, city, state, zip, phone);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
}
