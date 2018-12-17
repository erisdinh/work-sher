package controller;

import java.awt.FontFormatException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import dao.UserDAO;
import model.Order;
import model.Posting;
import model.User;

@WebServlet("/ManageOrder")
public class ManageOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManageOrder() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		HttpSession session = request.getSession();

		Order order = (Order) session.getAttribute("order");

		if (action.equals("cancel")) {

			order.setStatus("Cancelled");
			OrderDAO.updateOrder(order);

		} else if (action.equals("update")) {

			response.sendRedirect("updateOrder.jsp");

		} else if (action.equals("review")) {

			response.sendRedirect("leavereview.jsp");

		} else if (action.equals("reject")) {

			order.setStatus("Rejected");

			OrderDAO.responseOrder(order);
			order = OrderDAO.getOrderById(order.getOrderid());

			session.setAttribute("order", order);

			response.sendRedirect("order.jsp");

		} else if (action.equals("approve")) {

			order.setStatus("Approved");

			OrderDAO.responseOrder(order);
			order = OrderDAO.getOrderById(order.getOrderid());

			session.setAttribute("order", order);

			response.sendRedirect("order.jsp");

		} else if (action.equals("complete")) {

			order.setStatus("Completed");

			OrderDAO.responseOrder(order);
			order = OrderDAO.getOrderById(order.getOrderid());
			
			session.setAttribute("type", "new");
			session.setAttribute("order", order);

			response.sendRedirect("order.jsp");

		} else if (action.equals("confirm")) {
			
			OrderDAO.deleteOrder(order.getOrderid());
			response.sendRedirect("viewOrders.jsp?initial=true&load=all");
			
		} else if(action.equals("skip")) {
			response.sendRedirect("order.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		
		String orderDescription = request.getParameter("description");

		if (action.equals("create")) {
			
			// get attributes from current session
			User currentUser = (User) session.getAttribute("currentUser");
			Posting posting = (Posting) session.getAttribute("posting");
			
			// create new order
			Order order = new Order();
			order.setRequestUser(currentUser);
			order.setPostUser(UserDAO.getUserById(posting.getUserId()));
			order.setPosting(posting);
			order.setDescription(orderDescription);
			order.setStatus("Pending");

			// add new order to database
			OrderDAO.addOrder(order);

			// set new order as an attribute to request
			session.setAttribute("order", order);
			response.sendRedirect("order.jsp");
			
		} else if (action.equals("update")) {
			
			Order order = (Order) session.getAttribute("order");
			
			order.setDescription(orderDescription);
			
			OrderDAO.updateOrder(order);
		}
	}

}
