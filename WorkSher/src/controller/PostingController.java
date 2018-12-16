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
import model.Posting;

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
			
		} else if (action.equalsIgnoreCase("advsearch")) {
			String searchTitle = request.getParameter("title");
			String searchJobCategory = request.getParameter("jobCategory");
			String searchDescription = request.getParameter("description");
			String searchUser = request.getParameter("user");

			forward = LIST_POSTINGS;
			request.setAttribute("postings", PostingDAO.getAdvancedSearchResults(searchTitle, searchJobCategory, searchDescription, searchUser));
			
		} else if (action.equalsIgnoreCase("delete")) {
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
				forward = VIEW;
			}
		} else {
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
		
		posting.setUserId(1); // TO DO, get from
		// posting.setUserId(((User)session.getAttribute("user")).getUserid()); ???
		posting.setJobCategory(request.getParameter("jobCategory"));
		posting.setTitle(request.getParameter("title"));
		posting.setDescription(request.getParameter("description"));
		posting.setCompensation(request.getParameter("compensation"));
		posting.setStatus(request.getParameter("status"));
		Part portfolioPart = request.getPart("portfolio");
		InputStream portfolio = portfolioPart.getInputStream();
		posting.setPortfolio(portfolio);
		
		String portfolioType = portfolioPart.getContentType();
		posting.setPortfolioType(portfolioType);
		int portfolioLength = Math.toIntExact(portfolioPart.getSize());
		System.out.println(portfolioLength);
		posting.setPortfolioLength(portfolioLength);
		
		
		//// what
//		Image ptflThumb = ImageIO.read(portfolio).getScaledInstance(100,100, BufferedImage.SCALE_SMOOTH);
//		BufferedImage bi = new BufferedImage(ptflThumb.getWidth(null), ptflThumb.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2d = bi.createGraphics();
//		g2d.drawImage(ptflThumb,  0, 0,  null);
//		g2d.dispose();
//		ByteArrayOutputStream baos = null;
//		try {
//			baos = new ByteArrayOutputStream();
//			ImageIO.write(bi, "png", baos);
//		} finally {
//			try {
//				baos.close();
//			} catch (Exception e) {		
//			}
// 		}
//		posting.setPortfolioThumb(new ByteArrayInputStream(baos.toByteArray()));
//		
//		
		
		
		
		
		String postingIdString = request.getParameter("postingId");
		
		if (postingIdString == null || postingIdString.isEmpty()) {
			PostingDAO.addPosting(posting);
		} else {
			posting.setPostingId(Integer.parseInt(postingIdString));
			PostingDAO.updatePosting(posting);
		}
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
