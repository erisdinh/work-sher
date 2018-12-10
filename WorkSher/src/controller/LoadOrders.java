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
		
		// sample currentUser to test manageOrder in user mode
		User tempCurrentUser = new User(1, "test", "test", "test","test", "user");
		session.setAttribute("currentUser", tempCurrentUser);
		
		// get parameters and attributes from request
		String mode = request.getParameter("mode");
		String action = request.getParameter("action");
		User currentUser = (User) session.getAttribute("currentUser");
		
		// set action to session
		session.setAttribute("action", action);
		
		// if in user mode
		if(mode.equals("user")) {
			
			// if action is received, load all received orders
			if(action.equals("received")) {
				ArrayList<Order> receivedOrders = OrderDAO.getReceivedOrders(currentUser.getUserid());
				session.setAttribute("receivedOrders", receivedOrders);
				System.out.println("Received Orders");
				
				// if action is requested, load all requested orders
			} else if (action.equals("requested")) {
				ArrayList<Order> requestedOrders = OrderDAO.getRequestedOrders(currentUser.getUserid());
				session.setAttribute("requestedOrders", requestedOrders);
				System.out.println("Requested Orders");
			}
			response.sendRedirect("User/viewOrders.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
