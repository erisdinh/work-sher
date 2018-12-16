package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDAO;
import model.Review;
import model.User;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/ReviewController")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int INVALID_ID = -1; // IDs cannot be < 0, therefore check if ID value has been set
	private static final double PAGE_SIZE = 10.0; // # of reviews displayed per page of reviews.jsp
	private static final String LOAD_REVIEWS = "reviews.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		List<Review> reviews = new ArrayList<Review>();

		int orderId = INVALID_ID;
		int postingId = INVALID_ID;
		int forUserId = INVALID_ID;
		int fromUserId = INVALID_ID;
		
		int revStartIndex = 0;
		int revEndIndex = (int) PAGE_SIZE;

		if (request.getParameter("orderId") != null) {
			orderId = Integer.parseInt(request.getParameter("orderId"));
		}

		if (request.getParameter("postingId") != null) {
			postingId = Integer.parseInt(request.getParameter("postingId"));
		}

		if (request.getParameter("forUserId") != null) {
			forUserId = Integer.parseInt(request.getParameter("forUserId"));
		}

		if (request.getParameter("fromUserId") != null) {
			fromUserId = Integer.parseInt(request.getParameter("fromUserId"));
		}

		String forwardUrl = null;

		// Load previous review page
		if (action.equalsIgnoreCase("prev")) {
			System.out.println("prev");
			int pageNum = Integer.parseInt(String.valueOf(request.getParameter("pageNum")));
			if (pageNum > 1) {
				pageNum--;
			}
			int numReviews = Integer.parseInt(String.valueOf(request.getParameter("numReviews")));

			revEndIndex = (int) (pageNum * PAGE_SIZE);

			if (revEndIndex >= numReviews) {
				revEndIndex = numReviews - 1;
			}

			if (pageNum > 1) {
				revStartIndex = (int) (pageNum * PAGE_SIZE - PAGE_SIZE);
			} else {
				revStartIndex = 0;
			}

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("numReviews", numReviews);
			
			if (forUserId != INVALID_ID) {
				request.setAttribute("forUserId", forUserId);
				reviews = ReviewDAO.getReviewsByForUserId(forUserId);
			} else if (fromUserId != INVALID_ID) {
				request.setAttribute("fromUserId", fromUserId);
				reviews = ReviewDAO.getReviewsByFromUserId(fromUserId);
			}
			forwardUrl = LOAD_REVIEWS;
			
		// Load next review page
		} else if (action.equalsIgnoreCase("next")) {
			int pageNum = Integer.parseInt(String.valueOf(request.getParameter("pageNum")));
			int numReviews = Integer.parseInt(String.valueOf(request.getParameter("numReviews")));
			
			if (pageNum < numReviews / PAGE_SIZE) {
				pageNum++;
			}

			revEndIndex = (int) (pageNum * PAGE_SIZE);

			if (revEndIndex >= numReviews) {
				revEndIndex = numReviews - 1;
			}

			if (pageNum > 1) {
				revStartIndex = (int) (pageNum * PAGE_SIZE - PAGE_SIZE);
			} else {
				revStartIndex = 0;
			}

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("numReviews", numReviews);
			
			if (forUserId != INVALID_ID) {
				request.setAttribute("forUserId", forUserId);
				reviews = ReviewDAO.getReviewsByForUserId(forUserId);
			} else if (fromUserId != INVALID_ID) {
				request.setAttribute("fromUserId", fromUserId);
				reviews = ReviewDAO.getReviewsByFromUserId(fromUserId);
			}
			forwardUrl = LOAD_REVIEWS;

		} else if (action.equalsIgnoreCase("edit")) {

		} else if (action.equalsIgnoreCase("delete")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			ReviewDAO.deleteReviewByReviewId(reviewId);

			reviews = ReviewDAO.getReviewsByForUserId(fromUserId);

		} else { // load reviews

			if (forUserId != INVALID_ID) {
				reviews = ReviewDAO.getReviewsByForUserId(forUserId);
				request.setAttribute("forUserId", forUserId);
			} else if (fromUserId != INVALID_ID) {
				reviews = ReviewDAO.getReviewsByFromUserId(fromUserId);
				request.setAttribute("fromUserId", fromUserId);
			}

			request.setAttribute("pageNum", 1);
			request.setAttribute("numReviews", reviews.size());

			forwardUrl = LOAD_REVIEWS;
		}
		
		request.setAttribute("reviews", reviews.subList(revStartIndex, revEndIndex));
		request.getRequestDispatcher(forwardUrl).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");

		long forUserId = Long.parseLong(request.getParameter("forUserId"));
		long orderId = Long.parseLong(request.getParameter("orderId"));
		long postingId = Long.parseLong(request.getParameter("postingId"));
		double reviewRating = Double.parseDouble(request.getParameter("reviewRating"));
		String reviewText = request.getParameter("reviewText");

		Review review = new Review();

		review.setForUserId(forUserId);
		review.setFromUserId(user.getUserid());
		review.setOrderId(orderId);
		review.setPostingId(postingId);
		review.setReviewRating(reviewRating);
		review.setReviewText(reviewText);

		ReviewDAO.addReview(review);
		request.getRequestDispatcher("LoadOrder?orderId=" + orderId).forward(request, response);
	}

}
