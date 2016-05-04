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
@WebServlet("/create_order")
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AccountDAO accountDAO;
	private OrderDAO orderDAO;

	public void init() {
		userDAO = new UserDAO();
		accountDAO = new AccountDAO();
		orderDAO = new OrderDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String role = (String) session.getAttribute("role");
		String type = request.getParameter("type");
		String stock = request.getParameter("stock");
		int account = Integer.parseInt(request.getParameter("account"));
		String priceType = request.getParameter("priceType");
		int numShares = Integer.parseInt(request.getParameter("numShares"));
		Double hiddenPrice = null;
		if (!request.getParameter("hiddenprice").isEmpty()) {
			hiddenPrice = Double.parseDouble(request.getParameter("hiddenprice"));
		}
		Double trailing = null;
		if (!request.getParameter("trailing").isEmpty()) {
			trailing = Double.parseDouble(request.getParameter("trailing"));
		}
		User user = userDAO.getUser(username, password);
		if (user != null && role.equals("Client")) {
			if (orderDAO.createOrder(user.getSsn(), account, stock, numShares, hiddenPrice, trailing, priceType, type)) {
				request.setAttribute("message", "Order created");
				response.sendRedirect("orders?account=" + account);
			}
			else {
				request.setAttribute("message", "Could not make order.");
				response.sendRedirect("index.jsp");
			}
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			response.sendRedirect("index.jsp");
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String role = (String) session.getAttribute("role");
		String type = (String) request.getParameter("type");
		String stock = (String) request.getParameter("stock");
		String account = (String) request.getParameter("account");
		User user = userDAO.getUser(username, password);
		RequestDispatcher rd;
		if (user != null && role.equals("Client")) {
			if (type.equals("buy")) {
				List<Account> accounts = accountDAO.getAccounts(user.getSsn());
				request.setAttribute("accounts", accounts);
			}
			request.setAttribute("type", type);
			request.setAttribute("stock", stock);
			request.setAttribute("account", account);
			rd = request.getRequestDispatcher("create_order.jsp");
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			rd = request.getRequestDispatcher("index.jsp");
		}
		rd.forward(request, response);
	}
	
}
