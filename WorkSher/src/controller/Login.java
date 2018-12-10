package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters from the login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		// Check if the username and matching password are in the database
		User user = UserDAO.authenticateUser(username, password);
		
		// If login is valid, action to be taken
		if (user != null) {
			// Create a new session and associate it with the user
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", user);	
			response.sendRedirect("homepage.jsp");
			
		// If invalid, go to registration page
		} else {
			response.sendRedirect("register.jsp");
		}
	}
}
