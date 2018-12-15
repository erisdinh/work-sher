package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostingDAO;
import dao.ReviewDAO;
import dao.UserDAO;
import model.Posting;
import model.Review;
import model.User;

// Entry point to a user's public profile. Called every time someone clicks on a username.
@WebServlet("/LoadProfile")
public class LoadProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoadProfile() {
        super();
    }

    // Using GET because it is an idempotent request and no changes are being written to server
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		// Link will have the format "LoadProfile?userId="
		long userId = Long.parseLong(request.getParameter("userId"));
		User user = UserDAO.getUserById(userId);
		ArrayList<Posting> postings = (ArrayList<Posting>) PostingDAO.getPostingsByUserId(userId);
		ArrayList<Review> reviews = ReviewDAO.getReviewsByForUserId(userId);
		
		request.setAttribute("user", user);
		request.setAttribute("postings", postings);
		request.setAttribute("reviews", reviews);
		
		RequestDispatcher rd = request.getRequestDispatcher("LoadReviews");
		rd.forward(request, response);		
	}
}
