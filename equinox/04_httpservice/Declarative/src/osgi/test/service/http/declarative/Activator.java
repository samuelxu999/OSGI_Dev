package osgi.test.service.http.declarative;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/* Embed the servlet-jetty container inside OSGi container-Declarative
 * This sample is mainly to show how to construct OSGI http service through Declarative Registration
 * 1) Define the request URLs that your bundle can handle in a file called plugin.xml.
 */
public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		System.out.println("OSGI web-declarative:Hello World!!");
		
		//printout register status
		System.out.println("Html webpage and service has been registered£¬ready for test:\n"
				+ "Test HelloWorldServlet: 'http://localhost:8081/declarative'\n"
				+ "Test declarative.html: 'http://localhost:8081/declarative.html'");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("OSGI web-declarative:Goodbye World!!");  
	}

}
