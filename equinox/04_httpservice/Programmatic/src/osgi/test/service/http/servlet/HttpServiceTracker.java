package osgi.test.service.http.servlet;

import org.osgi.framework.BundleContext;  
import org.osgi.framework.ServiceReference;  
import org.osgi.service.http.HttpService;  
import org.osgi.util.tracker.ServiceTracker;

public class HttpServiceTracker extends ServiceTracker<Object, Object>{
	public HttpServiceTracker(BundleContext context) {  
		super(context, HttpService.class.getName(), null);  
	}
	
	/* Override function to register servlet
	 * Default implementation of the ServiceTrackerCustomizer.addingService method.
	 * This method is only called when this ServiceTracker has been constructed with a null ServiceTrackerCustomizer argument.
	 * Such a callback method that will be invoked once HttpService is available.
	 */
	public Object addingService(ServiceReference reference) {  
		HttpService httpService = (HttpService) context.getService(reference);  
		try { 
			/* Register resource:
			 * The registerResources() method is used for mapping html source to the httpService class
			 * Function: Map the '/pages/helloworld.html' file to '/helloworld.html' URL
			 * After this, whenever you request http://localhost/helloworld.html, 
			 * the HttpService will serve helloworld.html to the user.
			 */
			httpService.registerResources("/programmatic.html", "/pages/programmatic.html", null);
			
			/* Register servlet
			 * The registerServlet() method is used for mapping a URL to the HelloWorldServlet class
			 * Function: Map the 'HelloWorldServlet ' class to '/helloworld' URL
			 * After this, whenever you request http://localhost/helloworld,
			 * the HttpService will serve HelloWorldServlet to the user.
			 */
			httpService.registerServlet("/programmatic", new HelloWorldServlet(), null, null);  
			
			//printout register status
			System.out.println("Html webpage and service has been registered£¬ready for test:\n"
					+ "Test HelloWorldServlet: 'http://localhost:8081/programmatic'\n"
					+ "Test programmatic.html: 'http://localhost:8081/programmatic.html'");
		} 
		catch (Exception e) {  
			e.printStackTrace();  
		}  
		return httpService;  
	}  
	
	/* Override function to unregister servlet
	 * Default implementation of the ServiceTrackerCustomizer.removedService method.
	 * This method is only called when this ServiceTracker has been constructed with a null ServiceTrackerCustomizer argument.
	 * Function: such method will unregister both the /helloworld.html and the /helloworld URI
	 */
	public void removedService(ServiceReference reference, Object service) {  
		HttpService httpService = (HttpService) service; 
		
		//unregister resource
		httpService.unregister("/programmatic.html"); 
		
		//unregister servlet
		httpService.unregister("/programmatic");  
		
		//printout unregister status
		System.out.println("Html webpage and service has been unregistered.");
		
		super.removedService(reference, service);  
	}  
}
