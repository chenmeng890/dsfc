/**
 * 
 */
package net.easipay.dsfc.ws;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import net.easipay.dsfc.ApplicationAdvice;
import net.easipay.dsfc.ApplicationAdviceMessage;
import net.easipay.dsfc.Service;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class WsAbstractDistributedHttpClient extends WsAbstractDistributedClient
{
    private WsApplicationSimpleAdviceMessage message;

    public WsAbstractDistributedHttpClient(String serviceId)
    {
	super(serviceId);

	this.message = new WsApplicationSimpleAdviceMessage();
    }

    @Override
    protected Object preprocess(Service service) throws Exception
    {
	String wsParamStr = toWsParamStr(service);

	message.setInput(wsParamStr);

	return wsParamStr;
    }

    @Override
    protected WsResult execute(String url, Object preObject, Service service) throws Exception
    {
	Map<String, String> headers = getHeaders();

	URL _url = new URL(url);
	
	String response = httpExecute(_url, headers, (String) preObject);

	message.setOutput(response);

	WsResult wsResult = toWsResult(response);
	return wsResult;
    }

    @Override
    protected ApplicationAdvice getApplicationAdvice()
    {
	return WsApplicationSimpleAdvice.simple;
    }

    @Override
    protected ApplicationAdviceMessage getApplicationAdviceMessage()
    {
	return this.message;
    }

    protected abstract Map<String, String> getHeaders();

    protected abstract String toWsParamStr(Service service) throws Exception;

    protected abstract WsOutputStream getWsOutputStream(OutputStream out);

    protected abstract WsInputStream getWsInputStream(InputStream in);

    protected abstract WsResult toWsResult(String response) throws Exception;

    private String httpExecute(URL paramURL, Map<String, String> headers, String paramString) throws WsClientCalledException
    {
	HttpURLConnection httpURLConnection = null;
	InputStream inputStream = null;
	ByteArrayOutputStream byteArrayOutputStream = null;
	try {
	    httpURLConnection = (HttpURLConnection) paramURL.openConnection();
	    httpURLConnection.setConnectTimeout(WsTimeout.getConnectTimeout());
	    httpURLConnection.setReadTimeout(WsTimeout.getReadTimeout());
	    if ((headers != null) && (headers.size() > 0)) {
		Iterator<String> iterators = headers.keySet().iterator();
		while (iterators.hasNext()) {
		    String str = iterators.next();
		    httpURLConnection.setRequestProperty(str, headers.get(str));
		}
	    }
	    httpURLConnection.setRequestMethod("POST");
	    httpURLConnection.setDoOutput(true);
	    OutputStream out = httpURLConnection.getOutputStream();
	    getWsOutputStream(out).write(paramString, WsCharset.CHARST_UTF8);

	    int i = httpURLConnection.getResponseCode();
	    if (i != 200) throw new Exception("Http Error [" + i + "]");

	    byte[] readBytes = getWsInputStream(httpURLConnection.getInputStream()).readBytes();
	    return new String(readBytes, WsCharset.CHARST_UTF8);
	} catch ( Exception e ) {
	    throw WsClientCalledException.wrap("999993", e.getMessage());
	} finally {
	    try {
		if (byteArrayOutputStream != null) {
		    byteArrayOutputStream.close();
		    byteArrayOutputStream = null;
		}
	    } catch ( Exception e ) {
	    }
	    try {
		if (inputStream != null) {
		    inputStream.close();
		    inputStream = null;
		}
	    } catch ( Exception e ) {
	    }
	    try {
		if (httpURLConnection != null) {
		    httpURLConnection.disconnect();
		    httpURLConnection = null;
		}
	    } catch ( Exception e ) {
	    }
	}
    }
}
