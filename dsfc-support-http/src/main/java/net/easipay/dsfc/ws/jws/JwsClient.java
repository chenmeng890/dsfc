package net.easipay.dsfc.ws.jws;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.easipay.dsfc.ApplicationContext;
import net.easipay.dsfc.CommonUtils;
import net.easipay.dsfc.Service;
import net.easipay.dsfc.ws.WsAbstractDistributedHttpClient;
import net.easipay.dsfc.ws.WsByteInputStream;
import net.easipay.dsfc.ws.WsByteOutputStream;
import net.easipay.dsfc.ws.WsInputStream;
import net.easipay.dsfc.ws.WsOutputStream;
import net.easipay.dsfc.ws.WsResult;

import org.codehaus.jackson.JsonNode;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class JwsClient extends WsAbstractDistributedHttpClient
{
    private Map<String, Object> params;

    public JwsClient(String service)
    {
	super(service);
	this.params = new HashMap<String, Object>();
    }

    @Override
    public JwsResult call()
    {
	return (JwsResult) super.call();
    }

    @Override
    public JwsResult call(boolean paramBoolean)
    {
	return (JwsResult) super.call(paramBoolean);
    }

    @Override
    protected Map<String, String> getHeaders()
    {
	Map<String, String> header = new HashMap<String, String>();
	header.put("Content-Type", "text/plain;charset=UTF-8");
	return header;
    }

    @Override
    protected String toWsParamStr(Service service) throws Exception
    {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("PubInfo", getPubInfo(service));
	param.put("Request", this.params);
	param.put("Signature", getSignature());
	return JwsObjectMapper.instance.writeValueAsString(param);
    }

    @Override
    protected JwsResult toWsResult(String response) throws Exception
    {
	JwsResult jwsResult = new JwsResult();
	JsonNode readTree = JwsObjectMapper.instance.readTree(response);
	if (readTree != null) {
	    jwsResult.setCode(readTree.path("Code").getTextValue());
	    jwsResult.setMessage(readTree.path("Message").getTextValue());
	    jwsResult.setObject(readTree.path("Response"));
	}
	return jwsResult;
    }

    @Override
    protected WsResult getWsResult()
    {
	return new JwsResult();
    }

    @Override
    protected WsOutputStream getWsOutputStream(OutputStream out)
    {
	return new WsByteOutputStream(out);
    }

    @Override
    protected WsInputStream getWsInputStream(InputStream in)
    {
	return new WsByteInputStream(in);
    }

    public JwsClient putAllParam(Object object)
    {
	if (object instanceof Map) {
	    throw new RuntimeException("Unsupported classType [java.util.Map]");
	}
	if (object instanceof List) {
	    throw new RuntimeException("Unsupported classType [java.util.List]");
	}

	Class<? extends Object> clazz = object.getClass();
	Field[] declaredFields = clazz.getDeclaredFields();
	for (Field field : declaredFields) {
	    try {
		Method method = clazz.getMethod("get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1));
		Object val = method.invoke(object);
		this.params.put(field.getName(), val);
	    } catch ( Exception e ) {
	    }
	}
	return this;
    }

    public JwsClient putParam(Map<String, Object> params)
    {
	this.params.putAll(params);
	return this;
    }

    public JwsClient putParam(String key, Object value)
    {
	this.params.put(key, value);
	return this;
    }

    private Map<String, String> getPubInfo(Service service)
    {
	Map<String, String> pubInfo = new HashMap<String, String>();
	pubInfo.put("TransactionId", getTransactionId());
	pubInfo.put("TransactionTime", getTransactionTime());
	pubInfo.put("Channel", ApplicationContext.context.getApplication().getName());
	pubInfo.put("Origin", service.getServiceSource());
	pubInfo.put("ClientIP", CommonUtils.getClientIp());
	return pubInfo;
    }

    private Map<String, String> getSignature()
    {
	Map<String, String> signature = new HashMap<String, String>();
	signature.put("Sign", "");
	signature.put("data", "");
	return signature;
    }

    private String getTransactionId()
    {
	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String t = df.format(Calendar.getInstance().getTime());
	return ApplicationContext.context.getApplication().getName() + t;
    }

    private String getTransactionTime()
    {
	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	return df.format(Calendar.getInstance().getTime());
    }
}
