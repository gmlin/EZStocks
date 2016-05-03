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
import beans.Transaction;
import beans.User;
import dao.AccountDAO;
import dao.AccountStockDAO;
import dao.ClientDAO;
import dao.EmployeeDAO;
import dao.OrderDAO;
import dao.TransactionDAO;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AccountDAO accountDAO;
	private OrderDAO orderDAO;
	private TransactionDAO transactionDAO;

	public void init() {
		userDAO = new UserDAO();
		accountDAO = new AccountDAO();
		orderDAO = new OrderDAO();
		transactionDAO = new TransactionDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		int orderId = Integer.parseInt(request.getParameter("order"));
		User user = userDAO.getUser(username, password);
		Order order = null;
		if (user != null) {
			order = orderDAO.getOrder(orderId);
		}
		Transaction transaction = null;
		if (order != null && order.getClient() == user.getSsn()) {
			transaction = transactionDAO.getTransaction(orderId);
		}
		RequestDispatcher rd;
		if (transaction != null) {
			request.setAttribute("transaction", transaction);
			rd = request.getRequestDispatcher("transaction.jsp");
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			rd = request.getRequestDispatcher("index.jsp");
		}
		rd.forward(request, response);
	}

}
