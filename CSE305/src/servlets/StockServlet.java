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
import dao.StockDAO;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/stocks")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private StockDAO stockDAO;

	public void init() {
		userDAO = new UserDAO();
		stockDAO = new StockDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String role = (String) session.getAttribute("role");
		String type = (String) request.getParameter("type");
		String keyword = (String) request.getParameter("keyword");
		User user = userDAO.getUser(username, password);
		RequestDispatcher rd;
		if (user != null) {
			List<Stock> stocks; 
			if (type != null) {
				stocks = stockDAO.getTypeStocks(type);
			}
			else if (keyword != null) {
				stocks = stockDAO.getKeywordStocks(keyword);
			}
			else {
				stocks = stockDAO.getStocks();
			}
			List<Stock> bestSellers = stockDAO.getBestSellers();
			if (role.equals("Client")) {
				List<Stock> recommendations = stockDAO.getRecommendations(user.getSsn());
				request.setAttribute("recommendations", recommendations);
			}
			request.setAttribute("stocks", stocks);
			request.setAttribute("bestSellers", bestSellers);
			rd = request.getRequestDispatcher("stocks.jsp");
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			rd = request.getRequestDispatcher("index.jsp");
		}
		rd.forward(request, response);
	}

}
