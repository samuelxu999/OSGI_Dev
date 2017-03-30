package com.osgicn.demo.webentry;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

import com.osgicn.demo.webentry.servlet.WeatherServlet;


public class Activator implements BundleActivator, ServiceListener {	
	
	private BundleContext bundleContext;
	
	private ServiceReference ref;
	
	private Servlet servlet;
	
	
	public void start(BundleContext context) throws Exception {
		bundleContext = context;
		
		//initialize WeatherServlet class
		servlet = new WeatherServlet(bundleContext);
		
		registerServlet();
		
		context.addServiceListener(this, "(objectClass=" + HttpService.class.getName() + ")");
	}

	public void stop(BundleContext context) throws Exception {
		try {			
            unregisterServlet();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        servlet = null;
        bundleContext = null;
        ref = null;
	}

	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()){
            case ServiceEvent.REGISTERED:            	
                registerServlet();
                break;

            case ServiceEvent.UNREGISTERING:            	
                unregisterServlet();
                break;
        }
	}
	
	/*
	 * register web application
	 */
	private void registerServlet(){
		if (ref == null){
            ref = bundleContext.getServiceReference(HttpService.class.getName());
        }
	 
        if (ref != null){
            try {
                HttpService http = (HttpService) bundleContext.getService(ref);
                if(null != http){
                	
					http.registerServlet("/osgicn/index", servlet, null, null);
					
					http.registerResources("/osgicn/index.html","/page/index.html",null);
					System.out.println("Html webpage has been registered��ready for visit\n"
							+ "Test page: http://localhost:8081/osgicn/index.html");
                }
            } 
			catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
	
	/*
	 * unregister web application
	 */
	private void unregisterServlet(){
		if (ref != null) {
            try {
                HttpService http = (HttpService) bundleContext.getService(ref);
                if(null != http){
	                http.unregister("/osgicn/index");
	                http.unregister("/osgicn/page");	                
                }
            }
            catch(Exception e){
            	e.printStackTrace();
            }
        }
	}
}
