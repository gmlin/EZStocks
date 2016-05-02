package beans;

public class Order {
	private String symbol;
	private String company;
	private String type;
	private double pricePerShare;
	private int numShares;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPricePerShare() {
		return pricePerShare;
	}
	public void setPricePerShare(double pricePerShare) {
		this.pricePerShare = pricePerShare;
	}
	public int getNumShares() {
		return numShares;
	}
	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}
	
}
