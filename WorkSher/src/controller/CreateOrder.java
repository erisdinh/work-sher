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
import dao.PostingDAO;
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
		
		// get session  from request
		HttpSession session = request.getSession();

		// get attributes from current session
		User currentUser = (User) session.getAttribute("currentUser");
		User postUser = (User) session.getAttribute("postUser");
		Posting posting = (Posting) session.getAttribute("posting");
		String orderDescription = request.getParameter("description");
		
		System.out.println(orderDescription);
		
		// create new order
		Order order = new Order();
		order.setRequestUser(currentUser);
		order.setPosting(posting);
		order.setDescription(orderDescription);
		order.setStatus("pending");
		
		// add new order to database
		OrderDAO.addOrder(order);
		
		// set new order as an attribute to request
		session.setAttribute("newOrder", order);
		response.sendRedirect("order.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
