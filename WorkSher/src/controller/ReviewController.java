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

import dao.OrderDAO;
import dao.PostingDAO;
import dao.ReviewDAO;
import model.Order;
import model.Posting;
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
	private static final String EDIT_REVIEW = "editReview.jsp";
	private static final String PROFILE_REDIRECT = "LoadProfile?userId=";
	private static final String REVIEWS_REDIRECT = "ReviewController?action=load";
	private static final String POSTING_REDIRECT = "PostingController?action=view&postingId=";
	private static final String LEAVE_REVIEW = "leaveReview.jsp";

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
		System.out.println("in doGet");
		String referrer = "";
		String action = request.getParameter("action");
		System.out.println("ACTION: " + action);

		if (request.getParameter("referrer") != null) {
			referrer = request.getParameter("referrer");
		}

		List<Review> reviews = new ArrayList<Review>();

		long orderId = INVALID_ID;
		long postingId = INVALID_ID;
		long forUserId = INVALID_ID;
		long fromUserId = INVALID_ID;

		int revStartIndex = 0;
		int revEndIndex = 0;

		if (request.getParameter("orderId") != null) {
			orderId = Long.parseLong(request.getParameter("orderId"));
		}

		if (request.getParameter("postingId") != null) {
			postingId = Long.parseLong(request.getParameter("postingId"));
		}

		if (request.getParameter("forUserId") != null) {
			forUserId = Long.parseLong(request.getParameter("forUserId"));
		}

		if (request.getParameter("fromUserId") != null) {
			fromUserId = Long.parseLong(request.getParameter("fromUserId"));
		}

		String forwardUrl = null;

		if (referrer.equalsIgnoreCase("profileReviews") || referrer.equalsIgnoreCase("profile")) {
			request.setAttribute("forUserId", forUserId);
			reviews = ReviewDAO.getReviewsByForUserId(forUserId);
		} else if (referrer.equalsIgnoreCase("reviews")) {
			request.setAttribute("fromUserId", fromUserId);
			reviews = ReviewDAO.getReviewsByFromUserId(fromUserId);
		} else {
			request.setAttribute("postingId", postingId);
			reviews = ReviewDAO.getReviewsByPostingId(postingId);
		}

		// Load previous review page
		if (action.equalsIgnoreCase("prev")) {
			System.out.println("prev");
			int pageNum = Integer.parseInt(String.valueOf(request.getParameter("pageNum")));
			if (pageNum > 1) {
				pageNum--;
			}

			request.setAttribute("pageNum", pageNum);
			revEndIndex = (int) (pageNum * PAGE_SIZE);

			if (revEndIndex >= reviews.size()) {
				revEndIndex = reviews.size();
			}

			if (pageNum > 1) {
				revStartIndex = (int) (pageNum * PAGE_SIZE - PAGE_SIZE);
			}
			forwardUrl = LOAD_REVIEWS;

			// Load next review page
		} else if (action.equalsIgnoreCase("next")) {
			int pageNum = Integer.parseInt(String.valueOf(request.getParameter("pageNum")));

			int arraySize = reviews.size();

			if (pageNum < arraySize / PAGE_SIZE) {
				pageNum++;
			}

			revEndIndex = (int) (pageNum * PAGE_SIZE);

			request.setAttribute("pageNum", pageNum);

			if (revEndIndex > arraySize) {
				revEndIndex = arraySize;
			}

			if (pageNum > 1) {
				revStartIndex = (int) (pageNum * PAGE_SIZE - PAGE_SIZE);
			}

			forwardUrl = LOAD_REVIEWS;

		} else if (action.equalsIgnoreCase("edit")) {
			long reviewId = Long.parseLong(request.getParameter("reviewId"));
			Review review = ReviewDAO.getReviewById(reviewId);
			request.setAttribute("review", review);

			System.out.println(review.toString());
			Posting posting = PostingDAO.getPostingById(review.getPostingId());
			request.setAttribute("posting", posting);
			request.setAttribute("fromUserId", fromUserId);

			forwardUrl = EDIT_REVIEW;

		} else if (action.equalsIgnoreCase("delete")) {
			long reviewId = Long.parseLong(request.getParameter("reviewId"));

			ReviewDAO.deleteReviewById(reviewId);

			if (referrer.equalsIgnoreCase("profile")) {
				forwardUrl = PROFILE_REDIRECT + forUserId;
			} else if (referrer.equalsIgnoreCase("reviews")) {
				forwardUrl = REVIEWS_REDIRECT + "&fromUserId=" + fromUserId;
			} else if (referrer.equalsIgnoreCase("profileReviews")) {
				forwardUrl = REVIEWS_REDIRECT + "&forUserId=" + forUserId;
			} else {
				HttpSession session = request.getSession();
				Posting posting = (Posting) session.getAttribute("posting");
				postingId = posting.getPostingId();
				forwardUrl = POSTING_REDIRECT + postingId;
			}
		} else if (action.equals("leaveReview")) {
			HttpSession session = request.getSession();
			forwardUrl = LEAVE_REVIEW;

		} else { // load reviews

			if (reviews.size() < PAGE_SIZE) {
				revEndIndex = reviews.size();
			} else {
				revEndIndex = (int) PAGE_SIZE;
			}

			request.setAttribute("pageNum", 1);

			forwardUrl = LOAD_REVIEWS;
		}

		request.setAttribute("referrer", referrer);
		request.setAttribute("reviews", reviews.subList(revStartIndex, revEndIndex));
		request.getRequestDispatcher(forwardUrl).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String action = "";

		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
		}
		String referrer = request.getParameter("referrer");
		System.out.println("Action: " + action);
		String forwardUrl = null;

		if (action.equalsIgnoreCase("edit")) {
			long reviewId = Long.parseLong(request.getParameter("reviewId"));
			Review review = ReviewDAO.getReviewById(reviewId);

			double reviewRating = Double.parseDouble(request.getParameter("reviewRating"));
			String reviewText = request.getParameter("reviewText");

			review.setReviewRating(reviewRating);
			review.setReviewText(reviewText);

			long postingId = review.getPostingId();

			ReviewDAO.editReview(review);

			if (referrer.equalsIgnoreCase("profile")) {
				forwardUrl = PROFILE_REDIRECT + review.getForUserId();
			} else if (referrer.equalsIgnoreCase("reviews")) {
				forwardUrl = "ReviewController?action=load&fromUserId=" + review.getFromUserId() + "&referrer="
						+ referrer;
			} else {
				forwardUrl = POSTING_REDIRECT + postingId;
			}

		} else {
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
			forwardUrl = "LoadOrder?orderid=" + orderId;
		}

		response.sendRedirect(forwardUrl);
	}

}
