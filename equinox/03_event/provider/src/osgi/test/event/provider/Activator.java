package osgi.test.event.provider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import osgi.test.event.iface.IService;
import osgi.test.event.impl.DefaultServiceHandler;

public class Activator implements BundleActivator {

	private static BundleContext context;

	public static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("event provider: started!");
		/*register service interface*/
		context.registerService(IService.class.getName(), new DefaultServiceHandler(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("event provider: stopped!");
		
	}

}
