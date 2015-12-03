/**
 * 
 */
package net.easipay.dsfc.ws.fws;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class FwsHttpServer
{
    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    private FwsHttpRequest fwsHttpRequest;

    private FwsHttpResponse fwsHttpResponse;

    public FwsHttpServer(HttpServletRequest request)
    {
	this(request, null);
    }

    public FwsHttpServer(HttpServletRequest request, HttpServletResponse response)
    {
	super();
	this.httpServletRequest = request;
	this.httpServletResponse = response;
    }

    public HttpServletRequest getHttpServletRequest()
    {
	return httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse()
    {
	return httpServletResponse;
    }

    public FwsHttpRequest getFwsHttpRequest()
    {
	if (this.fwsHttpRequest == null) {
	    this.fwsHttpRequest = new FwsHttpRequest(httpServletRequest);
	}
	return fwsHttpRequest;
    }

    public FwsHttpResponse getFwsHttpResponse()
    {
	if (this.fwsHttpResponse == null) {
	    this.fwsHttpResponse = new FwsHttpResponse(httpServletResponse);
	}
	return fwsHttpResponse;
    }

}
