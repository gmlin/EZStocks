

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Employee;
import beans.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private ClientDAO clientDAO;
	private EmployeeDAO employeeDAO;

	public void init() {
		userDAO = new UserDAO();
		clientDAO = new ClientDAO();
		employeeDAO = new EmployeeDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDAO.getUser(username, password);
		RequestDispatcher rd;
		if (user != null) {
			session.setAttribute("user", user);
			request.setAttribute("message", "You have logged in.");
			Client client = clientDAO.getClient(user.getSsn());
			if (client != null) {
				session.setAttribute("role", "Client");
				session.setAttribute("client", client);
			}
			else {
				Employee employee = employeeDAO.getEmployee(user.getSsn());
				session.setAttribute("role", employee.getType());
				session.setAttribute("employee", employee);
			}
			rd = request.getRequestDispatcher("index.jsp");
		}
		else {
			request.setAttribute("message", "Could not log in.");
			rd = request.getRequestDispatcher("login.jsp");
		}
		rd.forward(request, response);
	}

}
