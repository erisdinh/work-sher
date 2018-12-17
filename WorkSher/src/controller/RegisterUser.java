package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get parameters from the registration form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String url;
		
		// If the username is not taken
		if (!UserDAO.checkUsername(username)) {
			// Store the data in a User object
			User user = new User(username, password, name, email);

			// Add to database
			UserDAO.addUser(user);
			
			// Log user in after they have registered
			url = "Login";
			
		// Take them back to register if the username is taken
		} else {
			url = "register.jsp";
			String feedback = "ERROR: That username is already taken. Please choose another one.";
			request.setAttribute("feedback", feedback);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
