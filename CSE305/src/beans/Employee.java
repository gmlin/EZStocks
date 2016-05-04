package beans;

import java.sql.Date;

import dao.UserDAO;

public class Employee {
	private int id;
	private Date startDate;
	private double hourlyRate;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
