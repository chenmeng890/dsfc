package net.easipay.dsfc.ws.wss;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.easipay.dsfc.ApplicationContext;
import net.easipay.dsfc.ApplicationContextInitializer;
import net.easipay.dsfc.ApplicationContextLogger;
import net.easipay.dsfc.ClassUtils;
import net.easipay.dsfc.Service;
import net.easipay.dsfc.ServiceAutowiredExecutor;
import net.easipay.dsfc.StringUitls;
import net.easipay.dsfc.ws.WsLogger;

/**
 * 
 * @author mchen
 * @date 2015-11-25
 */
public class WssServiceAutowiredExecutor implements ServiceAutowiredExecutor
{
    private static Map<String, WssRequestMapping> wssRequestMappingRegistry = new HashMap<String, WssRequestMapping>();

    public void execute() throws Exception
    {
	loadWssRequestMappingRegistry();
	autowiredAnnoService();
    }

    protected void loadWssRequestMappingRegistry() throws InstantiationException, IllegalAccessException
    {
	ApplicationContextLogger.info("Start to autowired...");
	List<String> wssPackages = ApplicationContext.context.getApplicationRegister().getWssPackages();
	Set<Class<?>> classSet = new HashSet<Class<?>>();
	for (String wssPackage : wssPackages) {
	    classSet.addAll(ClassUtils.getClasses(wssPackage));
	}

	for (Class<?> clazz : classSet) {
	    Method[] declaredMethods = clazz.getDeclaredMethods();
	    for (Method method : declaredMethods) {
		WssRequestMapping wssRequestMapping = method.getAnnotation(WssRequestMapping.class);
		if (wssRequestMapping == null) {
		    continue;
		}

		if (StringUitls.isBlank(wssRequestMapping.value()) || StringUitls.isBlank(wssRequestMapping.service()) || StringUitls.isBlank(wssRequestMapping.desc())) {
		    throw new WssException("999981", "There are error requestMapping [value,service,desc must be not empty], please check.");
		}

		String requestMapping = StringUitls.splicingLink(wssRequestMapping.value());

		if (wssRequestMappingRegistry.containsKey(requestMapping)) {
		    throw new WssException("999981", "There are duplicate requestMapping, please check.");
		}

		wssRequestMappingRegistry.put(requestMapping, wssRequestMapping);

		ApplicationContextLogger.info(String.format("Success to init wssRequestMappingRegistry[%s]....", requestMapping));

		WssConfig.ins.setWssHandlerExecutionChain(wssRequestMapping, clazz, method);
	    }
	}
    }

    protected void autowiredAnnoService() throws Exception
    {
	if (ApplicationContext.context.getApplicationRegister().getServiceAutowiredExecutor() == null) {
	    WsLogger.info("Fail to automatic registration... ServiceAutowiredExecutor is empty");
	    return;
	}

	if (ApplicationContext.context.getApplicationRegister().getServletMappingPath() == null) {
	    WsLogger.info("Fail to automatic registration... lack the servletMappingPath of applicationContext");
	    return;
	}

	if (wssRequestMappingRegistry.size() == 0) {
	    WsLogger.info("Fail to automatic registration... wssRequestMappingRegistry is empty");
	    return;
	}
	WsLogger.info("Start automatic registration...");
	ApplicationContextInitializer initializer = new ApplicationContextInitializer();
	List<Service> serviceConfigs = new ArrayList<Service>();
	for (WssRequestMapping wssRequestMapping : wssRequestMappingRegistry.values()) {
	    Service service = new Service();
	    service.setServiceId(wssRequestMapping.service());
	    service.setServiceName(wssRequestMapping.desc());
	    service.setServiceSuffix(StringUitls.splicingLink(ApplicationContext.context.getApplication().getContextPath(), ApplicationContext.context.getApplicationRegister().getServletMappingPath(), wssRequestMapping.value()));
	    serviceConfigs.add(service);
	}
	initializer.setServiceConfigs(serviceConfigs);
    }
  

}
