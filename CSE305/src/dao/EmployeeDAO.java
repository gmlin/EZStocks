package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
