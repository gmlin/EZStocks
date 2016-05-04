package servlets;


import java.io.IOException;
import java.sql.Timestamp;
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
import beans.StockPrice;
import beans.User;
import dao.AccountDAO;
import dao.AccountStockDAO;
import dao.ClientDAO;
import dao.EmployeeDAO;
import dao.StockDAO;
import dao.StockPriceDAO;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/stockHistory")
public class StockPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private StockDAO stockDAO;
	private StockPriceDAO stockPriceDAO;

	public void init() {
		userDAO = new UserDAO();
		stockDAO = new StockDAO();
		stockPriceDAO = new StockPriceDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String symbol = (String) request.getParameter("stock");
		String start = (String) request.getParameter("start");
		User user = userDAO.getUser(username, password);
		RequestDispatcher rd;
		Stock stock = null;
		if (user != null) {
			stock = stockDAO.getStock(symbol);
		}
		if (stock != null) {
			List<StockPrice> stockPrices;
			if (start != null) {
				start = start.replace("T", " ") + ":00";
				Timestamp startDateTime = Timestamp.valueOf(start);
				stockPrices = stockPriceDAO.getStockPrices(symbol, startDateTime);
			}
			else {
				stockPrices = stockPriceDAO.getStockPrices(symbol);
			}
			request.setAttribute("stockPrices", stockPrices);
			request.setAttribute("stock", symbol);
			rd = request.getRequestDispatcher("stockHistory.jsp");
		}
		else {
			request.setAttribute("message", "Something went wrong.");
			rd = request.getRequestDispatcher("index.jsp");
		}
		rd.forward(request, response);
	}

}
