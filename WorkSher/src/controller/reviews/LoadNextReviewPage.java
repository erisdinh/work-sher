package controller.reviews;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Review;

/**
 * Servlet implementation class LoadNextReviewPage
 */
@WebServlet("/LoadNextReviewPage")
public class LoadNextReviewPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadNextReviewPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in LoadNextReview");
		String referer = request.getHeader("Referer");
		System.out.println(referer);

		HttpSession session = request.getSession();
		int revStartIndex = (int) request.getAttribute("revStartIndex");
		int revEndIndex = (int) request.getAttribute("revEndIndex");
		ArrayList<Review> reviews = (ArrayList<Review>) request.getAttribute("reviews");
		int arraySize = reviews.size();
		int pageSize = (int) request.getAttribute("pageSize");

		if (referer.contains("reviews.jsp")) {
			if (revEndIndex != arraySize) {
				if (revEndIndex + pageSize > arraySize) {
					revStartIndex += pageSize;
					revEndIndex = arraySize;
				} else if (revEndIndex + pageSize < arraySize) {
					revStartIndex = revEndIndex;
					revEndIndex += pageSize;
				}
			}
			session.setAttribute("revStartIndex", revStartIndex);
			session.setAttribute("revEndIndex", revEndIndex);
			response.sendRedirect("reviews.jsp");
		}
	}
}
