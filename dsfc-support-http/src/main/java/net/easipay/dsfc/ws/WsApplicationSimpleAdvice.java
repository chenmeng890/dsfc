/**
 * 
 */
package net.easipay.dsfc.ws;

import net.easipay.dsfc.ApplicationAdvice;
import net.easipay.dsfc.ApplicationAdviceException;
import net.easipay.dsfc.ApplicationAdviceMessage;
import net.easipay.dsfc.StringUitls;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsApplicationSimpleAdvice implements ApplicationAdvice
{
    public static final WsApplicationSimpleAdvice simple = new WsApplicationSimpleAdvice();

    @Override
    public void doBefore(ApplicationAdviceMessage message) throws ApplicationAdviceException
    {
	if (message instanceof WsApplicationSimpleAdviceMessage) {
	    if (WsLogger.isDebugEnabled()) {
		WsApplicationSimpleAdviceMessage _message = (WsApplicationSimpleAdviceMessage) message;
		_message.setTimemillis(System.currentTimeMillis());

		WsLogger.debug(String.format("Service - %s", _message.getService().getServiceName()));
		WsLogger.debug(String.format("HttpUrl - %s", _message.getUrl()));
		WsLogger.debug(String.format("Request - %s", StringUitls.skipsString(_message.getInput(), 5000)));
	    }
	}

    }

    @Override
    public void doAround(ApplicationAdviceMessage message) throws ApplicationAdviceException
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void doThrowing(ApplicationAdviceMessage message) throws ApplicationAdviceException
    {
	if (message instanceof WsApplicationSimpleAdviceMessage) {
	    WsApplicationSimpleAdviceMessage _message = (WsApplicationSimpleAdviceMessage) message;
	    WsResult wsResult = _message.getWsResult();

	    if (wsResult != null) WsLogger.error(String.format("Exception - [code = %s , message = %s]", wsResult.getCode(), wsResult.getMessage()));

	    if (WsLogger.isDebugEnabled()) {
		_message.setTimemillis(System.currentTimeMillis() - _message.getTimemillis());
		WsLogger.info(String.format("Call time - %s", _message.getTimemillis()));
	    }
	}
    }

    @Override
    public void doAfter(ApplicationAdviceMessage message) throws ApplicationAdviceException
    {
	if (message instanceof WsApplicationSimpleAdviceMessage) {
	    if (WsLogger.isDebugEnabled()) {
		WsApplicationSimpleAdviceMessage _message = (WsApplicationSimpleAdviceMessage) message;
		_message.setTimemillis(System.currentTimeMillis() - _message.getTimemillis());
		WsLogger.debug(String.format("Response - %s", StringUitls.skipsString(_message.getOutput(), 5000)));
		WsLogger.debug(String.format("Call time - %s", _message.getTimemillis()));
	    }
	}
    }
}
