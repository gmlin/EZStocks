package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Account;
import beans.AccountStock;
import beans.Client;
import beans.Stock;
import beans.User;

public class AccountStockDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public List<AccountStock> getAccountStocks(int client) {
		String query = "SELECT * FROM accountstock WHERE client=" + 
				client;
		List<AccountStock> accountStocks = new ArrayList<AccountStock>();
		AccountStock accountStock;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				accountStock = new AccountStock();
				accountStock.setClient(client);
				accountStock.setAccountNum(rs.getInt("AccountNum"));
				accountStock.setStock(rs.getString("Stock"));
				accountStock.setNumShares(rs.getInt("NumShares"));
				accountStocks.add(accountStock);
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
		return accountStocks;
	}
}
