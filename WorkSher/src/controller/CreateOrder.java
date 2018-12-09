package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.sql.SQLException;
import dao.OrderDAO;
import model.Order;
import model.User;
import model.Posting;

@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// sample users and postings to test order creating
		User user = new User(1, "test", "test", "test","test", "user");
		User user2 = new User(2, "test2", "test2", "test2", "test2", "user");
		Posting tempPost = new Posting(1, user2, "Design", "I can design posters.", "2 cups of coffee", "active", "");
		
		// get session  from request
		HttpSession session = request.getSession();

		// set sample users and posting to session to test order
		session.setAttribute("currentUser", user);
		session.setAttribute("postUser", user2);
		session.setAttribute("posting", tempPost);
		
		// get attributes from current session
		User requestUser = (User) session.getAttribute("currentUser");
		User postUser = (User) session.getAttribute("postUser");
		Posting posting = (Posting) session.getAttribute("posting");
		String orderDescription = request.getParameter("description");
		
		System.out.println(orderDescription);
		
		// create new order
		Order order = new Order();
		order.setRequestUser(requestUser);
		order.setPosting(posting);
		order.setDescription(orderDescription);
		order.setStatus("pending");
		
		try {
			OrderDAO.addOrder(order);
		}
		catch (SQLException e) {
			e.getMessage();
		}
		
		session.setAttribute("newOrder", order);
		response.sendRedirect("order.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
