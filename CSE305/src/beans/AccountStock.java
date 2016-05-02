package beans;

public class AccountStock {
	private int client;
	private int accountNum;
	private String stock;
	private int numShares;

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
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getNumShares() {
		return numShares;
	}
	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}
}