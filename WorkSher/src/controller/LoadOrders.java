package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import model.Order;
import model.User;

@WebServlet("/LoadOrders")
public class LoadOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadOrders() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in LoadOrders");

		// get request session
		HttpSession session = request.getSession();

		// get parameters and attributes from request
		String load = (String) session.getAttribute("load");
		System.out.println("load:" + load);

		User user = (User) session.getAttribute("currentUser");

		ArrayList<Order> orders = new ArrayList<>();

		// if load is received, load all received orders
		if (load.equals("received")) {
			String searchBy = request.getParameter("searchBy");
			System.out.println("SearchBy: " + searchBy);
			
			if (searchBy == null || searchBy.isEmpty()) {
				orders = OrderDAO.getReceivedOrders(user.getUserid());
			} else if (searchBy.equals("jobCategory")) {
				String jobCategory = request.getParameter("category");
				System.out.println(jobCategory);
				orders = OrderDAO.searchReceivedOrdersByJobCategory(user.getUserid(), jobCategory);
			} else if (searchBy.equals("title")) {
				String title = request.getParameter("title");
				orders = OrderDAO.searchReceivedOrdersByTitle(user.getUserid(), title);
			} else if (searchBy.equals("status")) {
				String status = request.getParameter("status");
				orders = OrderDAO.searchReceivedOrdersByStatus(user.getUserid(), status);
			} else {
				orders = OrderDAO.getReceivedOrders(user.getUserid());
			}

			// if load is requested, load all requested orders
		} else if (load.equals("placed")) {
			String searchBy = request.getParameter("searchBy");
			System.out.println("SearchBy: " + searchBy);
			
			if (searchBy == null || searchBy.isEmpty()) {
				orders = OrderDAO.getPlacedOrders(user.getUserid());
			} else if (searchBy.equals("jobCategory")) {
				String jobCategory = request.getParameter("category");
				System.out.println(jobCategory);
				orders = OrderDAO.searchPlacedOrdersByJobCategory(user.getUserid(), jobCategory);
			} else if (searchBy.equals("title")) {
				String title = request.getParameter("title");
				orders = OrderDAO.searchPlacedOrdersByTitle(user.getUserid(), title);
			} else if (searchBy.equals("status")) {
				String status = request.getParameter("status");
				orders = OrderDAO.searchPlacedOrdersByStatus(user.getUserid(), status);
			} else {
				orders = OrderDAO.getPlacedOrders(user.getUserid());
			}

			// if load is all (only for admin), load all existing orders
		} else if (load.equals("all")) {

			String searchBy = request.getParameter("searchBy");
			System.out.println("SearchBy: " + searchBy);
			if (searchBy == null || searchBy.isEmpty()) {
				orders = OrderDAO.getAllOrders();
			} else if (searchBy.equals("jobCategory")) {
				String jobCategory = request.getParameter("category");
				System.out.println(jobCategory);
				orders = OrderDAO.searchOrdersByJobCategory(jobCategory);
			} else if (searchBy.equals("title")) {
				String title = request.getParameter("title");
				orders = OrderDAO.searchOrdersByTitle(title);
			} else if (searchBy.equals("status")) {
				String status = request.getParameter("status");
				orders = OrderDAO.searchOrdersByStatus(status);
			} else {
				orders = OrderDAO.getAllOrders();
			}
		}

		session.setAttribute("orders", orders);

		int numberOfOrderPage = (int) Math.ceil((orders.size() / 5.0));
		System.out.println("NumberofOrderPage:" + numberOfOrderPage);

		session.setAttribute("ordersSize", orders.size());
		session.setAttribute("numberOfOrderPages", numberOfOrderPage);
		response.sendRedirect("viewOrders.jsp");

		System.out.println("Orders size:" + orders.size());
		for (int i = 0; i < orders.size(); i++) {
			System.out.println("Order:" + orders.get(i).getOrderid());
			System.out.println("Posting:" + orders.get(i).getPosting().getPostingId());
		}
	}
}
