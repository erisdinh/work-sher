package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDAO;
import model.Review;

@WebServlet("/CreateReview")
public class CreateReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateReview() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Review review = new Review();
		
		long userId = 1; // TODO: Get from session
		long postingId = 1; // TODO: Get from request
		int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
		String reviewText = request.getParameter("reviewText");
		
		review.setUserId(userId);
		review.setPostingId(postingId);
		review.setReviewRating(reviewRating);
		review.setReviewText(reviewText);
		
		ReviewDAO.addReview(review);
		
		// TODO: Implement actual redirect logic here
		response.sendRedirect("profile.jsp");
		
	}

}
