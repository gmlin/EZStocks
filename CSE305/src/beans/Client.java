package beans;

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
}
