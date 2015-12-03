/**
 * 
 */
package net.easipay.dsfc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ServiceProvider
{
    private List<Service> services = new ArrayList<Service>();

    public void addService(Service service)
    {
	this.services.add(service);
    }

    /**
     * @return the webApplicationServices
     */
    public List<Service> getServices()
    {
	return services;
    }

    public static boolean supportRetransmission(Service service)
    {
	return service != null && "HTTP".equalsIgnoreCase(service.getServiceProtocol()) && !ApplicationContext.context.getLocalServices().containsKey(service.getServiceId());
    }
}
