/**
 * 
 */
package net.easipay.dsfc.ws.jws;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.easipay.dsfc.ws.jws.JwsHttpRequest;
import net.easipay.dsfc.ws.jws.JwsHttpResponse;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class JwsHttpServer
{
    private HttpServletRequest httpServletRequest;
    
    private HttpServletResponse httpServletResponse;

    private JwsHttpRequest jwsHttpRequest;

    private JwsHttpResponse jwsHttpResponse;
    
    public JwsHttpServer(HttpServletRequest request)
    {
	this(request, null);
    }

    public JwsHttpServer(HttpServletRequest request, HttpServletResponse response)
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
    
    
    public JwsHttpRequest getJwsHttpRequest()
    {
	if(this.jwsHttpRequest == null){
	    this.jwsHttpRequest = new JwsHttpRequest(httpServletRequest); 
	}
	return jwsHttpRequest;
    }
    
    public JwsHttpResponse getJwsHttpResponse()
    {
	if(this.jwsHttpResponse == null){
	    this.jwsHttpResponse = new JwsHttpResponse(httpServletResponse); 
	}
	return jwsHttpResponse;
    }

}
