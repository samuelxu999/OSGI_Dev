package osgi.test.event.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.EventAdmin;


import osgi.test.event.provider.Activator;
import osgi.test.event.testevent.*;
import osgi.test.event.iface.IService;

public class DefaultServiceHandler implements IService{
	BundleContext context=Activator.getContext();
	EventAdmin eventAdmin_post;
	EventAdmin eventAdmin_send;
	public String getService() {
		//get registered service
		ServiceReference<?> ref = context.getServiceReference(EventAdmin.class.getName());
		if(ref!=null) {
			eventAdmin_post = (EventAdmin)context.getService(ref);
			if(eventAdmin_post!=null) {
				System.out.println("provider post event: started.");
				/*Asynchronous event delivery is initiated by the postEvent method*/
				eventAdmin_post.postEvent(new MyEvent("0",null));
				System.out.println("provider post event: returned.");
			}
			eventAdmin_send = (EventAdmin)context.getService(ref);
			if(eventAdmin_send!=null) {
				System.out.println("provider send event: started.");				
				/*Synchronous event delivery is initiated by the sendEvent method*/
				eventAdmin_send.sendEvent(new MyEvent("1",null));
				System.out.println("provider send event: returned.");
			}
		}
		return "Welcome to osgi service from provider!";
	}
}
