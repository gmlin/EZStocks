package beans;

import java.util.List;

import dao.AccountDAO;
import dao.ClientDAO;
import dao.UserDAO;

public class Client {
	private int id;
	private String email;
	private long creditCard;
	private int rating;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getFirstName() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(id);
		return user.getFirstName();
	}
	public String getLastName() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(id);
		return user.getLastName();
	}
	public String getName() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(id);
		return user.getFirstName() + " " + user.getLastName();
	}
	public String getUsername() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(id);
		return user.getUsername();
	}
	public double getRevenue() {
		ClientDAO clientDAO= new ClientDAO();
		return clientDAO.getClientRevenue(getFirstName(), getLastName());
	}
	public int getNumAcc() {
		AccountDAO accDAO = new AccountDAO();
		List <Account> accs = accDAO.getAccounts(id);
		return accs.size();
	}
}
