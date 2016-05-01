import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Account;
import beans.Client;
import beans.User;

public class AccountDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public List<Account> getAccounts(Client client) {
		String query = "SELECT * FROM Account WHERE client=" + client.getId();
		List<Account> accounts = new ArrayList<Account>();
		Account account;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				account = new Account();
				account.setClient(client);
				account.setAccountNum(rs.getInt("AccountNum"));
				account.setDateOpened(rs.getDate("DateOpened"));
				accounts.add(account);
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
		return accounts;
	}
	
	public Account getAccount(Client client, int accountNum) {
		String query = "SELECT * FROM Account WHERE client=" + client.getId()
			+ " AND accountNum=" + accountNum;
		Account account = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				account = new Account();
				account.setClient(client);
				account.setAccountNum(rs.getInt("AccountNum"));
				account.setDateOpened(rs.getDate("DateOpened"));
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
		return account;
	}
}
