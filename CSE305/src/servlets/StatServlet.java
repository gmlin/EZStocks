package servlets;


import java.io.IOException;
import java.util.ArrayList;
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
import dao.StockDAO;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/stats")
public class StatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private StockDAO stockDAO;
	private EmployeeDAO employeeDAO;
	private ClientDAO clientDAO;

	public void init() {
		userDAO = new UserDAO();
		stockDAO = new StockDAO();
		employeeDAO = new EmployeeDAO();
		clientDAO = new ClientDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String role = (String) session.getAttribute("role");
		User user = userDAO.getUser(username, password);
		RequestDispatcher rd;
		if (user != null && role.equals("Manager")) {
			Employee topBroker = employeeDAO.getTopBroker();
			Client topCustomer = clientDAO.getTopCustomer();
			List<Stock> bestSellers = stockDAO.getMostTraded();
			request.setAttribute("broker", topBroker);
			request.setAttribute("customer", topCustomer);
			request.setAttribute("bestSellers", bestSellers);
			rd = request.getRequestDispatcher("stats.jsp");
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			rd = request.getRequestDispatcher("index.jsp");
		}
		rd.forward(request, response);
	}

}
