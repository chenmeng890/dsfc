/**
 * 
 */
package net.easipay.dsfc.ws;

import java.util.List;

import net.easipay.dsfc.ApplicationContext;
import net.easipay.dsfc.ApplicationContextInitializeException;
import net.easipay.dsfc.ApplicationContextInitializer;
import net.easipay.dsfc.ApplicationContextLogger;
import net.easipay.dsfc.Service;
import net.easipay.dsfc.StringUitls;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsApplicationContextInitializer extends ApplicationContextInitializer
{
    /**
     * Initialize the given application service.
     */
    public void setWsServiceConfigs(List<Service> wsServiceConfigs)
    {
	ApplicationContextLogger.info("Start to setWsServiceConfigs...");
	for (Service service : wsServiceConfigs) {
	    if (StringUitls.isBlank(service.getServiceId()) || StringUitls.isBlank(service.getServiceIp()) || StringUitls.isBlank(service.getServicePort()) || StringUitls.isBlank(service.getServiceSuffix()) || StringUitls.isBlank(service.getServiceSource())
		    || StringUitls.isBlank(service.getServiceName())) {
		throw new ApplicationContextInitializeException("999981", "serviceId, serviceIp,servicePort,ServiceSource, serviceName or serviceSuffix must not be null.");
	    }

	    if (StringUitls.isBlank(service.getServiceProtocol())) {
		service.setServiceProtocol("HTTP");
	    }

	    if (StringUitls.isBlank(service.getCallWay())) {
		service.setCallWay("POST");
	    }

	    if (StringUitls.isBlank(service.getServiceStatus())) {
		service.setServiceStatus("01");
	    }

	    if (StringUitls.isBlank(service.getRouteArithmetic())) {
		service.setRouteArithmetic("0");
	    }

	    if (StringUitls.isBlank(service.getArithmeticValue())) {
		service.setArithmeticValue("30");
	    }
	    if (StringUitls.isBlank(service.getMemo())) {
		service.setMemo("");
	    }
	    ApplicationContext.context.getLocalServices().put(service.getServiceId(), service);
	    ApplicationContextLogger.info(String.format("Success to init service[serviceUrl - %s]....", service.toServiceUrl()));
	}
    }

}
