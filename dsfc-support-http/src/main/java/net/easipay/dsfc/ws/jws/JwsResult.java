/**
 * 
 */
package net.easipay.dsfc.ws.jws;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import net.easipay.dsfc.ws.WsResult;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class JwsResult extends WsResult
{
    private JsonNode object;

    /**
     * 
     */
    public JwsResult()
    {
	object = JwsObjectMapper.instance.createObjectNode();
    }

    public void setObject(JsonNode object)
    {
	this.object = object;
    }

    public JsonNode getObject()
    {
	return object;
    }

    public <T> T toBean(Class<T> paramClass)
    {
	return JwsObjectMapper.instance.readValue(object, paramClass);
    }

    public <T> T getBean(String paramString, Class<T> paramClass)
    {
	return JwsObjectMapper.instance.readValue(this.object.get(paramString), paramClass);
    }

    public int getIntValue(String paramString)
    {
	return this.object.get(paramString).asInt();
    }

    public long getLongValue(String paramString)
    {
	return this.object.get(paramString).asLong();
    }

    public double getDoubleValue(String paramString)
    {
	return this.object.get(paramString).asDouble();
    }

    public String getStringValue(String paramString)
    {
	JsonNode jsonNode = this.object.get(paramString);
	return jsonNode == null ? null : jsonNode.asText();
    }

    public boolean hasKey(String paramString)
    {
	return this.object.has(paramString);
    }

    public <T> List<T> getList(Class<T> paramClass)
    {
	return JwsObjectMapper.instance.readValue(this.object, new TypeReference<List<T>>() {
	});
    }

    public <T> List<T> getList(String paramString, Class<T> paramClass)
    {
	return JwsObjectMapper.instance.readValues(this.object.get(paramString), paramClass);
    }

    public <T> T getListFirst(String paramString, Class<T> paramClass)
    {
	List<T> list = getList(paramString, paramClass);
	if (list == null || list.size() == 0) return null;
	return list.get(0);
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
		String content = JwsObjectMapper.instance.writeValueAsString(val);
		((ObjectNode) this.object).put(field.getName(), JwsObjectMapper.instance.readTree(content));
	    } catch ( Exception e ) {
		throw new JwsException("Fail to JwsHttpResponse.setAllValue", e);
	    }
	}
    }

    public void setValue(Map<String, Object> paramMap)
    {
	String content = JwsObjectMapper.instance.writeValueAsString(paramMap);
	this.object = JwsObjectMapper.instance.readTree(content);
    }

    public void setValue(String key, Object value)
    {
	this.object = JwsObjectMapper.instance.put(this.object, key, value);
    }

    @Override
    public String toResult()
    {
	ObjectNode resultNode = JwsObjectMapper.instance.createObjectNode();
	resultNode.put("Code", super.getCode());
	resultNode.put("Message", super.getMessage());
	resultNode.put("Response", this.object);
	return JwsObjectMapper.instance.writeValueAsString(resultNode);
    }
}
