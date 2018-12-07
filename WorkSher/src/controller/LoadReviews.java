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
		ArrayList<Review> reviews = ReviewDAO.getReviewsByUserId(1);
		
		request.setAttribute("reviews", reviews);
		
		request.getRequestDispatcher("reviews.jsp").forward(request, response);
	}

}
