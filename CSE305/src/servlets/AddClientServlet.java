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
@WebServlet("/add_client")
public class AddClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AccountDAO accountDAO;
	private OrderDAO orderDAO;
	private ClientDAO clientDAO;

	public void init() {
		userDAO = new UserDAO();
		accountDAO = new AccountDAO();
		orderDAO = new OrderDAO();
		clientDAO = new ClientDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String role = (String) session.getAttribute("role");
		String clientUsername = request.getParameter("username");
		String clientPassword = request.getParameter("password");
		int ssn = Integer.parseInt(request.getParameter("ssn"));
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zip = Integer.parseInt(request.getParameter("zip"));
		long phone = Long.parseLong(request.getParameter("phone"));
		String email = request.getParameter("email");
		long cc = Long.parseLong(request.getParameter("cc"));
		
		User user = userDAO.getUser(username, password);
		if (user != null && !role.equals("Client")) {
			if (clientDAO.addClient(clientUsername, clientPassword, ssn, 
					first, last, address, city, state, zip, phone, email, cc)) {
				request.setAttribute("message", "Client added.");
				response.sendRedirect("clients");
			}
			else {
				request.setAttribute("message", "Could not add new client.");
				response.sendRedirect("clients");
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
