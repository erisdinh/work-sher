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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in LoadPrevReview");
		
		HttpSession session = request.getSession();
		int revStartIndex = Integer.parseInt(request.getParameter("revStartIndex"));
		int arraySize = Integer.parseInt(request.getParameter("arraySize"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int revEndIndex = 0;
		
		String referer = request.getHeader("Referer");
		System.out.println(referer);

		if (referer.contains("reviews.jsp")) {
			if (revStartIndex > 0) {
				if (revStartIndex > arraySize) {
					revEndIndex = arraySize;
				} else {
					revEndIndex = revStartIndex;
				}
				
				if (revStartIndex - pageSize < 0) {
					revStartIndex = 0;
				} else {
					revStartIndex -= pageSize;
				}
				session.setAttribute("revStartIndex", revStartIndex);
				session.setAttribute("revEndIndex", revEndIndex);
			}
			response.sendRedirect("reviews.jsp");
		}

	}

}
