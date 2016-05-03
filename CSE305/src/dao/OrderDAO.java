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
import beans.User;

public class OrderDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public List<Order> getOrders(int client, int accountNum) {
		String query = "SELECT * FROM `Order` WHERE client=" + client
				+ " AND accountNum=" + accountNum;
		List<Order> orders = new ArrayList<Order>();
		Order order;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setClient(client);
				order.setAccountNum(accountNum);
				order.setStock(rs.getString("Stock"));
				order.setEmployee(rs.getInt("Employee"));
				order.setNumShares(rs.getInt("NumShares"));
				order.setDateTime(rs.getTimestamp("DateTime"));
				order.setPricePerShare(rs.getDouble("PricePerShare"));
				order.setPercentage(rs.getDouble("Percentage"));
				order.setPriceType(rs.getString("PriceType"));
				order.setOrderType(rs.getString("OrderType"));
				order.setStatus(rs.getString("Status"));
				orders.add(order);
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
		return orders;
	}
	
	public Order getOrder(int id) {
		String query = "SELECT * FROM `Order` WHERE id=" + id;
		Order order = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setClient(rs.getInt("Client"));
				order.setAccountNum(rs.getInt("AccountNum"));
				order.setStock(rs.getString("Stock"));
				order.setEmployee(rs.getInt("Employee"));
				order.setNumShares(rs.getInt("NumShares"));
				order.setDateTime(rs.getTimestamp("DateTime"));
				order.setPricePerShare(rs.getDouble("PricePerShare"));
				order.setPercentage(rs.getDouble("Percentage"));
				order.setPriceType(rs.getString("PriceType"));
				order.setOrderType(rs.getString("OrderType"));
				order.setStatus(rs.getString("Status"));
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
		return order;
	}
	
}
