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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in LoadPrevReview");
		
		HttpSession session = request.getSession();
		int revStartIndex = (int) session.getAttribute("revStartIndex");
		int revEndIndex = (int) session.getAttribute("revEndIndex");
		ArrayList<Review> reviews = (ArrayList<Review>) session.getAttribute("reviews");
		int arraySize = reviews.size();
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		String referer = request.getHeader("Referer");
		System.out.println(referer);

		if (referer.contains("reviews.jsp")) {
			if (revStartIndex > 0) {
				if (revStartIndex - pageSize < 0) {
					revStartIndex = 0;
					revEndIndex = pageSize;
				} else {
					revEndIndex = revStartIndex;
					revStartIndex = revEndIndex - pageSize;
				}
				
				session.setAttribute("revStartIndex", revStartIndex);
				session.setAttribute("revEndIndex", revEndIndex);
			}
			response.sendRedirect("reviews.jsp");
		}

	}

}
