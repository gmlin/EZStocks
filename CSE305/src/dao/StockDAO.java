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
		String query = "SELECT * FROM stock WHERE symbol=" + symbol;
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
}
