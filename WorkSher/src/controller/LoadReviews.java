package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDAO;
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
		if (referer.contains("profile.jsp")) {
			ArrayList<Review> reviews = ReviewDAO.getReviewsByUserId(1);
			System.out.println("In first condition");
			request.setAttribute("reviews", reviews);
			request.getRequestDispatcher("reviews.jsp").forward(request, response);
		} else if (referer.contains("posting.jsp")) {
			ArrayList<Review> reviews = ReviewDAO.getReviewsByPostingId(1);
			System.out.println("In second condition");
			request.setAttribute("reviews", reviews);
			request.getRequestDispatcher("posting.jsp").forward(request, response);
		} else {
			System.out.println("In else condition");
		}
		
		
	}

}
