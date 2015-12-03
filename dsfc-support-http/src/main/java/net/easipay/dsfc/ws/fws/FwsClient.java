/**
 * 
 */
package net.easipay.dsfc.ws.fws;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.easipay.dsfc.Service;
import net.easipay.dsfc.ws.WsAbstractDistributedHttpClient;
import net.easipay.dsfc.ws.WsByteInputStream;
import net.easipay.dsfc.ws.WsException;
import net.easipay.dsfc.ws.WsGzipOutputStream;
import net.easipay.dsfc.ws.WsInputStream;
import net.easipay.dsfc.ws.WsLogger;
import net.easipay.dsfc.ws.WsOutputStream;
import net.easipay.dsfc.ws.WsResult;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class FwsClient extends WsAbstractDistributedHttpClient
{
    private String paramString;

    public FwsClient(String service)
    {
	super(service);
    }

    @Override
    public FwsResult call()
    {
	return (FwsResult) super.call();
    }

    @Override
    public FwsResult call(boolean paramBoolean)
    {
	return (FwsResult) super.call(paramBoolean);
    }

    public <T> FwsClient putParam(List<T> objects, Class<T> clazz) throws FwsException
    {
	paramString = FwsAnnoResolver.serialize(objects, clazz);
	return this;
    }

    @Override
    protected Map<String, String> getHeaders()
    {
	Map<String, String> header = new HashMap<String, String>();
	header.put("Content-Type", "text/plain;charset=UTF-8");
	header.put("Content-Encoding", "gzip");
	return header;
    }

    @Override
    protected String toWsParamStr(Service service) throws Exception
    {
	return this.paramString;
    }

    @Override
    protected WsResult getWsResult()
    {
	return new FwsResult();
    }

    @Override
    protected WsResult toWsResult(String response) throws WsException
    {
	WsResult fwsResult = getWsResult();
	try {
	    String[] split = response.split("#");
	    fwsResult.setCode(split[0]);
	    fwsResult.setMessage(split[1]);
	} catch ( Exception e ) {
	    WsLogger.error("FwsClient.toWsResult throw exception", e);
	    throw FwsException.wrap(e);
	}
	return fwsResult;
    }

    @Override
    protected WsOutputStream getWsOutputStream(OutputStream out)
    {
	return new WsGzipOutputStream(out);
    }
    
    @Override
    protected WsInputStream getWsInputStream(InputStream in)
    {
	return new WsByteInputStream(in);
    }

}
