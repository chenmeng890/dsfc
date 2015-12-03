/**
 * 
 */
package net.easipay.dsfc;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import net.easipay.dsfc.cache.CacheManagementContext;
import net.easipay.dsfc.ws.WsTimeout;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ApplicationContextInitializer
{
    public void initApplicationContext() throws Exception
    {
	CacheManagementContext.initCacheContext();

	if (ApplicationContext.context.getApplicationRegister().isAutowiredService()) ApplicationContext.context.getApplicationRegister().getServiceAutowiredExecutor().execute();

	ApplicationContext.context.setApplicationContextInitFlag(true);

	ApplicationContextPusher.push();

    }

    public void setApplicationRegister(ApplicationRegister applicationRegister)
    {
	ApplicationContext.context.setApplicationRegister(applicationRegister);
    }

    public void setConnectTimeout(int connectTimeout)
    {
	WsTimeout.setConnectTimeout(connectTimeout);
    }

    public void setReadTimeout(int readTimeout)
    {
	WsTimeout.setReadTimeout(readTimeout);
    }

    /**
     * Initialize the given application service.
     */
    public void setServiceConfigs(List<Service> serviceConfigs)
    {
	ApplicationContextLogger.info("Start to setServiceConfigs...");
	Application application = ApplicationContext.context.getApplication();
	for (Service service : serviceConfigs) {
	    if (StringUitls.isBlank(service.getServiceId()) || StringUitls.isBlank(service.getServiceSuffix()) || StringUitls.isBlank(service.getServiceName())) {
		throw new ApplicationContextInitializeException("999981", "serviceId, serviceName or serviceSuffix must not be null.");
	    }

	    if (StringUitls.isBlank(service.getServiceIp())) {
		service.setServiceIp(application.getLocalIp());
	    }

	    if (StringUitls.isBlank(service.getServicePort())) {
		service.setServicePort(String.valueOf(application.getLocalPort()));
	    }

	    if (StringUitls.isBlank(service.getServiceProtocol())) {
		service.setServiceProtocol("HTTP");
	    }

	    if (!service.getServiceSuffix().startsWith(ApplicationContext.context.getApplication().getContextPath())) {
		service.setServiceSuffix(StringUitls.splicingLink(application.getContextPath(), service.getServiceSuffix()));
	    }

	    if (StringUitls.isBlank(service.getServiceSource())) {
		service.setServiceSource(ApplicationContext.context.getApplication().getName());
	    }

	    if (StringUitls.isBlank(service.getCallWay())) {
		service.setCallWay("POST");
	    }

	    service.setServiceStatus("01");
	    service.setRouteArithmetic("0");
	    service.setArithmeticValue("30");
	    service.setMemo("");
	    ApplicationContext.context.getServiceProvider().addService(service);
	    ApplicationContextLogger.info(String.format("Success to init service[serviceUrl - %s]....", service.toServiceUrl()));
	}
    }

    /**
     * Initialize the dsfs server service.
     * 
     * @param dsfsConfig
     */
    public void setDsfsConfig(Map<String, String> dsfsConfig)
    {
	initLocalContextEnv();

	ApplicationContextLogger.info("Start to setDsfsConfig...");
	if (!dsfsConfig.containsKey("protocol") || !dsfsConfig.containsKey("ip") || !dsfsConfig.containsKey("port") || !dsfsConfig.containsKey("context")) {
	    throw new ApplicationContextInitializeException("999982", "ip , port or suffix must be not null");
	}
	Service service = new Service();
	service.setServiceId("dsfc-0001");
	service.setServiceName("服务注册接口");
	service.setServiceDesc("服务注册接口");
	service.setServiceProtocol(dsfsConfig.get("protocol"));
	service.setServiceIp(dsfsConfig.get("ip"));
	service.setServicePort(dsfsConfig.get("port"));
	service.setCallWay("POST");
	service.setRouteArithmetic("0");
	service.setArithmeticValue("100");
	service.setServiceSuffix(StringUitls.splicingLink(dsfsConfig.get("context"), "/interface/registeService"));
	service.setServiceSource(ApplicationContext.context.getApplication().getName());
	service.setServiceStatus(ServiceConstant.SERVICE_STATUS.AVAILABLE);
	service.setCacheNum(-1);
	service.setMemo("");
	ApplicationContext.context.getLocalServices().put("dsfc-0001", service);

	service = new Service();
	service.setServiceId("dsfc-0002");
	service.setServiceName("服务查找接口");
	service.setServiceDesc("服务查找接口");
	service.setServiceProtocol(dsfsConfig.get("protocol"));
	service.setServiceIp(dsfsConfig.get("ip"));
	service.setServicePort(dsfsConfig.get("port"));
	service.setCallWay("POST");
	service.setRouteArithmetic("0");
	service.setArithmeticValue("100");
	service.setServiceSuffix(StringUitls.splicingLink(dsfsConfig.get("context"), "/interface/searchService"));
	service.setServiceSource(ApplicationContext.context.getApplication().getName());
	service.setServiceStatus(ServiceConstant.SERVICE_STATUS.AVAILABLE);
	service.setCacheNum(-2);
	service.setMemo("");
	ApplicationContext.context.getLocalServices().put("dsfc-0002", service);

	service = new Service();
	service.setServiceId("dsfc-0003");
	service.setServiceName("监控管理接口");
	service.setServiceDesc("监控管理接口");
	service.setServiceProtocol(dsfsConfig.get("protocol"));
	service.setServiceIp(dsfsConfig.get("ip"));
	service.setServicePort(dsfsConfig.get("port"));
	service.setCallWay("POST");
	service.setRouteArithmetic("0");
	service.setArithmeticValue("100");
	service.setServiceSuffix(StringUitls.splicingLink(dsfsConfig.get("context"), "/interface/manageMonitor"));
	service.setServiceSource(ApplicationContext.context.getApplication().getName());
	service.setServiceStatus(ServiceConstant.SERVICE_STATUS.AVAILABLE);
	service.setCacheNum(-3);
	service.setMemo("");
	ApplicationContext.context.getLocalServices().put("dsfc-0003", service);

	service = new Service();
	service.setServiceId("dsfc-0004");
	service.setServiceName("参数查找接口");
	service.setServiceDesc("参数查找接口");
	service.setServiceProtocol(dsfsConfig.get("protocol"));
	service.setServiceIp(dsfsConfig.get("ip"));
	service.setServicePort(dsfsConfig.get("port"));
	service.setCallWay("POST");
	service.setRouteArithmetic("0");
	service.setArithmeticValue("100");
	service.setServiceSuffix(StringUitls.splicingLink(dsfsConfig.get("context"), "/interface/searchParameter"));
	service.setServiceSource(ApplicationContext.context.getApplication().getName());
	service.setServiceStatus(ServiceConstant.SERVICE_STATUS.AVAILABLE);
	service.setCacheNum(-4);
	service.setMemo("");
	ApplicationContext.context.getLocalServices().put("dsfc-0004", service);

	ApplicationContextLogger.info("Success to init localService[DsfsConfig - dsfc-0001 - dsfc-0002 - dsfc-0003 - dsfc-0004]....");
    }

    private void initLocalContextEnv()
    {
	try {
	    ApplicationContextLogger.info("Start to initLocalContextEnv...");
	    ApplicationContext.context.getApplication().setLocalIp(getLocalIp());

	    ArrayList<MBeanServer> listMBeanServer = MBeanServerFactory.findMBeanServer(null);
	    if (listMBeanServer.size() == 0) {
		throw new IllegalStateException("没有发现JVM中关联的MBeanServer.");
	    }
	    MBeanServer mBeanServer = listMBeanServer.get(0);

	    ApplicationContextLogger.info("Found MBeanServer...");

	    Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName("*:type=Connector,port=*"), null);

	    if (objectNames != null && objectNames.size() > 0) {
		for (ObjectName objectName : objectNames) {
		    String port = objectName.getKeyProperty("port");
		    if ("8009".equals(port) || "8010".equals(port) || "8011".equals(port)) continue;
		    ApplicationContext.context.getApplication().setLocalPort(Integer.valueOf(port));
		    ApplicationContextLogger.info(String.format("Success to initLocalContextEnv[ port - %s ]....", port));
		}
	    }

	    objectNames = mBeanServer.queryNames(new ObjectName("*:type=Loader,context=*,host=*"), null);
	    if (objectNames != null && objectNames.size() > 0) {
		String context = objectNames.iterator().next().getKeyProperty("context");
		ApplicationContext.context.getApplication().setContextPath(context);
		ApplicationContext.context.getApplication().setName(context.replaceAll("/", ""));
		ApplicationContextLogger.info(String.format("Success to initLocalContextEnv[ context - %s ]....", context));
	    }

	} catch ( Exception e ) {
	    ApplicationContextLogger.error(e.getMessage());
	}
    }

    private String getLocalIp()
    {
	try {
	    for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
		NetworkInterface item = e.nextElement();

		for (InterfaceAddress address : item.getInterfaceAddresses()) {
		    if (address.getAddress() instanceof Inet4Address) {
			Inet4Address inet4Address = (Inet4Address) address.getAddress();
			if (!inet4Address.isLinkLocalAddress() && !inet4Address.isLoopbackAddress() && !inet4Address.isMCGlobal() && !inet4Address.isMulticastAddress()) {
			    return inet4Address.getHostAddress();
			}
		    }
		}
	    }

	} catch ( IOException ex ) {
	    ex.printStackTrace();
	}
	return "";
    }

}
