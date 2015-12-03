/**
 * 
 */
package net.easipay.dsfc.ws.wss;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.easipay.dsfc.StringUitls;
import net.easipay.dsfc.ws.WsLogger;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class WssAbstractHttpResponse
{
    private HttpServletResponse _response;

    private String code;

    private String message;

    public WssAbstractHttpResponse(HttpServletResponse _response)
    {
	this._response = _response;

	this.code = "000000";

	this.message = "成功";
    }
    
    protected String getCode()
    {
	return code;
    }
    
    protected String getMessage()
    {
	return message;
    }

    public void setSuccess(String code, String message)
    {
	setError(code, message);
    }

    public void setError(String code, String message)
    {
	this.code = code;
	this.message = message;
    }

    public void writeContent() throws Exception
    {
	if (null == this.code || "".equals(this.code)) {
	    throw new RuntimeException("Illegal code");
	}

	String responseContent = getResponseContent();
	if (WsLogger.isDebugEnabled()) {
	    WsLogger.debug(String.format("Response - %s", StringUitls.skipsString(responseContent, 5000)));
	}
	write(responseContent);
    }

    public abstract String getResponseContent() throws Exception;

    private void write(String content) throws IOException
    {
	_response.setContentType("text/plain;charset=utf-8");
	_response.setCharacterEncoding("utf-8");
	_response.setHeader("Pragma", "no-cache");
	_response.setHeader("Cache-Control", "no-cache, must-revalidate");
	getHttpPrintWriter().write(content);
	getHttpPrintWriter().flush();
	getHttpPrintWriter().close();
    }

    public PrintWriter getHttpPrintWriter() throws IOException
    {
	return _response.getWriter();
    }

    public ServletOutputStream getHttpOutputStream() throws IOException
    {
	return _response.getOutputStream();
    }
}
