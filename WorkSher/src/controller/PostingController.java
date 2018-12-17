package controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.User;
import dao.PostingDAO;
import dao.ReviewDAO;
import model.Posting;
import model.Review;

/**
 * Servlet implementation class PostingController
 */
@WebServlet("/PostingController")
@MultipartConfig
public class PostingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static String VIEW = "/posting.jsp";
	private static String INSERT_OR_EDIT = "/editPosting.jsp";
	private static String LIST_POSTINGS = "/listPostings.jsp";
    public PostingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("search")) {
			String searchTerm = request.getParameter("searchTerm");
			
			
			forward = LIST_POSTINGS;
			request.setAttribute("postings", PostingDAO.getSearchResults(searchTerm));
			
		} else if (action.equalsIgnoreCase("catSearch")) {
			String searchTitle = "";
			String searchJobCategory = request.getParameter("jobCategory").toLowerCase();
			String searchDescription = "";
			String searchUser = "";
			String searchStartDate = "2018-12-01";
			String searchEndDate = "2999-12-31";
			forward = LIST_POSTINGS;
			request.setAttribute("postings", PostingDAO.getAdvancedSearchResults(searchTitle, searchJobCategory, searchDescription, searchUser, searchStartDate, searchEndDate));
			
		} else if (action.equalsIgnoreCase("advsearch")) {
			String searchTitle = request.getParameter("title").toLowerCase();
			String searchJobCategory = request.getParameter("jobCategory").toLowerCase();
			String searchDescription = request.getParameter("description").toLowerCase();
			String searchUser = request.getParameter("user").toLowerCase();
			String searchStartDate = request.getParameter("startDate");
			String searchEndDate = request.getParameter("endDate");
			System.out.println(searchStartDate + " " + searchEndDate);
			//			try {
//				Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("startDate"));
//				
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			try {
//				Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("endDate"));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
			forward = LIST_POSTINGS;
			request.setAttribute("postings", PostingDAO.getAdvancedSearchResults(searchTitle, searchJobCategory, searchDescription, searchUser, searchStartDate, searchEndDate));
			
		} else if (action.equalsIgnoreCase("deactivate")) {
			int postingId = Integer.parseInt(request.getParameter("postingId"));
			
			PostingDAO.deactivatePostingById(postingId);
			forward = LIST_POSTINGS;
			
			request.setAttribute("postings", PostingDAO.getAllPostings());
			
		
		} else if (action.equalsIgnoreCase("activate")) {
			int postingId = Integer.parseInt(request.getParameter("postingId"));
			
			PostingDAO.activatePostingById(postingId);
			forward = LIST_POSTINGS;
			
			request.setAttribute("postings", PostingDAO.getAllPostings());
			
		
		}
		
		else if (action.equalsIgnoreCase("delete")) {
			int postingId = Integer.parseInt(request.getParameter("postingId"));
			
			PostingDAO.deletePostingById(postingId);
			forward = LIST_POSTINGS;
			
			request.setAttribute("postings", PostingDAO.getAllPostings());
			
		
		} else if (action.equalsIgnoreCase("listPostings")) {
			forward = LIST_POSTINGS;
			request.setAttribute("postings", PostingDAO.getAllPostings());
		} else if (!action.equalsIgnoreCase("insert")){
			int postingId = Integer.parseInt(request.getParameter("postingId"));
			
			Posting posting = PostingDAO.getPostingById(postingId);
			
			//request.setAttribute("posting", posting);
			session.setAttribute("posting", posting);
			if (action.equalsIgnoreCase("edit")) {
				forward = INSERT_OR_EDIT;
			} else {
				List<Review> reviews = ReviewDAO.getReviewsByPostingId(postingId);
				request.setAttribute("reviews", reviews);
				forward = VIEW;
			}
		} else {
			session.removeAttribute("posting");
			forward = INSERT_OR_EDIT;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		Posting posting = new Posting();
		User currentUser = (User)session.getAttribute("currentUser");
		
		posting.setUserId(currentUser.getUserid()); // TO DO, get from
		posting.setUsername(currentUser.getUsername());
		posting.setJobCategory(request.getParameter("jobCategory"));
		posting.setTitle(request.getParameter("title"));
		posting.setDescription(request.getParameter("description"));
		posting.setCompensation(request.getParameter("compensation"));
		posting.setStatus(request.getParameter("status"));
		System.out.println("in PC: " + request.getParameter("dateUpdated"));
		try {
			Date dateUpdated = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dateUpdated"));
			posting.setDateUpdated(dateUpdated);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
//		Date dateUpdated = new java.sql.Date(     ((Date)request.getAttribute("dateUpdated")).getTime()     );
//		posting.setDateUpdated(dateUpdated);
		
		//posting.setDateUpdated(request.getParameter("dateUpdated"));
		Part portfolioPart = request.getPart("portfolio");
		InputStream portfolio = portfolioPart.getInputStream();
		posting.setPortfolio(portfolio);
		
		String portfolioType = portfolioPart.getContentType();
		if (!portfolioType.equals("application/octet-stream")) {
			posting.setPortfolioType(portfolioType);

			int portfolioLength = Math.toIntExact(portfolioPart.getSize());

			System.out.println(portfolioLength);
			posting.setPortfolioLength(portfolioLength);
		}
		String postingIdString = request.getParameter("postingId");
		
		if (postingIdString == null || postingIdString.isEmpty()) {
			PostingDAO.addPosting(posting);
			
		} else {
			posting.setPostingId(Integer.parseInt(postingIdString));
			PostingDAO.updatePosting(posting);
		}
	    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	    response.setDateHeader("Expires", 0);
	    
		RequestDispatcher view = request.getRequestDispatcher(LIST_POSTINGS);
		request.setAttribute("postings", PostingDAO.getAllPostings());
		view.forward(request, response);
	}
//	 https://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
	private String getFileName(final Part part) {
		System.out.println("in getFileName!");
	    for (String content : part.getHeader("content-disposition").split(";")) {
	    	System.out.println(content);
	        if (content.trim().startsWith("filename")) {
	        	String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	            System.out.println(fileName);
	        	return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
	            
	        }
	    }
	    return null;
	}
}
