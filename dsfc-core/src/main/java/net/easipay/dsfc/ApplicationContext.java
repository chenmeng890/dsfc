/**
 * 
 */
package net.easipay.dsfc;

import java.util.HashMap;
import java.util.Map;

import net.easipay.dsfc.remote.AbstractRemoteFactory;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ApplicationContext
{
    public final static ApplicationContext context = new ApplicationContext();

    private boolean applicationContextInitFlag = false;

    private final Application application = new Application();

    private final ServiceProvider serviceProvider = new ServiceProvider();

    private final AbstractRemoteFactory remoteFactory = new AbstractRemoteFactory();

    private final ServiceFactory serviceFactory = new ServiceFactory();

    private final Map<String, Service> localServices = new HashMap<String, Service>();

    private ApplicationRegister applicationRegister = new ApplicationRegister();

    /**
     * @param applicationContextInitFlag
     *            the applicationContextInitFlag to set
     */
    public void setApplicationContextInitFlag(boolean applicationContextInitFlag)
    {
	this.applicationContextInitFlag = applicationContextInitFlag;
    }

    /**
     * @return the applicationContextInitFlag
     */
    public boolean isApplicationContextInitFlag()
    {
	return applicationContextInitFlag;
    }

    /**
     * @return the localServices
     */
    public Map<String, Service> getLocalServices()
    {
	return localServices;
    }

    /**
     * @return the serviceProvider
     */
    public ServiceProvider getServiceProvider()
    {
	return serviceProvider;
    }

    /**
     * @return the remoteFactory
     */
    public AbstractRemoteFactory getRemoteFactory()
    {
	return remoteFactory;
    }

    /**
     * @return the serviceFactory
     */
    public ServiceFactory getServiceFactory()
    {
	return serviceFactory;
    }

    /**
     * @return the application
     */
    public Application getApplication()
    {
	return application;
    }

    /**
     * @return the applicationRegister
     */
    public ApplicationRegister getApplicationRegister()
    {
	return applicationRegister;
    }
    
    /**
     * @param applicationRegister the applicationRegister to set
     */
    public void setApplicationRegister(ApplicationRegister applicationRegister)
    {
	this.applicationRegister = applicationRegister;
    }
}
