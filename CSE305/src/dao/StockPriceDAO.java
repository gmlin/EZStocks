package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
import beans.Stock;
import beans.StockPrice;
import beans.User;

public class StockPriceDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public List<StockPrice> getStockPrices(String stock) {
		String query = "SELECT * FROM stockHistory WHERE stock='" + stock + "'";
		List<StockPrice> stockPrices = new ArrayList<StockPrice>();
		StockPrice stockPrice;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				stockPrice = new StockPrice();
				stockPrice.setStock(stock);
				stockPrice.setDateTime(rs.getTimestamp("DateTime"));
				stockPrice.setPrice(rs.getDouble("Price"));
				stockPrices.add(stockPrice);
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
		return stockPrices;
	}
}
