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


@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageServlet() {
        super();
    }

    // using https://coderanch.com/wiki/660125/Image-Servlet
    // Hansen, Mark. “Image Servlet (Wiki Forum at Coderanch).” Coderanch, 1 Jan. 2016, coderanch.com/wiki/660125/Image-Servlet.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Posting posting = PostingDAO.getPostingById(Long.parseLong(request.getParameter("ps")));
		
		
		//  take blob from input array
		InputStream is = posting.getPortfolio();
		byte[] buff = new byte[8000];
		int bytesRead = 0;
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		// write the input stream as bytes
		while ((bytesRead = is.read(buff))!= -1) {
			bao.write(buff, 0, bytesRead);
		}
		byte[] data = bao.toByteArray();
		
		// convert the byte array to an input array so it can be streamed
		// as a byte
		ByteArrayInputStream  bin = new ByteArrayInputStream(data);
		
		// retrieve necessary buffering data
		response.setContentType(posting.getPortfolioType());
		response.setContentLength(posting.getPortfolioLength());
 		ServletOutputStream oStream = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		
		// send image as outputstream to be served by servlet
		while((len = bin.read(buffer))!= -1) {
			oStream.write(buffer, 0, len);
		}
		bin.close();
		oStream.flush();
		oStream.close();
		
		
	}

}
