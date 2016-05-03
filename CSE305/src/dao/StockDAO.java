package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
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
		String query = "SELECT stock.* FROM `order` INNER JOIN stock "
				+ "ON stock.symbol = `order`.stock "
				+ "WHERE status='Completed' AND orderType='Buy' "
				+ "ORDER BY `order`.numShares DESC LIMIT 5";
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
		String query = "SELECT stockA.* FROM Stock stockA INNER JOIN Stock stockB INNER JOIN `Order` "
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
	
}
