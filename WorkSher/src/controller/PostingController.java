package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("search")) {
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
			
			request.setAttribute("posting", posting);
			
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
		
		String path = File.separator + "images" + File.separator + posting.getUserId() + File.separator +  "portfolio";
//		String path = "/images/" + posting.getUserId() + "/portfolio/";
//		String path = getServletContext().getContextPath() + "/images";
		Part portfolio = request.getPart("portfolio");
		String fileName = getFileName(portfolio);
		System.out.println(getServletContext().getContextPath());
		
		OutputStream out= null;
		InputStream filecontent = null;
		
		try {
			File file = new File(path + File.separator + fileName);
			boolean test = file.mkdirs();
			if (test) {
				System.out.println("it owrks");
			} else {
				System.out.println("it doesn't");
			}
			
			out = new FileOutputStream(file);
			filecontent = portfolio.getInputStream();
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
		}
		posting.setPortfolio(path + fileName);
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
	    //final String partHeader = part.getHeader("content-disposition");
//	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	        	String test = content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        	System.out.println(test);
	        	test = test.substring(test.lastIndexOf("\\") + 1 ,test.length());
	        	return test;
	        }
	    }
	    return null;
	}
}
