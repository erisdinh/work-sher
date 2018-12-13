package controller.reviews;

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
import javax.servlet.http.HttpSession;

import dao.ReviewDAO;
import model.Review;
import model.User;

@WebServlet("/CreateReview")
public class CreateReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateReview() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Review review = new Review();
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("currentUser");
		
		long fromUserId = user.getUserid();
		long forUserId = 1; // TODO: Get from page
		long postingId = 1; // TODO: Get from request
		double reviewRating = Double.parseDouble(request.getParameter("reviewRating"));
		String reviewText = request.getParameter("reviewText");
		
		review.setForUserId(forUserId);
		review.setFromUserId(fromUserId);
		review.setPostingId(postingId);
		review.setReviewRating(reviewRating);
		review.setReviewText(reviewText);
		
		ReviewDAO.addReview(review);
		
		// TODO: Implement actual redirect logic here
		response.sendRedirect("profile.jsp");
		
	}

}
