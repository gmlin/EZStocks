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
import beans.User;
import dao.AccountDAO;
import dao.AccountStockDAO;
import dao.ClientDAO;
import dao.EmployeeDAO;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/portfolio")
public class PortfolioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private AccountDAO accountDAO;
	private AccountStockDAO accountStockDAO;

	public void init() {
		userDAO = new UserDAO();
		accountDAO = new AccountDAO();
		accountStockDAO = new AccountStockDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String role = (String) session.getAttribute("role");
		int accountNum = Integer.parseInt(request.getParameter("account"));
		User user = userDAO.getUser(username, password);
		Account account = null;
		if (user != null) {
			account = accountDAO.getAccount(user.getSsn(), accountNum);
		}
		RequestDispatcher rd;
		if (role.equals("Client") && account != null) {
			List<AccountStock> accountStocks = accountStockDAO.getAccountStocks(user.getSsn(), accountNum);
			request.setAttribute("accountStocks", accountStocks);
			request.setAttribute("accountNum", accountNum);
			rd = request.getRequestDispatcher("portfolio.jsp");
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			rd = request.getRequestDispatcher("index.jsp");
		}
		rd.forward(request, response);
	}

}
