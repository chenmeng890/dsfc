/**
 * 
 */
package net.easipay.dsfc.ws.jws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.easipay.dsfc.StringUitls;
import net.easipay.dsfc.ws.WsHttpUtils;
import net.easipay.dsfc.ws.WsLogger;
import net.easipay.dsfc.ws.wss.WssAbstractHttpRequest;

import org.codehaus.jackson.JsonNode;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
@SuppressWarnings("unused")
public class JwsHttpRequest extends WssAbstractHttpRequest
{
    private JsonNode pubInfo;

    private JsonNode _request;

    private JsonNode signature;

    public JwsHttpRequest(HttpServletRequest request)
    {
	super(request);
	initJwsBody(request);
    }

    public void initJwsBody(HttpServletRequest request)
    {
	try {
	    String parsePostData = WsHttpUtils.parsePostData(request);
	    if (WsLogger.isDebugEnabled()) {
		WsLogger.debug(String.format("Request - %s", StringUitls.skipsString(parsePostData, 5000)));
	    }

	    JsonNode readTree = JwsObjectMapper.instance.readTree(parsePostData);
	    pubInfo = readTree.get("PubInfo");
	    _request = readTree.get("Request");
	    signature = readTree.get("Signature");
	} catch ( Exception e ) {
	    throw new JwsException("Fail to JwsHttpRequest.initJwsBody", e);
	}
    }

    public <T> T toBean(Class<T> paramClass)
    {
	return JwsObjectMapper.instance.readValue(this._request, paramClass);
    }

    public <T> T getBean(String paramString, Class<T> paramClass)
    {
	return JwsObjectMapper.instance.readValue(this._request.get(paramString), paramClass);
    }

    public int getIntValue(String paramString)
    {
	return this._request.get(paramString).asInt();
    }

    public long getLongValue(String paramString)
    {
	return this._request.get(paramString).asLong();
    }

    public double getDoubleValue(String paramString)
    {
	return this._request.get(paramString).asDouble();
    }

    public String getStringValue(String paramString)
    {
	JsonNode jsonNode = this._request.get(paramString);
	return jsonNode == null ? null : jsonNode.asText();
    }

    public boolean hasKey(String paramString)
    {
	return this._request.has(paramString);
    }

    public <T> List<T> getList(String paramString, Class<T> paramClass)
    {
	return JwsObjectMapper.instance.readValues(this._request.get(paramString), paramClass);
    }

    public <T> T getListFirst(String paramString, Class<T> paramClass)
    {
	List<T> list = getList(paramString, paramClass);
	if (list == null || list.size() == 0) return null;
	return list.get(0);
    }

}
