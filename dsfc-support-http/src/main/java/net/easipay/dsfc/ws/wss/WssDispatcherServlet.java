/**
 * 
 */
package net.easipay.dsfc.ws.wss;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.easipay.dsfc.StringUitls;
import net.easipay.dsfc.ws.WsIOUtil;
import net.easipay.dsfc.ws.WsLogger;
import net.easipay.dsfc.ws.WsResult;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WssDispatcherServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private WssArgumentResolver wssArgumentResolver = new WssArgumentResolver();

    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init();
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	doDispatch(request, response);
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response)
    {
	try {
	    String requestMapping = request.getPathInfo();
	    WssHandlerExecutionChain wssHandlerExecutionChain = WssConfig.ins.getWssHandlerExecutionChain(requestMapping);

	    if (wssHandlerExecutionChain == null) {
		throw new WssHttpDispatherException("999970", String.format("Invalid requestMapping %s.", requestMapping));
	    }

	    Object[] args = getMethodArgumentValues(wssHandlerExecutionChain, request, response);

	    WsResult result = (WsResult) wssHandlerExecutionChain.getMethod().invoke(wssHandlerExecutionChain.getHandler(), args);

	    writeContent(response, result.toResult());
	} catch ( Exception e ) {
	    WsLogger.error("WssDispatcherServlet.doDispatch throw exception", e);
	    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
    }

    private Object[] getMethodArgumentValues(WssHandlerExecutionChain wssHandlerExecutionChain, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	Class<?>[] parameterTypes = wssHandlerExecutionChain.getMethod().getParameterTypes();
	Object[] args = new Object[parameterTypes.length];
	for (int i = 0; i < parameterTypes.length; i++) {
	    if (wssArgumentResolver.supportsParameter(parameterTypes[i])) {
		try {
		    args[i] = wssArgumentResolver.resolveArgument(parameterTypes[i], request, response);
		} catch ( Exception e ) {
		    WsLogger.error("Error resolving argument", e);
		    throw e;
		}
	    }

	    if (args[i] == null) {
		throw new IllegalStateException("No suitable resolver for argument");
	    }
	}
	return args;
    }

    private void writeContent(HttpServletResponse response, String content)
    {
	if (WsLogger.isDebugEnabled()) {
	    WsLogger.debug(String.format("Response - %s", StringUitls.skipsString(content, 5000)));
	}
	response.setContentType("text/plain;charset=utf-8");
	response.setCharacterEncoding("utf-8");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache, must-revalidate");
	PrintWriter writer = null;
	try {
	    writer = response.getWriter();
	    writer.write(content);
	} catch ( IOException e ) {
	    WsLogger.error(e);
	} finally {
	    WsIOUtil.flushQuietly(writer);
	    WsIOUtil.closeQuietly(writer);
	}
    }


}
