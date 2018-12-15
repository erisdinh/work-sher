package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

// Entry point to a user's public profile. Called every time someone clicks on a username.
@WebServlet("/LoadProfile")
public class LoadProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoadProfile() {
        super();
    }

    // Using GET because it is an idempotent request and no changes are being written to server
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// User profile will have the format ?u=username
		// Map the username to the userId number to get the information associated with that user
		String username = request.getParameter("u");
		int userId = UserDAO.getIdOfUser(username);
		
		// Determine if current user is looking at their own profile or someone else's
		// Profile will show user's postings, and reviews left for them
		// getReviewsbyUserId
		
	}
}
