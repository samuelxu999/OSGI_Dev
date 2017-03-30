package osgi.test.service.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import osgi.test.service.iface.IService;

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
		System.out.println("service consumer, started!");
		
		/*test hello world from exported bundle*/
		IService serv =(IService)context.getService(context.getServiceReference(IService.class.getName()));
		System.out.println(serv.getService());
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("service consumer, stopped!");
	}

}
