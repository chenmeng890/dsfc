package net.easipay.dsfc.ws.wss;

import java.lang.reflect.Method;

/**
 * 
 * @author mchen
 * @date 2015-11-25
 */
public class WssHandlerExecutionChain
{
    private WssRequestMapping wssRequestMapping;
    private Object handler;
    private Method method;

    public WssHandlerExecutionChain(WssRequestMapping wssRequestMapping, Object handler, Method method)
    {
	this.wssRequestMapping = wssRequestMapping;
	this.handler = handler;
	this.method = method;
    }

    public WssRequestMapping getWssRequestMapping()
    {
	return wssRequestMapping;
    }

    public void setWssRequestMapping(WssRequestMapping wssRequestMapping)
    {
	this.wssRequestMapping = wssRequestMapping;
    }

    public Object getHandler()
    {
	return handler;
    }

    public void setHandler(Object handler)
    {
	this.handler = handler;
    }

    public Method getMethod()
    {
	return method;
    }

    public void setMethod(Method method)
    {
	this.method = method;
    }

}
