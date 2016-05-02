package beans;

import java.sql.Date;
import java.util.List;

import dao.AccountStockDAO;

public class Account {
	private int client;
	private int accountNum;
	private Date dateOpened;
	
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public Date getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}
	public List<AccountStock> getAccountStocks() {
		AccountStockDAO accountStockDAO = new AccountStockDAO();
		return accountStockDAO.getAccountStocks(client);
	}
}
