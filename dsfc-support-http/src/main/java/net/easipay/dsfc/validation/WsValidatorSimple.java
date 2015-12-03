/**
 * 
 */
package net.easipay.dsfc.validation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import net.easipay.dsfc.ws.WsException;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsValidatorSimple
{

    /**
     * Newly added JSR303 verification function, need to pass formbean
     * 
     * @param validateObject
     */
    public static void validate(Object validateObject, Class<?>... groups)
    {
	Validator validator = WsValidatorFactory.instance.getValidator();
	if (validateObject instanceof List || validateObject instanceof Map) {
	    throw new WsException("999997", "Not supported List and Map");
	}

	Set<ConstraintViolation<Object>> violations = validator.validate(validateObject, groups);
	if (violations.size() > 0) {
	    StringBuffer sb = new StringBuffer("Found field error [");
	    for (ConstraintViolation<Object> constraintViolation : violations) {
		if(constraintViolation.getPropertyPath() == null){
		    sb.append(constraintViolation.getPropertyPath()).append(":");
		}
		sb.append(constraintViolation.getMessage()).append(", ");
	    }
	    sb.delete(sb.lastIndexOf(","), sb.length() - 1);
	    sb.append("]");
	    throw new WsException("999999", sb.toString());
	}
    }

    /**
     * Collection check of object
     * 
     * @param validateObjects
     */
    public static void validateList(List<?> validateObjects, Class<?>... groups)
    {
	for (Object validateObject : validateObjects) {
	    validate(validateObject, groups);
	}
    }
}
