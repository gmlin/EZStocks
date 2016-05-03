package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Account;
import beans.Client;
import beans.Order;
import beans.Transaction;
import beans.User;

public class TransactionDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public Transaction getTransaction(int order) {
		String query = "SELECT * FROM Transaction WHERE `order`=" + order;
		Transaction transaction = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				transaction = new Transaction();
				transaction.setOrder(order);
				transaction.setFee(rs.getDouble("Fee"));
				transaction.setPricePerShare(rs.getDouble("PricePerShare"));
				transaction.setDateTime(rs.getTimestamp("DateTime"));
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
		return transaction;
	}
	
}
