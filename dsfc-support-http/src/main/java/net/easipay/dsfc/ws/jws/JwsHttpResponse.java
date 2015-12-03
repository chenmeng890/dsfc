/**
 * 
 */
package net.easipay.dsfc.ws.jws;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.easipay.dsfc.ws.wss.WssAbstractHttpResponse;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class JwsHttpResponse extends WssAbstractHttpResponse
{
    private Map<String, Object> value;

    public JwsHttpResponse(HttpServletResponse _response)
    {
	super(_response);

	this.value = new HashMap<String, Object>();
    }

    public void setAllValue(Object object)
    {
	if (object instanceof Map) {
	    throw new JwsException("Unsupported classType [java.util.Map]");
	}
	if (object instanceof List) {
	    throw new JwsException("Unsupported classType [java.util.List]");
	}
	Class<? extends Object> clazz = object.getClass();
	Field[] declaredFields = clazz.getDeclaredFields();
	for (Field field : declaredFields) {
	    try {
		Method method = clazz.getMethod("get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1));
		Object val = method.invoke(object);
		this.value.put(field.getName(), val);
	    } catch ( Exception e ) {
		throw new JwsException("Fail to JwsHttpResponse.setAllValue", e);
	    }
	}
    }

    public void setValue(Map<String, Object> paramMap)
    {
	this.value = paramMap;
    }

    public void setValue(String key, Object value)
    {
	this.value.put(key, value);
    }

    @Override
    public String getResponseContent() throws Exception
    {
	Map<String, Object> result = new HashMap<String, Object>();
	result.put("Code", super.getCode());
	result.put("Message", super.getMessage());
	result.put("Response", this.value);
	return JwsObjectMapper.instance.writeValueAsString(result);
    }

}
