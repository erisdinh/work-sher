package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostingDAO;
import model.Posting;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(Long.parseLong(request.getParameter("ps")));
		
		Posting posting = PostingDAO.getPostingById(Long.parseLong(request.getParameter("ps")));
		Posting test = PostingDAO.getPostingById(5);
		System.out.println(posting.getDescription());

		System.out.println("processing image" + posting.getPortfolioType());
		System.out.println(posting.getPortfolioLength());
		
		
		InputStream is = posting.getPortfolio();
		
		byte[] buff = new byte[8000];
		int bytesRead = 0;
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		while ((bytesRead = is.read(buff))!= -1) {
			bao.write(buff, 0, bytesRead);
		}
		
		byte[] data = bao.toByteArray();
		
		ByteArrayInputStream  bin = new ByteArrayInputStream(data);
		
		response.setContentType(posting.getPortfolioType());
		response.setContentLength(posting.getPortfolioLength());
 		ServletOutputStream oStream = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while((len = bin.read(buffer))!= -1) {
			oStream.write(buffer, 0, len);
		}
		bin.close();
		oStream.flush();
		oStream.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
