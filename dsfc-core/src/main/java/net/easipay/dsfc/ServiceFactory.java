/**
 * 
 */
package net.easipay.dsfc;

import java.util.List;

import net.easipay.dsfc.algorithm.AlgorithmInstance;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ServiceFactory
{
    public Service getService(String serviceId)
    {
	if (ApplicationContext.context.getLocalServices().size() == 0) {
	    throw new ApplicationContextInitializeException("999991", "Context environment is not initialized");
	}

	if (ApplicationContext.context.getLocalServices().containsKey(serviceId)) return ApplicationContext.context.getLocalServices().get(serviceId);

	List<Service> services = ServiceCacheHandler.getCacheService(serviceId);

	if (services.size() == 0) {
	    throw new ServiceNoAvailableException("999991", "No available service.");
	}

	if (services.size() == 1) return services.get(0);

	return AlgorithmInstance.algorithmLookup(services);
    }

}
