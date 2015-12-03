package net.easipay.dsfc.ws.wss;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.easipay.dsfc.ApplicationContextLogger;
import net.easipay.dsfc.StringUitls;

/**
 * 
 * @author mchen
 * @date 2015-11-25
 */
public class WssConfig
{
    public static WssConfig ins = new WssConfig();

    private Map<String, WssHandlerExecutionChain> wssHandlerExecutingChains = new HashMap<String, WssHandlerExecutionChain>();

    public WssHandlerExecutionChain getWssHandlerExecutionChain(String requestMapping)
    {
	return wssHandlerExecutingChains.get(requestMapping);
    }

    public void setWssHandlerExecutionChain(WssRequestMapping wssRequestMapping, Class<?> clazz, Method method) throws InstantiationException, IllegalAccessException
    {
	Object wssHandler = clazz.newInstance();
	String requestMapping = StringUitls.splicingLink(wssRequestMapping.value());
	wssHandlerExecutingChains.put(requestMapping, new WssHandlerExecutionChain(wssRequestMapping, wssHandler, method));
	ApplicationContextLogger.info(String.format("Success to init wssHandlerExecutingChains[%s]....", requestMapping));
    }

}
