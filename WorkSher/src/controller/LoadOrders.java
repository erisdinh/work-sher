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
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in LoadOrders");
		
		// get request session
		HttpSession session = request.getSession();
		
		// get parameters and attributes from request
		String load = request.getParameter("load");
		
		User currentUser = (User) session.getAttribute("currentUser");
		
		// set action to session
		session.setAttribute("load", load);

		ArrayList<Order> orders = new ArrayList<>();	
		
			// if action is received, load all received orders
			if(load.equals("received")) {
				orders = OrderDAO.getReceivedOrders(currentUser.getUserid());
				
				// if action is requested, load all requested orders
			} else if (load.equals("requested")) {
				orders = OrderDAO.getRequestedOrders(currentUser.getUserid());
			}
			
			session.setAttribute("orders", orders);
			
			int numberOfOrderPage = (int) Math.ceil((orders.size() / 5.0));
			System.out.println("NumberofOrderPage:" + numberOfOrderPage);
			
			session.setAttribute("ordersSize", orders.size());
			session.setAttribute("numberOfOrderPages", numberOfOrderPage);
			response.sendRedirect("viewOrders.jsp");
		
		System.out.println("Orders size:" + orders.size());
		for(int i = 0; i < orders.size(); i++) {
			System.out.println("Order:" + orders.get(i).getOrderid());
			System.out.println("Posting:" + orders.get(i).getPosting().getPostingId());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
