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
	
	public List<AccountStock> getAccountStocks(int client, int accountNum) {
		String query = "SELECT * FROM accountstock WHERE client=" + 
				client + " AND accountNum=" + accountNum;
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
				accountStock.setAccountNum(accountNum);
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

	public void updatePortfolio(Connection conn, int client, int accountNum, String symbol, int numShares, String orderType) {
		String query = "SELECT * FROM AccountStock WHERE client=" + client + " AND stock='" + symbol + "' "
				+ "AND AccountNum=" + accountNum;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				if (orderType.equals("Buy")) {
				query = "UPDATE AccountStock SET NumShares=NumShares+" + numShares
						+ " WHERE Client=" + client + " AND AccountNum=" + accountNum + " AND Stock='" + symbol +"'";
				}
				else {
					query = "UPDATE AccountStock SET NumShares=NumShares-" + numShares
							+ " WHERE Client=" + client + " AND AccountNum=" + accountNum + " AND Stock='" + symbol +"'";
				}
			}
			else {
				if (orderType.equals("Buy")) {
				query = "INSERT INTO AccountStock (Client, AccountNum, Stock, NumShares) VALUES (" +
						client + "," + accountNum + ",'" + symbol + "'," + numShares + ")";
				}
			}
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
