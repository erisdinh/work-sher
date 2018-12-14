package controller.reviews;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDAO;
import dao.UserDAO;
import model.Review;
import model.User;

@WebServlet("/LoadReviews")
public class LoadReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoadReviews() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		
		// TODO: Make sure these redirect properly
		// TODO: Get the userId from the session and the postingId from the request
		
		ArrayList<Review> reviews = null;
		
		String forwardUrl = null;
		
		
		if (referer.contains("profile.jsp")) {
			reviews = ReviewDAO.getReviewsByForUserId(1);
			forwardUrl = "profile.jsp";
		
		} else if (referer.contains("posting.jsp")) {
			reviews = ReviewDAO.getReviewsByPostingId(1);
			forwardUrl = "posting.jsp";
		} else if (referer.contains("homepage.jsp")) {
			reviews = ReviewDAO.getReviewsByFromUserId(user.getUserid());
			forwardUrl = "reviews.jsp";
		
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
		
		int revEndIndex;
		
		session.setAttribute("reviews", reviews);
		session.setAttribute("reviewImages", reviewImages);
		session.setAttribute("revStartIndex", 0);
		
		if (reviews.size() < 5) {
			revEndIndex = reviews.size();
		} else {
			revEndIndex = 5;
		}
		session.setAttribute("revEndIndex", revEndIndex);
		response.sendRedirect("reviews.jsp");

		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
