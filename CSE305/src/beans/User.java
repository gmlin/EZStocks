package beans;

import dao.ClientDAO;
import dao.EmployeeDAO;

public class User {
	private String username;
	private String password;
	private int ssn;
	private String lastName;
	private String firstName;
	private String address;
	private String city;
	private String state;
	private int zipCode;
	private long phoneNumber;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRole() {
		ClientDAO clientDAO = new ClientDAO();
		if (clientDAO.getClient(ssn) != null) {
			return "Client";
		}
		else {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			Employee employee = employeeDAO.getEmployee(ssn);
			return employee.getType();
		}
	}
}
