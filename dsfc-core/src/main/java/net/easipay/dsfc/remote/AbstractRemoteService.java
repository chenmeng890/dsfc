/**
 * 
 */
package net.easipay.dsfc.remote;

import java.util.List;

import net.easipay.dsfc.ApplicationContextLogger;
import net.easipay.dsfc.Service;
import net.easipay.dsfc.UnifiedConfig;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class AbstractRemoteService
{
    public final static String DEFAULT_REMOTE_CALL = "net.easipay.dsfc.remote.JwsHttpRemoteService";
    
    public static ClassLoader getClassLoader()
    {
	return AbstractRemoteService.class.getClassLoader();
    }

    @SuppressWarnings("unchecked")
    public static Class<? extends AbstractRemoteService> loadClass(String name)
    {
	try {
	    return (Class<? extends AbstractRemoteService>) getClassLoader().loadClass(name);
	} catch ( ClassNotFoundException e ) {
	    ApplicationContextLogger.error("AbstractRemoteService.loadClass throw exception", e);
	}
	return null;
    }

    public abstract void registeService(List<Service> services);

    public abstract List<Service> searchService(List<String> serviceIds);

    public abstract void manageMonitor(String serviceId, Integer cacheNum, String errorNo, String errorDesc, String operateType);

    public abstract List<UnifiedConfig> searchParameter(List<SearchParameterQueryForm> forms);
}
