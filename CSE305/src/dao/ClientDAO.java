package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
