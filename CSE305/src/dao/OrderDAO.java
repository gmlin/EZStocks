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
		if (priceType.equals("HiddenStop")) {
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
		Connection conn = null;;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			conn = connection;
			if (status.equals("Approved"))
				triggerOrder(connection, Integer.parseInt(orderId));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void triggerOrder(Connection conn, int orderId) {
		Order order = getOrder(orderId);
		StockDAO stockDAO = new StockDAO();
		Stock stock = stockDAO.getStock(order.getStock());
		String query = "";
		String query2 = "";
		if (order.getOrderType().equals("Buy")) {
			if (order.getPriceType().equals("Market")
					|| (order.getPriceType().equals("HiddenStop") 
							&& order.getPricePerShare() >= stock.getPricePerShare())) {
				query = "UPDATE Client SET Rating=Rating+1 WHERE Id=" + order.getClient();
				query2 = "UPDATE `order` SET " + "status='Completed' WHERE Id=" + orderId;
				TransactionDAO transactionDAO = new TransactionDAO();
				transactionDAO.addTransaction(conn, order, stock);
				AccountStockDAO accountStockDAO = new AccountStockDAO();
				accountStockDAO.updatePortfolio(conn, order.getClient(), order.getAccountNum(), stock.getSymbol(), order.getNumShares(), order.getOrderType());
			}
		}
		else {
			if (order.getPriceType().equals("Market")
					|| (order.getPriceType().equals("HiddenStop") 
							&& order.getPricePerShare() <= stock.getPricePerShare())) {
				query = "UPDATE Client SET Rating=Rating+1 WHERE Id=" + order.getClient();
				query2 = "UPDATE `order` SET " + "status='Completed' WHERE Id=" + orderId;
				TransactionDAO transactionDAO = new TransactionDAO();
				transactionDAO.addTransaction(conn, order, stock);
				AccountStockDAO accountStockDAO = new AccountStockDAO();
				accountStockDAO.updatePortfolio(conn, order.getClient(), order.getAccountNum(), stock.getSymbol(), order.getNumShares(), order.getOrderType());
			}
		}
		try {
			statement = conn.createStatement();
			if (!query.isEmpty()) {
				statement.executeUpdate(query);
				statement.executeUpdate(query2);
			}
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

	public void closeMarket() {
		String query = "SELECT * FROM `Order` WHERE Status='Approved' AND PriceType='MarketOnClose'";
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.executeQuery(query);

			List<Order> orders = new ArrayList<Order>();
			Order order;


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
			rs.close();
			for (Order o : orders) {
				Stock stock;
				query = "SELECT * FROM Stock WHERE Stock.symbol='" + o.getStock() + "'";
				rs = statement.executeQuery(query);
				rs.next();
				stock = new Stock();
				stock.setSymbol(rs.getString("Symbol"));
				stock.setCompany(rs.getString("Company"));
				stock.setType(rs.getString("Type"));
				stock.setPricePerShare(rs.getDouble("PricePerShare"));
				stock.setNumShares(rs.getInt("NumShares"));
				if (o.getOrderType().equals("Buy")) {

					query = "UPDATE `Order` SET Status='Completed' WHERE Id=" + o.getId();
					statement.executeUpdate(query);
					query = "INSERT INTO Transaction (`Order`, Fee, DateTime, PricePerShare) VALUES ("
							+ o.getId() + ", " + 0.05 * o.getNumShares() * stock.getPricePerShare()
							+ ", NOW(), " + stock.getPricePerShare() + ")";
					statement.executeUpdate(query);
					query = "UPDATE Client SET Rating=Rating+1 WHERE Id=" + o.getClient();
					statement.executeUpdate(query);
					query = "UPDATE AccountStock SET NumShares=NumShares+" + o.getNumShares()
					+ " WHERE Client=" + o.getClient() + " AND AccountNum=" + o.getAccountNum() + " AND Stock='" + o.getStock() +"'";
					statement.executeUpdate(query);
				}
				else {
					query = "UPDATE `Order` SET Status='Completed' WHERE Id=" + o.getId();
					statement.executeUpdate(query);
					query = "INSERT INTO Transaction (`Order`, Fee, DateTime, PricePerShare) VALUES ("
							+ o.getId() + ", " + 0.05 * o.getNumShares() * stock.getPricePerShare()
							+ ", NOW(), " + stock.getPricePerShare() + ")";
					statement.executeUpdate(query);
					query = "UPDATE Client SET Rating=Rating+1 WHERE Id=" + o.getClient();
					statement.executeUpdate(query);
					query = "UPDATE AccountStock SET NumShares=NumShares-" + o.getNumShares()
					+ " WHERE Client=" + o.getClient() + " AND AccountNum=" + o.getAccountNum() + " AND Stock='" + o.getStock() +"'";
					statement.executeUpdate(query);
				}
			}
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
}
