/**
 * 
 */
package net.easipay.dsfc.ws.fws;

import javax.servlet.http.HttpServletResponse;

import net.easipay.dsfc.ws.wss.WssAbstractHttpResponse;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class FwsHttpResponse extends WssAbstractHttpResponse
{

    public FwsHttpResponse(HttpServletResponse _response)
    {
	super(_response);
    }

    @Override
    public String getResponseContent() throws Exception
    {
	return super.getCode() + "#" + super.getMessage();
    }
}
