package osgi.test.event.consumer;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import osgi.test.event.testevent.MyEvent;
import osgi.test.event.iface.IService;
import osgi.test.event.consumer.MyEventHandler;

//import helloworld.event.MyEvent;
//import helloworld.service.IHello;
//import osgi.test.helloworld2.MyEventHandler;

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
		System.out.println("event consumer, started!");
		
		/*test event handle*/
		String[] topics = new String[] {MyEvent.MY_TOPIC};		
		Hashtable<String,String[]> ht = new Hashtable<String,String[]>();
		ht.put(EventConstants.EVENT_TOPIC, topics);
		//String[] ttt= new String[] {"RRRRR"};
		//System.out.printf("%s\n",topics);
		EventHandler myHandler = new MyEventHandler();
		context.registerService(EventHandler.class.getName(), myHandler, ht);
		System.out.println("event handler registered");
		/**
		* Test hello service from bundle1.
		*/
		IService hello = (IService) context.getService(context.getServiceReference(IService.class.getName()));
		System.out.println(hello.getService());
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("event consumer, stopped!");
	}

}
