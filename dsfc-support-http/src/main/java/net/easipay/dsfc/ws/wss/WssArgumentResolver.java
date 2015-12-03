package net.easipay.dsfc.ws.wss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.easipay.dsfc.ws.fws.FwsHttpRequest;
import net.easipay.dsfc.ws.fws.FwsHttpResponse;
import net.easipay.dsfc.ws.jws.JwsHttpRequest;
import net.easipay.dsfc.ws.jws.JwsHttpResponse;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WssArgumentResolver
{
    public boolean supportsParameter(Class<?> paramType)
    {
	return JwsHttpRequest.class.isAssignableFrom(paramType) || JwsHttpResponse.class.isAssignableFrom(paramType) || FwsHttpRequest.class.isAssignableFrom(paramType) || FwsHttpResponse.class.isAssignableFrom(paramType);
    }

    public Object resolveArgument(Class<?> paramType, HttpServletRequest nativeRequest, HttpServletResponse nativeResponse) throws Exception
    {
	if (JwsHttpRequest.class.isAssignableFrom(paramType)) {
	    return new JwsHttpRequest(nativeRequest);
	}
	else if (JwsHttpResponse.class.isAssignableFrom(paramType)) {
	    return new JwsHttpResponse(nativeResponse);
	}
	else if (FwsHttpRequest.class.isAssignableFrom(paramType)) {
	    return new FwsHttpRequest(nativeRequest);
	}
	else if (FwsHttpResponse.class.isAssignableFrom(paramType)) {
	    return new FwsHttpResponse(nativeResponse);
	}
	// should never happen..
	throw new UnsupportedOperationException("Unknown parameter type: " + paramType + " in method");
    }

}
