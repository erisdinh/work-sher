package controller.reviews;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoadNextReviewPage
 */
@WebServlet("/LoadNextReviewPage")
public class LoadNextReviewPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int PAGE_OFFSET = 5;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in LoadNextReview");
		HttpSession session = request.getSession();
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		int revEndIndex = Integer.parseInt(request.getParameter("revEndIndex"));
		int size = Integer.parseInt(request.getParameter("size"));
		int revStartIndex = 0;

		if (referer.contains("reviews.jsp")) {
			revStartIndex = revEndIndex;
			if (revEndIndex + PAGE_OFFSET < size) {
				revEndIndex += PAGE_OFFSET;
			} else {
				revEndIndex = size;
			}
			session.setAttribute("revStartIndex", revStartIndex);
			session.setAttribute("revEndIndex", revEndIndex);
			response.sendRedirect("reviews.jsp");
		}
	}
}
