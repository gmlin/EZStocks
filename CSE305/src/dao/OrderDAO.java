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
	
	public boolean createOrder(int client, int account, String stock, int numShares, Double hiddenPrice, Double trailing, String priceType, String type) {
		Double price = hiddenPrice;
		String t;
		if (type.equals("buy")) {
			t = "Buy";
		}
		else t = "Sell";
		if (priceType.equals("Market") || type.equals("MarketOnClose")) {
			StockDAO stockDAO = new StockDAO();
			price = stockDAO.getStock(stock).getPricePerShare();
		}
		String query = "INSERT INTO `Order` (Client, AccountNum, Stock, Employee, NumShares, "
				+ "DateTime, PricePerShare, Percentage, PriceType, OrderType, Status) VALUES ("
				+ client + ", " + account + ", '" + stock + "', NULL, " + numShares + ", "
						+ "now(), " + price + ", " + trailing + ", '" + priceType + "', '" + t + "', 'Pending')"; 
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public List<Order> getPendingOrders() {
		String query = "SELECT * FROM `Order` WHERE status='Pending'";
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
				order.setClient(rs.getInt("client"));
				order.setAccountNum(rs.getInt("accountNum"));
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

	public List<Order> getOrders() {
		String query = "SELECT * FROM `Order`";
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
				order.setClient(rs.getInt("client"));
				order.setAccountNum(rs.getInt("accountNum"));
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
	
	public void setStatus(int employee, String orderId, String status) {
		String query = "UPDATE `order` SET " + "status='" + status + "', employee=" + employee
				+ " WHERE id=" + orderId;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Order> getStockOrders(String stock) {
		String query = "SELECT * FROM `Order` WHERE stock='" + stock + "'";
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
	
	public List<Order> getClientOrders(String first, String last) {
		String query = "SELECT `Order`.* FROM `Order` INNER JOIN User "
				+ "ON `Order`.client=User.ssn "
				+ "WHERE User.firstName='" + first + "'"
				+ " AND User.lastName='" + last + "'";
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
}
