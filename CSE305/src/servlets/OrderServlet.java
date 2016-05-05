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
import beans.Order;
import beans.User;
import dao.AccountDAO;
import dao.AccountStockDAO;
import dao.ClientDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = {"/orders", "/manager_orders"})
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AccountDAO accountDAO;
	private OrderDAO orderDAO;

	public void init() {
		userDAO = new UserDAO();
		accountDAO = new AccountDAO();
		orderDAO = new OrderDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String role = (String) session.getAttribute("role");
		String stock = (String) request.getParameter("stock");
		String first = (String) request.getParameter("first");
		String last = (String) request.getParameter("last");
		User user = userDAO.getUser(username, password);
		Account account = null;
		int accountNum = 0;
		if (user != null && role.equals("Client")) {
			accountNum = Integer.parseInt(request.getParameter("account"));
			account = accountDAO.getAccount(user.getSsn(), accountNum);
		}
		RequestDispatcher rd;
		if (role.equals("Client") && account != null) {
			List<Order> orders = orderDAO.getOrders(user.getSsn(), accountNum);
			request.setAttribute("orders", orders);
			request.setAttribute("accountNum", accountNum);
			rd = request.getRequestDispatcher("orders.jsp");
		}
		else if (role.equals("Manager")) {
			List<Order> orders;
			if (stock != null) {
				orders = orderDAO.getStockOrders(stock);
			}
			else if (first != null) {
				orders = orderDAO.getClientOrders(first, last);
			}
			else {
				orders = orderDAO.getOrders();
			}
			request.setAttribute("orders", orders);
			rd = request.getRequestDispatcher("orders.jsp");
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			rd = request.getRequestDispatcher("index.jsp");
		}
		rd.forward(request, response);
	}

}
