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
import java.util.Calendar;

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
		response.getWriter().append("Served at: Create Order.java").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		
		User user = new User("qinee.kun", "12345", "Qinee Dinh", "qinee.kun@gmail.com");
		User user2 = new User("keanee.kun", "12345", "Keanee Dinh", "keanee.kun@gmail.com");
		Posting tempPost = new Posting(3, user2, "Design", "design posters", "whatever", "active", "");
		
		HttpSession session = request.getSession();
		session.setAttribute("currentUser", user);
		session.setAttribute("postUser", user2);
		session.setAttribute("posting", tempPost);
		
		User requestUser = (User) session.getAttribute("currentUser");
		User postUser = (User) session.getAttribute("postUser");
		Posting posting = (Posting) session.getAttribute("posting");
		
		String orderDescription = request.getParameter("description");
		System.out.println(orderDescription);
		
		Order order = new Order();
		order.setRequestUser(requestUser);
		order.setPostUser(postUser);
		order.setDescription(orderDescription);
		order.setStatus("pending");
		
		try {
			OrderDAO.addOrder(order);
		}
		catch (SQLException e) {
			e.getMessage();
		}
		
		session.setAttribute("newOrder", order);
		response.sendRedirect("/order.jsp");
		
	}
}
