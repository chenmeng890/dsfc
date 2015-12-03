/**
 * 
 */
package net.easipay.dsfc;

import net.easipay.dsfc.remote.AbstractRemoteFactory;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ApplicationContextPusher
{
    public static void push()
    {
	ServiceProvider serviceProvider = ApplicationContext.context.getServiceProvider();
	AbstractRemoteFactory remoteFactory = ApplicationContext.context.getRemoteFactory();
	
	ApplicationContextLogger.info(String.format("Start to push %s services", serviceProvider.getServices().size()));
	if (serviceProvider.getServices().size() != 0) remoteFactory.getDelegate().registeService(serviceProvider.getServices());
    }
}
