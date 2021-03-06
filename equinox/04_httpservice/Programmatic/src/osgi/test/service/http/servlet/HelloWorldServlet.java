package osgi.test.service.http.servlet;

import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
/*
 * Implementation HelloWorldServlet class to show hwo to use Servlet in OSGI
 */
public class HelloWorldServlet extends HttpServlet{  
	
	private static final long serialVersionUID = 1L;
	
	//Implement doGet action to print text on web page
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.setContentType("text/html");  
		resp.getWriter().println("<h3>Hello OSGI servlet, from HelloWorldServlet<br>"
				+ "Embed the servlet-jetty container inside OSGi container-Programmatic</h3>");  
	}  
}  
