/**
 * 
 */
package net.easipay.dsfc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import net.easipay.dsfc.cache.CacheHandler;
import net.easipay.dsfc.cache.CacheManager;
import net.easipay.dsfc.remote.AbstractRemoteService;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ServiceCacheHandler implements CacheHandler
{
    /**
     * The keys of all the services.
     */
    public static final String NET_EASIPAY_DSFC_SERVICE = "net.easipay.dsfc.Service";

    /**
     * Collection of all the service id.
     */
    private static HashSet<String> set = new HashSet<String>();

    /**
     * Collection of exception services
     */
    private static HashSet<Service> failoverSet = new HashSet<Service>();

    @Override
    public Object execute() throws Exception
    {
	AbstractRemoteService delegate = ApplicationContext.context.getRemoteFactory().getDelegate();
	List<Service> services = delegate.searchService(Arrays.asList(set.toArray(new String[set.size()])));
	return services;
    }

    @SuppressWarnings("unchecked")
    public static List<Service> getCacheService(String serviceId)
    {
	boolean contains = set.contains(serviceId);
	set.add(serviceId);

	List<Service> _services = new ArrayList<Service>();

	List<Service> services = (List<Service>) CacheManager.getCache(ServiceCacheHandler.NET_EASIPAY_DSFC_SERVICE, !contains);
	if (services == null) return _services;

	for (Service service : services) {
	    if (service.getServiceId().equals(serviceId) && ServiceConstant.SERVICE_STATUS.AVAILABLE.equals(service.getServiceStatus())) {
		_services.add(service);
	    }
	}
	return _services;
    }

    public static synchronized void discoveryErrorService(Service service, String code, String message)
    {
	try {
	    List<Service> services = getCacheService(service.getServiceId());
	    for (int i = 0; i < services.size(); i++) {
		if (services.get(i).equals(service)) service.setServiceStatus("02");
	    }
	    failoverSet.add(service);

	    AbstractRemoteService delegate = ApplicationContext.context.getRemoteFactory().getDelegate();
	    delegate.manageMonitor(service.getServiceId(), service.getCacheNum(), code, message, "01");
	} catch ( Exception e ) {
	    ApplicationContextLogger.error("Fail to discoveryErrorService", e);
	}
    }

    public static synchronized void recoverErrorService(Service service)
    {
	try {
	    if (failoverSet.contains(service)) {
		failoverSet.remove(service);

		AbstractRemoteService delegate = ApplicationContext.context.getRemoteFactory().getDelegate();
		delegate.manageMonitor(service.getServiceId(), service.getCacheNum(), "000000", "成功", "02");
	    }
	} catch ( Exception e ) {
	    ApplicationContextLogger.error("Fail to recoverErrorService", e);
	}

    }

}
