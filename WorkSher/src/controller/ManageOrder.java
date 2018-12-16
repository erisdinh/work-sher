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
import model.Order;

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

			order.setStatus("Cancel");
			OrderDAO.editOrder(order);

		} else if (action.equals("edit")) {

			response.sendRedirect("editOrder.jsp");

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
			
			session.setAttribute("order", order);
			
			response.sendRedirect("order.jsp");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
