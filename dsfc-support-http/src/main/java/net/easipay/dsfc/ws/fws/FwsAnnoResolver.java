/**
 * 
 */
package net.easipay.dsfc.ws.fws;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.easipay.dsfc.ws.WsLogger;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class FwsAnnoResolver
{
    private static Map<String, Field[]> staticField = new HashMap<String, Field[]>();

    public static <T> List<T> getList(List<String> messages, Class<T> clazz) throws FwsException
    {
	if(messages == null || messages.size() == 0){
	    throw new FwsException("Data can not be empty");
	}
	
	List<T> list = new ArrayList<T>();
	for (String message : messages) {
	    list.add(getObject(message, clazz));
	}
	return list;
    }

    public static <T> T getObject(String message, Class<T> clazz) throws FwsException
    {
	try {
	    String[] split = message.split("\\" + FwsConfig.APART_MARK);

	    Field[] fields = null;

	    if (!staticField.containsKey(clazz.getName())) {
		fields = clazz.getDeclaredFields();
		staticField.put(clazz.getName(), fields);
	    }
	    else {
		fields = staticField.get(clazz.getName());
	    }

	    T object = clazz.newInstance();

	    for (int i = 0; i < fields.length; i++) {
		FwsAnno annotation = fields[i].getAnnotation(FwsAnno.class);
		if (annotation == null) {
		    continue;
		}
		Method method = clazz.getMethod("set" + Character.toUpperCase(fields[i].getName().charAt(0)) + fields[i].getName().substring(1), fields[i].getType());

		Object param = cast(fields[i].getType(), split[annotation.value()]);

		method.invoke(object, param);
	    }

	    return object;
	} catch ( Exception e ) {
	    WsLogger.error(String.format("class[ %s ] conversion failed", clazz.getName()), e);
	    throw FwsException.wrap(e);
	}
    }

    public static <T> String serialize(List<T> objects, Class<T> clazz) throws FwsException
    {
	try {
	    Field[] fields = null;

	    if (!staticField.containsKey(clazz.getName())) {
		fields = clazz.getDeclaredFields();
		staticField.put(clazz.getName(), fields);
	    }
	    else {
		fields = staticField.get(clazz.getName());
	    }

	    StringBuffer sb = new StringBuffer();

	    Map<Integer, Object> map = new HashMap<Integer, Object>();
	    for (T object : objects) {
		for (int i = 0; i < fields.length; i++) {
		    FwsAnno annotation = fields[i].getAnnotation(FwsAnno.class);
		    if (annotation == null) {
			continue;
		    }
		    Method method = clazz.getMethod("get" + Character.toUpperCase(fields[i].getName().charAt(0)) + fields[i].getName().substring(1));

		    Object val = method.invoke(object);

		    map.put(annotation.value(), val == null ? "" : val);
		}

		for (int i = 0; i < map.size(); i++) {
		    sb.append(map.get(i)).append(FwsConfig.APART_MARK);
		}
		if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
		sb.append("\n");
	    }
	    
	    return sb.toString();
	} catch ( Exception e ) {
	    WsLogger.error(String.format("class[ %s ] serialize failed", clazz.getName()), e);
	    throw FwsException.wrap(e);
	}
    }

    private static Object cast(Class<?> _clazz, String value)
    {
	String type = _clazz.getName();
	if (type.equals("java.lang.String")) {
	    return value;
	}
	else if (type.equals("java.lang.Short") || type.equals("short")) {
	    return Short.valueOf(value);
	}
	else if (type.equals("java.lang.Integer") || type.equals("int")) {
	    return Integer.valueOf(value);
	}
	else if (type.equals("java.lang.Long") || type.equals("long")) {
	    return Long.valueOf(value);
	}
	else if (type.equals("java.lang.Float") || type.equals("float")) {
	    return Float.valueOf(value);
	}
	else if (type.equals("java.lang.Double") || type.equals("double")) {
	    return Double.valueOf(value);
	}
	else if (type.equals("java.lang.Boolean") || type.equals("boolean")) {
	    return Boolean.valueOf(value);
	}
	else {
	    return null;
	}
    }

}
