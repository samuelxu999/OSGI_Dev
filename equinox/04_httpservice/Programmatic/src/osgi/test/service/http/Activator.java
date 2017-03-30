package osgi.test.service.http;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import osgi.test.service.http.servlet.HttpServiceTracker;


/* Embed the servlet-jetty container inside OSGi container-Programmatic
  This sample is mainly to show how to construct OSGI http service through Programmatic Registration
   1) Retrieve HttpService from OSGi's service registry, 
   2) Call methods on it to register the request URLs that the bundle can service.
 */
public class Activator implements BundleActivator {

	private static BundleContext context;
	
	//The ServiceTracker class simplifies using services from the Framework's service registry.
	ServiceTracker<?, ?> httpServiceTracker; 
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("OSGI web-programmatic:Hello World!!");  
		httpServiceTracker = new HttpServiceTracker(context);
		/*
		 * Open this ServiceTracker and begin tracking services
		 */
		httpServiceTracker.open();  
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("OSGI web-programmatic:Goodbye World!!");  
		/*
		 * Close this ServiceTracker.
		 * This method should be called when this ServiceTracker should end the tracking of services.
		 */
		httpServiceTracker.close();  
		httpServiceTracker = null; 
	}

}
