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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");

		ArrayList<Review> reviews = ReviewDAO.getReviewsByFromUserId(user.getUserid());

		session.setAttribute("reviews", reviews);
		
		response.sendRedirect("reviews.jsp");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
