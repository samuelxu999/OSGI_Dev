package osgi.test.event.consumer;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import osgi.test.event.testevent.MyEvent;
import osgi.test.event.iface.IService;
import osgi.test.event.consumer.MyEventHandler;

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
		System.out.println("event consumer: started!");
		
		/*************************test event handle*******************************/
		String[] topics = new String[] {MyEvent.MY_TOPIC};		
		Hashtable<String,String[]> ht = new Hashtable<String,String[]>();
		ht.put(EventConstants.EVENT_TOPIC, topics);
		
		//System.out.printf("%s\n",topics);
		EventHandler myHandler = new MyEventHandler();
		context.registerService(EventHandler.class.getName(), myHandler, ht);
		System.out.println("consumer event handler registered.");
		/**
		* Test service from provider.
		*/
		IService testserv = (IService) context.getService(context.getServiceReference(IService.class.getName()));
		System.out.println(testserv.getService());
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("event consumer: stopped!");
	}

}
