package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		String feedback = "";
		String url;
		
		// Check if the username and matching password are in the database
		User user = UserDAO.authenticateUser(username, password);
		
		// If login is valid
		if (user != null) {
			
			// Check if user is trying to access a deleted account
			if (user.getRole().equalsIgnoreCase("deleted")) {
				feedback = "ERROR: This account has been deleted. Please contact an administrator to reinstate it.";
				url = "login.jsp";
				
			} else { // If account exists and has not been deleted
				// Create a new session and associate it with the user
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", user);	
				url = "homepage.jsp";
			}	
		// If invalid, set error message to display on page
		} else {
			feedback = "ERROR: Incorrect username or password.";
			url = "login.jsp";
		}		
		request.setAttribute("feedback", feedback);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
