package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
import beans.Order;
import beans.Stock;
import beans.User;

public class StockDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;

	public Stock getStock(String symbol) {
		String query = "SELECT * FROM stock WHERE symbol='" + symbol + "'";
		Stock stock = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				stock = new Stock();
				stock.setSymbol(symbol);
				stock.setCompany(rs.getString("Company"));
				stock.setType(rs.getString("Type"));
				stock.setPricePerShare(rs.getDouble("PricePerShare"));
				stock.setNumShares(rs.getInt("NumShares"));
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
		return stock;
	}

	public List<Stock> getStocks() {
		String query = "SELECT * FROM stock";
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				stock = new Stock();
				stock.setSymbol(rs.getString("Symbol"));
				stock.setCompany(rs.getString("Company"));
				stock.setType(rs.getString("Type"));
				stock.setPricePerShare(rs.getDouble("PricePerShare"));
				stock.setNumShares(rs.getInt("NumShares"));
				stocks.add(stock);
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
		return stocks;
	}

	public List<Stock> getTypeStocks(String type) {
		String query = "SELECT * FROM stock WHERE type='" + type + "'";
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				stock = new Stock();
				stock.setSymbol(rs.getString("Symbol"));
				stock.setCompany(rs.getString("Company"));
				stock.setType(type);
				stock.setPricePerShare(rs.getDouble("PricePerShare"));
				stock.setNumShares(rs.getInt("NumShares"));
				stocks.add(stock);
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
		return stocks;
	}

	public List<Stock> getKeywordStocks(String keyword) {
		String query = "SELECT * FROM stock WHERE company LIKE '%" + keyword + "%'";
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				stock = new Stock();
				stock.setSymbol(rs.getString("Symbol"));
				stock.setCompany(rs.getString("Company"));
				stock.setType(rs.getString("Type"));
				stock.setPricePerShare(rs.getDouble("PricePerShare"));
				stock.setNumShares(rs.getInt("NumShares"));
				stocks.add(stock);
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
		return stocks;
	}

	public List<Stock> getBestSellers() {
		String query = "SELECT DISTINCT stock.* FROM `order` INNER JOIN stock "
				+ "ON stock.symbol = `order`.stock "
				+ "WHERE status='Completed' AND orderType='Buy' "
				+ "GROUP BY stock.symbol ORDER BY SUM(`order`.numShares) DESC LIMIT 5";
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				stock = new Stock();
				stock.setSymbol(rs.getString("Symbol"));
				stock.setCompany(rs.getString("Company"));
				stock.setType(rs.getString("Type"));
				stock.setPricePerShare(rs.getDouble("PricePerShare"));
				stock.setNumShares(rs.getInt("NumShares"));
				stocks.add(stock);
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
		return stocks;
	}

	public List<Stock> getRecommendations(int client) {
		String query = "SELECT DISTINCT stockA.* FROM Stock stockA INNER JOIN Stock stockB INNER JOIN `Order` "
				+ "ON stockA.type = stockB.type AND stockB.symbol = `Order`.stock "
				+ "WHERE `Order`.client=" + client + " LIMIT 5";
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				stock = new Stock();
				stock.setSymbol(rs.getString("Symbol"));
				stock.setCompany(rs.getString("Company"));
				stock.setType(rs.getString("Type"));
				stock.setPricePerShare(rs.getDouble("PricePerShare"));
				stock.setNumShares(rs.getInt("NumShares"));
				stocks.add(stock);
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
		return stocks;
	}

	public int getNumSold(String symbol) {
		String query = "SELECT SUM(numShares) FROM `order` WHERE stock='" + symbol + "'"
				+ " AND status='Completed' AND orderType='Buy'";
		int result = 0;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}
	
	public int getNumTraded(String symbol) {
		String query = "SELECT SUM(numShares) FROM `order` WHERE stock='" + symbol + "'"
				+ " AND status='Completed'";
		int result = 0;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}
	
	public List<Stock> getMostTraded() {
		String query = "SELECT DISTINCT stock.* FROM `order` INNER JOIN stock "
				+ "ON stock.symbol = `order`.stock "
				+ "WHERE status='Completed' "
				+ "GROUP BY stock.symbol ORDER BY SUM(`order`.numShares) DESC LIMIT 5";
		List<Stock> stocks = new ArrayList<Stock>();
		Stock stock = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				stock = new Stock();
				stock.setSymbol(rs.getString("Symbol"));
				stock.setCompany(rs.getString("Company"));
				stock.setType(rs.getString("Type"));
				stock.setPricePerShare(rs.getDouble("PricePerShare"));
				stock.setNumShares(rs.getInt("NumShares"));
				stocks.add(stock);
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
		return stocks;
	}

	public double getStockRevenue(String stock) {
		String query = "SELECT SUM(Transaction.PricePerShare * NumShares + Fee) FROM "
				+ "`Order` INNER JOIN Transaction ON "
				+ "`Order`.Id=Transaction.`Order` "
				+ "WHERE `Order`.Stock='" + stock + "'";
		double result = 0;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				result = rs.getDouble(1);
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
		return result;
	}

	public double getStockTypeRevenue(String stockType) {
		String query = "SELECT SUM(Transaction.PricePerShare * `Order`.NumShares + Fee) FROM "
				+ "Stock INNER JOIN `Order` INNER JOIN Transaction ON "
				+ "Stock.Symbol=`Order`.Stock AND `Order`.Id=Transaction.`Order` "
				+ "WHERE Stock.Type='" + stockType + "'";
		double result = 0;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				result = rs.getDouble(1);
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
		return result;
	}

	public void setStockPrice(String stock, Double price) {
		String query = "UPDATE Stock SET PricePerShare=" + price + " WHERE "
				+ "Symbol='" + stock + "'";
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			query = "SELECT * FROM `Order` WHERE Status='Approved' AND Stock='" + stock + "'"
					+ " AND PriceType LIKE '%Stop%'";
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
				if (o.getOrderType().equals("Buy")) {
					if (o.getPriceType().equals("TrailingStop")) {
						if (o.getPricePerShare() > price) {
							query = "UPDATE `Order` SET PricePerShare=" + price + " WHERE Id=" + o.getId();
						}
						else {
							if (o.getPricePerShare() * (1 + (o.getPercentage() / 100)) <= price) {
								query = "UPDATE `Order` SET Status='Completed' WHERE Id=" + o.getId();
								statement.executeUpdate(query);
								query = "INSERT INTO Transaction (`Order`, Fee, DateTime, PricePerShare) VALUES ("
										+ o.getId() + ", " + 0.05 * o.getNumShares() * price
										+ ", NOW(), " + price + ")";
								statement.executeUpdate(query);
								query = "UPDATE Client SET Rating=Rating+1 WHERE Id=" + o.getClient();
								statement.executeUpdate(query);
								query = "UPDATE AccountStock SET NumShares=NumShares+" + o.getNumShares()
								+ " WHERE Client=" + o.getClient() + " AND AccountNum=" + o.getAccountNum() + " AND Stock='" + o.getStock() +"'";
							}
						}
					}
					else if (o.getPriceType().equals("HiddenStop")){
						if (o.getPricePerShare() >= price) {
							query = "UPDATE `Order` SET Status='Completed' WHERE Id=" + o.getId();
							statement.executeUpdate(query);
							query = "INSERT INTO Transaction (`Order`, Fee, DateTime, PricePerShare) VALUES ("
									+ o.getId() + ", " + 0.05 * o.getNumShares() * price
									+ ", NOW(), " + price + ")";
							statement.executeUpdate(query);
							query = "UPDATE Client SET Rating=Rating+1 WHERE Id=" + o.getClient();
							statement.executeUpdate(query);
							query = "UPDATE AccountStock SET NumShares=NumShares+" + o.getNumShares()
							+ " WHERE Client=" + o.getClient() + " AND AccountNum=" + o.getAccountNum() + " AND Stock='" + o.getStock() +"'";
						}
					}
				}
				else {
					if (o.getPriceType().equals("TrailingStop")) {
						if (o.getPricePerShare() < price) {
							query = "UPDATE `Order` SET PricePerShare=" + price + " WHERE Id=" + o.getId();
						}
						else {
							if (o.getPricePerShare() * (1 - (o.getPercentage() / 100)) >= price) {
								query = "UPDATE `Order` SET Status='Completed' WHERE Id=" + o.getId();
								statement.executeUpdate(query);
								query = "INSERT INTO Transaction (`Order`, Fee, DateTime, PricePerShare) VALUES ("
										+ o.getId() + ", " + 0.05 * o.getNumShares() * price
										+ ", NOW(), " + price + ")";
								statement.executeUpdate(query);
								query = "UPDATE Client SET Rating=Rating+1 WHERE Id=" + o.getClient();
								statement.executeUpdate(query);
								query = "UPDATE AccountStock SET NumShares=NumShares-" + o.getNumShares()
								+ " WHERE Client=" + o.getClient() + " AND AccountNum=" + o.getAccountNum() + " AND Stock='" + o.getStock() +"'";
							}
						}
					}
					else if (o.getPriceType().equals("HiddenStop")){
						if (o.getPricePerShare() <= price) {
							query = "UPDATE `Order` SET Status='Completed' WHERE Id=" + o.getId();
							statement.executeUpdate(query);
							query = "INSERT INTO Transaction (`Order`, Fee, DateTime, PricePerShare) VALUES ("
									+ o.getId() + ", " + 0.05 * o.getNumShares() * price
									+ ", NOW(), " + price + ")";
							statement.executeUpdate(query);
							query = "UPDATE Client SET Rating=Rating+1 WHERE Id=" + o.getClient();
							statement.executeUpdate(query);
							query = "UPDATE AccountStock SET NumShares=NumShares-" + o.getNumShares()
							+ " WHERE Client=" + o.getClient() + " AND AccountNum=" + o.getAccountNum() + " AND Stock='" + o.getStock() +"'";
						}
					}
				}
			}
			statement.executeUpdate(query);
			query = "INSERT INTO StockHistory (Stock, DateTime, Price) VALUES ('"
					+ stock + "', NOW(), " + price + ")";
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

}
