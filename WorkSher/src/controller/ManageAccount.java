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

@WebServlet("/ManageAccount")
public class ManageAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String feedback;
	
    public ManageAccount() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("currentUser");		
		String username = currentUser.getUsername();
		String password = request.getParameter("currpassword");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
			
		// If name, email fields are empty
		if (name == "") {
			name = currentUser.getName();
		}
		if (email == "") {
			email = currentUser.getEmail();
		}
		
		// Find user in the database
		User user = UserDAO.authenticateUser(username, password);
				
		if (user != null) {
			feedback = "Account updated successfully";
			// Update current user information in session attribute
			currentUser.setName(name);
			currentUser.setEmail(email);
			currentUser.setPassword(request.getParameter("newpassword"));
			
			// Update current user information in database
			UserDAO.updateUser(currentUser);
		} else {
			feedback = "ERROR: Incorrect password";
		}
		
		request.setAttribute("feedback", feedback);
		RequestDispatcher rd = request.getRequestDispatcher("accountsettings.jsp");
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		
		// If user is deleting their own account
		if (action.equalsIgnoreCase("delete")) {
			UserDAO.deleteUser(user);
			session.removeAttribute("currentUser");
			feedback = "Thank you for using WorkSher. Your account has been deleted.";
		
		// If admin is deleting another user
		} else if (action.equalsIgnoreCase("admdelete") && UserDAO.authorizeUser(user)) {
			long userId = Long.parseLong(request.getParameter("userId"));
			user = UserDAO.getUserById(userId);
			UserDAO.deleteUser(user);
		}
		
		request.setAttribute("feedback", feedback);
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
}
