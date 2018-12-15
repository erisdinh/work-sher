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

@WebServlet("/LoadPrevReviewPage")
public class LoadPrevReviewPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int PAGE_OFFSET = 5;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadPrevReviewPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in LoadPrevReview");
		HttpSession session = request.getSession();
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		int revStartIndex = Integer.parseInt(request.getParameter("revStartIndex"));
		int size = Integer.parseInt(request.getParameter("size"));
		int revEndIndex = 0;

		if (referer.contains("reviews.jsp")) {
			if (revStartIndex > 0) {
				if (size < revStartIndex) {
					revEndIndex = size;
				} else {
					revEndIndex = revStartIndex;
				}
				revStartIndex -= PAGE_OFFSET;
				session.setAttribute("revStartIndex", revStartIndex);
				session.setAttribute("revEndIndex", revEndIndex);
			}
			response.sendRedirect("reviews.jsp");
		}

	}

}
