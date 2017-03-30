package osgi.test.service.provider;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import osgi.test.service.iface.IService;
import osgi.test.service.impl.DefaultServiceImpl;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private List<ServiceRegistration> registrations = new ArrayList<ServiceRegistration>();
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("service provider, started!");
		/*register service interface*/
		//context.registerService(IService.class.getName(), new DefaultServiceImpl(), null);
		registrations.add(context.registerService(IService.class.getName(), new DefaultServiceImpl(), null));
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("service provider, stopped!");
		//unregister all service
	    for (ServiceRegistration registration : registrations) {
	        System.out.println("unregistering: "+ registration);
	        registration.unregister();
	    }
	}

}
