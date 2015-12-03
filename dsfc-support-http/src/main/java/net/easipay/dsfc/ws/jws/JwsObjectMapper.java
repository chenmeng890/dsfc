package net.easipay.dsfc.ws.jws;

import java.text.SimpleDateFormat;
import java.util.List;

import net.easipay.dsfc.ws.WsLogger;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class JwsObjectMapper extends ObjectMapper
{
    public static final JwsObjectMapper instance = new JwsObjectMapper();

    public JwsObjectMapper()
    {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	this.setSerializationConfig(getSerializationConfig().withDateFormat(simpleDateFormat));
	this.setDeserializationConfig(getDeserializationConfig().withDateFormat(simpleDateFormat));
    }

    @Override
    public <T> T readValue(JsonNode root, Class<T> valueType)
    {
	try {
	    return super.readValue(root, valueType);
	} catch ( Exception e ) {
	    WsLogger.error(e);
	    JwsException.wrap(e);
	}
	return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public <T> T readValue(JsonNode root, TypeReference valueTypeRef)
    {
	try {
	    return (T) super.readValue(root, valueTypeRef);
	} catch ( Exception e ) {
	    WsLogger.error(e);
	    JwsException.wrap(e);
	}
	return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> readValues(JsonNode root, Class<T> valueType)
    {
	try {
	    JavaType constructParametricType = getTypeFactory().constructParametricType(List.class, valueType);
	    return (List<T>) readValue(root, constructParametricType);
	} catch ( Exception e ) {
	    WsLogger.error(e);
	    JwsException.wrap(e);
	}
	return null;
    }

    public String writeValueAsString(Object value)
    {
	try {
	    return super.writeValueAsString(value);
	} catch ( Exception e ) {
	    WsLogger.error(e);
	    JwsException.wrap(e);
	}
	return null;
    }

    public JsonNode readTree(String content)
    {
	try {
	    return super.readTree(content);
	} catch ( Exception e ) {
	    WsLogger.error(e);
	    JwsException.wrap(e);
	}
	return null;
    }

    public JsonNode put(JsonNode jsonNode, String fieldName, Object value)
    {
	ObjectNode resultNode = JwsObjectMapper.instance.createObjectNode();
	resultNode.putPOJO(fieldName, value);
	if (jsonNode != null) resultNode.putAll((ObjectNode) jsonNode);
	return resultNode;
    }
}
