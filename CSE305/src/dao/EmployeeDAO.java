package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
import beans.Employee;
import beans.User;

public class EmployeeDAO {

	private Connection connection;
	private Statement statement;
	private ResultSet rs;
	
	public Employee getEmployee(int id) {
		String query = "SELECT * FROM employee WHERE id=" + id;
		Employee employee = null;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				employee = new Employee();
				employee.setId(id);
				employee.setStartDate(rs.getDate("StartDate"));
				employee.setHourlyRate(rs.getDouble("HourlyRate"));
				employee.setType(rs.getString("Type"));
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
		return employee;
	}
	
	public List<Employee> getEmployees() {
		String query = "SELECT * FROM Employee";
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee;
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				employee = new Employee();
				employee.setId(rs.getInt("Id"));
				employee.setHourlyRate(rs.getDouble("HourlyRate"));
				employee.setStartDate(rs.getDate("StartDate"));
				employee.setType(rs.getString("Type"));
				employees.add(employee);
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
		return employees;
	}
	
	public boolean addEmployee(String username, String password, int ssn, String first, String last,
			String address, String city, String state, int zip, long phone, double hourlyRate, String empRole) {
		String query = "INSERT INTO Employee (Id, StartDate, HourlyRate, Type) VALUES ("
				+ ssn + ", CURDATE()," + hourlyRate + ",'" + empRole + "')";
		try {
			connection = ConnectionManager.createConnection();
			connection.setAutoCommit(false);
			UserDAO userDAO = new UserDAO();
			userDAO.addUser(connection, username, password, ssn, last, first, address, city, state, zip, phone);
			statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
}
