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

@WebServlet("/LoadReviews")
public class LoadReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoadReviews() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		
		// TODO: Make sure these redirect properly
		// TODO: Get the userId from the session and the postingId from the request
		
		ArrayList<Review> reviews = null;
		
		String forwardUrl = null;
		
		
		if (referer.contains("profile.jsp")) {
			reviews = ReviewDAO.getReviewsByUserId(1);
			forwardUrl = "profile.jsp";
		
		} else if (referer.contains("posting.jsp")) {
			reviews = ReviewDAO.getReviewsByPostingId(1);
			forwardUrl = "posting.jsp";
		} else {
			reviews = ReviewDAO.getReviewsByUserId(1);
			forwardUrl = "reviews.jsp";
		}
		
		ArrayList<String> reviewImages = new ArrayList<String>();

		// Set rating images
		for (Review review : reviews) {
			
			double rating = review.getReviewRating();

			if (rating == 1.0) {
				reviewImages.add("rating1.png");
			} else if (rating == 1.5) {
				reviewImages.add("rating1-5.png");
			} else if (rating == 2.0) {
				reviewImages.add("rating2.png");
			} else if (rating == 2.5) {
				reviewImages.add("rating2-5.png");
			} else if (rating == 3.0) {
				reviewImages.add("rating3.png");
			} else if (rating == 3.5) {
				reviewImages.add("rating3-5.png");
			} else if (rating == 4.0) {
				reviewImages.add("rating4.png");
			} else if (rating == 4.5) {
				reviewImages.add("rating4-5.png");
			} else {
				reviewImages.add("rating5.png");
			}
		}
		
		request.setAttribute("reviews", reviews);
		request.setAttribute("reviewImages", reviewImages);
		request.getRequestDispatcher(forwardUrl).forward(request, response);
	}

}
