package osgi.test.event.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.EventAdmin;


import osgi.test.event.provider.Activator;
import osgi.test.event.testevent.MyEvent;
//import osgi.test.event.impl;
import osgi.test.event.iface.IService;

public class DefaultServiceHandler implements IService{
	BundleContext context=Activator.getContext();
	EventAdmin eventAdmin;
	public String getService() {
		//post a event
		ServiceReference ref = context.getServiceReference(EventAdmin.class.getName());
		if(ref!=null) {
			eventAdmin = (EventAdmin)context.getService(ref);
			if(eventAdmin!=null) {
				System.out.println("post event started");
				eventAdmin.postEvent(new MyEvent());
				System.out.println("post event returned");
			}
		}
		return "Hello osgi,service";
	}
}
