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
public class ApplicationRegister
{
    private List<String> wssPackages = new ArrayList<String>();

    private boolean autowiredService = false;

    private String servletMappingPath;

    private ServiceAutowiredExecutor serviceAutowiredExecutor;

    public List<String> getWssPackages()
    {
	return wssPackages;
    }

    public void setWssPackages(List<String> wssPackages)
    {
	this.wssPackages = wssPackages;
    }

    public boolean isAutowiredService()
    {
	return autowiredService;
    }

    public void setAutowiredService(boolean autowiredService)
    {
	this.autowiredService = autowiredService;
    }

    public String getServletMappingPath()
    {
	return servletMappingPath;
    }

    public void setServletMappingPath(String servletMappingPath)
    {
	this.servletMappingPath = servletMappingPath;
    }

    public ServiceAutowiredExecutor getServiceAutowiredExecutor()
    {
	return serviceAutowiredExecutor;
    }

    public void setServiceAutowiredExecutor(ServiceAutowiredExecutor serviceAutowiredExecutor)
    {
	this.serviceAutowiredExecutor = serviceAutowiredExecutor;
    }

}
