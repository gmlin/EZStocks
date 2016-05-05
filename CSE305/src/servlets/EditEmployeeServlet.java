package servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import beans.AccountStock;
import beans.Client;
import beans.Employee;
import beans.Stock;
import beans.User;
import dao.AccountDAO;
import dao.AccountStockDAO;
import dao.ClientDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import dao.StockDAO;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/edit_employee")
public class EditEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AccountDAO accountDAO;
	private EmployeeDAO employeeDAO;

	public void init() {
		userDAO = new UserDAO();
		accountDAO = new AccountDAO();
		employeeDAO = new EmployeeDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String role = (String) session.getAttribute("role");
		User user = userDAO.getUser(username, password);
		int id = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher rd;
		if (user != null && role.equals("Manager")) {
			User u = userDAO.getUser(id);
			Employee employee = employeeDAO.getEmployee(id);
			request.setAttribute("user", u);
			request.setAttribute("employee", employee);
			rd = request.getRequestDispatcher("edit_employee.jsp");
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			rd = request.getRequestDispatcher("index.jsp");
		}
		rd.forward(request, response);
	}
		
}
