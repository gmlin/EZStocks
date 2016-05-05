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
import beans.Stock;
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

	public double getProfits(int month, int year) {
		String query = "SELECT SUM(Fee) FROM Transaction WHERE MONTH(DateTime)=" +
				month + " AND YEAR(DateTime)=" + year;
		double profit = 0;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				profit = rs.getDouble(1);
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
		return profit;
	}
	
	public void addTransaction(Connection conn, Order order, Stock stock) {
		String query = "INSERT INTO Transaction (`Order`, Fee, DateTime, PricePerShare) VALUES ("
				+ order.getId() + ", " + 0.05 * order.getNumShares() * stock.getPricePerShare()
				+ ", NOW(), " + stock.getPricePerShare() + ")";
		try {
			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}
