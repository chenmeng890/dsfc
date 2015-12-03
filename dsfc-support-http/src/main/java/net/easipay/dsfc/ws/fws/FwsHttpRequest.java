/**
 * 
 */
package net.easipay.dsfc.ws.fws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.easipay.dsfc.validation.WsValidatorSimple;
import net.easipay.dsfc.ws.WsHttpUtils;
import net.easipay.dsfc.ws.WsLogger;
import net.easipay.dsfc.ws.wss.WssAbstractHttpRequest;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class FwsHttpRequest extends WssAbstractHttpRequest
{

    public FwsHttpRequest(HttpServletRequest request)
    {
	super(request);
    }

    public <T> List<T> getList(Class<T> clazz) throws FwsException
    {
	List<String> messages = WsHttpUtils.readGziplines(super.getHttpServletRequest());

	if (WsLogger.isDebugEnabled()) {
	    WsLogger.debug(String.format("Request - rows %s", messages.size()));
	}

	return FwsAnnoResolver.getList(messages, clazz);
    }

    public void validate(Object validateObject, Class<?>... groups)
    {
	WsValidatorSimple.validate(validateObject, groups);
    }

    public void validateList(List<?> validateObjects, Class<?>... groups)
    {
	WsValidatorSimple.validateList(validateObjects, groups);
    }
}
