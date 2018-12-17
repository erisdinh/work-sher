package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import model.Order;
import model.Posting;
import model.User;

import java.util.ArrayList;

@WebServlet("/LoadOrder")
public class LoadOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadOrder() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get session from request
		HttpSession session = request.getSession();

		// get order id
		long orderid = Long.parseLong(request.getParameter("orderid"));
		Order order = OrderDAO.getOrderById(orderid);
		
		session.setAttribute("type", "old");
		session.setAttribute("order", order);

		response.sendRedirect("order.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
