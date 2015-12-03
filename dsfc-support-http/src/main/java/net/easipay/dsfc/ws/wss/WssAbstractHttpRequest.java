/**
 * 
 */
package net.easipay.dsfc.ws.wss;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.easipay.dsfc.validation.WsValidatorSimple;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class WssAbstractHttpRequest
{
    private HttpServletRequest request;

    public WssAbstractHttpRequest(HttpServletRequest request)
    {
	this.request = request;
    }

    public HttpServletRequest getHttpServletRequest()
    {
	return this.request;
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
