package osgi.test.service.provider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import osgi.test.service.iface.IService;
import osgi.test.service.impl.DefaultServiceImpl;

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
		System.out.println("service provider, started!");
		/*register service interface*/
		context.registerService(IService.class.getName(), new DefaultServiceImpl(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("service provider, stopped!");
	}

}
