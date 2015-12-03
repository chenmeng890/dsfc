/**
 * 
 */
package net.easipay.dsfc;

import java.io.Serializable;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class Service implements Serializable
{
    private static final long serialVersionUID = -4828830348114894745L;

    private String serviceId;
    private String serviceName;
    private String serviceDesc;
    private String serviceProtocol;
    private String serviceIp;
    private String servicePort;
    private String callWay;
    private String routeArithmetic;
    private String arithmeticValue;
    private String serviceSuffix;
    private String serviceSource;
    private String serviceStatus;
    private Integer cacheNum;
    private String memo;

    public boolean _isLocalService()
    {
	return ApplicationContext.context.getLocalServices().containsKey(serviceId);
    }

    public String toServiceUrl()
    {
	if (StringUitls.isNotBlank(serviceSuffix) && serviceSuffix.lastIndexOf("/") == serviceSuffix.length() - 1) serviceSuffix = serviceSuffix.substring(0, serviceSuffix.length() - 1);
	return serviceProtocol.toLowerCase() + "://" + serviceIp + ":" + servicePort + serviceSuffix;
    }

    @Override
    public boolean equals(Object o)
    {
	if (this == o) {
	    return true;
	}

	if (o.getClass() == Service.class) {
	    Service n = (Service) o;
	    return n.serviceId.equals(serviceId) && n.cacheNum.equals(cacheNum);
	}
	return false;
    }

    @Override
    public int hashCode()
    {
	return (serviceId + cacheNum).hashCode();
    }

    @Override
    public String toString()
    {
	return super.toString();
    }

    public String getServiceId()
    {
	return serviceId;
    }

    public void setServiceId(String serviceId)
    {
	this.serviceId = serviceId;
    }

    public String getServiceName()
    {
	return serviceName;
    }

    public void setServiceName(String serviceName)
    {
	this.serviceName = serviceName;
    }

    public String getServiceDesc()
    {
	return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc)
    {
	this.serviceDesc = serviceDesc;
    }

    public String getServiceProtocol()
    {
	return serviceProtocol;
    }

    public void setServiceProtocol(String serviceProtocol)
    {
	this.serviceProtocol = serviceProtocol;
    }

    public String getServiceIp()
    {
	return serviceIp;
    }

    public void setServiceIp(String serviceIp)
    {
	this.serviceIp = serviceIp;
    }

    public String getServicePort()
    {
	return servicePort;
    }

    public void setServicePort(String servicePort)
    {
	this.servicePort = servicePort;
    }

    public String getCallWay()
    {
	return callWay;
    }

    public void setCallWay(String callWay)
    {
	this.callWay = callWay;
    }

    public String getRouteArithmetic()
    {
	return routeArithmetic;
    }

    public void setRouteArithmetic(String routeArithmetic)
    {
	this.routeArithmetic = routeArithmetic;
    }

    public String getArithmeticValue()
    {
	return arithmeticValue;
    }

    public void setArithmeticValue(String arithmeticValue)
    {
	this.arithmeticValue = arithmeticValue;
    }

    public String getServiceSuffix()
    {
	return serviceSuffix;
    }

    public void setServiceSuffix(String serviceSuffix)
    {
	this.serviceSuffix = serviceSuffix;
    }

    public String getServiceSource()
    {
	return serviceSource;
    }

    public void setServiceSource(String serviceSource)
    {
	this.serviceSource = serviceSource;
    }

    public String getServiceStatus()
    {
	return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus)
    {
	this.serviceStatus = serviceStatus;
    }

    public Integer getCacheNum()
    {
	return cacheNum;
    }

    public void setCacheNum(Integer cacheNum)
    {
	this.cacheNum = cacheNum;
    }

    public String getMemo()
    {
	return memo;
    }

    public void setMemo(String memo)
    {
	this.memo = memo;
    }

}
