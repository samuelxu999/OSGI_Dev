package osgi.test.service.impl;

import osgi.test.service.iface.IService;

/**********Implement IService to enable function available***********/
public class DefaultServiceImpl implements IService{
	public String getService() {
		return "Service provider offer service-run";
	}
}
