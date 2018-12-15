package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDAO;
import dao.UserDAO;
import model.Review;

// Entry point to a user's public profile. Called every time someone clicks on a username.
@WebServlet("/LoadProfile")
public class LoadProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoadProfile() {
        super();
    }

    // Using GET because it is an idempotent request and no changes are being written to server
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		// User profile will have the format ?userId=
		long userId = Long.parseLong(request.getParameter("userId"));		
		// ArrayList<Posting> postings = PostingDAO.getPostingsByUserId(userId);
		ArrayList<Review> reviews = ReviewDAO.getReviewsByForUserId(userId);
	}
}
