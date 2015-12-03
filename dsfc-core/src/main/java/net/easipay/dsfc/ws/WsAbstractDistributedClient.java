/**
 * 
 */
package net.easipay.dsfc.ws;

import net.easipay.dsfc.ApplicationAdvice;
import net.easipay.dsfc.ApplicationAdviceMessage;
import net.easipay.dsfc.ApplicationContext;
import net.easipay.dsfc.Service;
import net.easipay.dsfc.ServiceCacheHandler;
import net.easipay.dsfc.ServiceNoAvailableException;
import net.easipay.dsfc.ServiceProvider;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class WsAbstractDistributedClient implements WsCallable
{
    private int counter = 0;
    private String serviceId;

    public WsAbstractDistributedClient(String serviceId)
    {
	this.serviceId = serviceId;
    }

    public String getServiceId()
    {
	return serviceId;
    }

    public WsResult call()
    {
	return call(false);
    }

    @Override
    public WsResult call(boolean paramBoolean)
    {
	Service service = null;
	WsResult wsResult = getWsResult();
	ApplicationAdvice applicationAdvice = getApplicationAdvice();
	ApplicationAdviceMessage message = getApplicationAdviceMessage();
	message.setWsResult(wsResult);
	try {
	    service = ApplicationContext.context.getServiceFactory().getService(this.serviceId);
	    String url = service.toServiceUrl();

	    message.setUrl(url.toString());
	    message.setService(service);

	    Object preObject = preprocess(service);

	    applicationAdvice.doBefore(message);

	    wsResult = execute(url, preObject, service);

	    doServiceWithAfter(service);
	    applicationAdvice.doAfter(message);
	} catch ( Exception e ) {
	    if (e instanceof ServiceNoAvailableException) {
		wsResult.setCode(((ServiceNoAvailableException) e).getCode());
		wsResult.setMessage(((ServiceNoAvailableException) e).getLocalMessage());
		doServiceWithNoAvailableService(wsResult);
	    }
	    else if (e instanceof WsClientCalledException) {
		wsResult.setCode(((WsClientCalledException) e).getCode());
		wsResult.setMessage(((WsClientCalledException) e).getLocalMessage());
		doServiceWithThrowing(service, wsResult);
	    }
	    else {
		wsResult.setCode("999998");
		wsResult.setMessage(e.getMessage());
		doServiceWithThrowing(service, wsResult);
	    }
	    applicationAdvice.doThrowing(message);

	    if (e instanceof WsClientCalledException && ServiceProvider.supportRetransmission(service) && counter == 0) {
		counter++;
		if (WsLogger.isDebugEnabled()) {
		    WsLogger.debug("Start retransmission...");
		}
		return call(paramBoolean);
	    }
	}
	if (!wsResult.isSuccess() && !paramBoolean) throw WsClientCalledException.wrap(wsResult.getCode(), wsResult.getMessage());
	return wsResult;
    }

    protected void doServiceWithThrowing(Service service, WsResult wsResult)
    {
	if (service != null && !service._isLocalService()) ServiceCacheHandler.discoveryErrorService(service, wsResult.getCode(), wsResult.getMessage());
    }

    protected void doServiceWithNoAvailableService(WsResult wsResult)
    {
	Service service = new Service();
	service.setServiceId(this.serviceId);
	service.setCacheNum(0);
	doServiceWithThrowing(service, wsResult);
    }

    protected void doServiceWithAfter(Service service)
    {
	if (service != null && !service._isLocalService()) ServiceCacheHandler.recoverErrorService(service);
    }

    protected abstract WsResult getWsResult();

    protected abstract ApplicationAdviceMessage getApplicationAdviceMessage();

    protected abstract ApplicationAdvice getApplicationAdvice();

    protected abstract Object preprocess(Service service) throws Exception;

    protected abstract WsResult execute(String url, Object preObject, Service service) throws Exception;

}
