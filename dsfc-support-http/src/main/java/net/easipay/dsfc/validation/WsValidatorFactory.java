/**
 * 
 */
package net.easipay.dsfc.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsValidatorFactory
{
    public static final WsValidatorFactory instance = new WsValidatorFactory();
    
    private volatile ValidatorFactory delegate;

    private ValidatorFactory getDelegate()
    {
	ValidatorFactory result = delegate;
	if (result == null) {
	    synchronized (this) {
		result = delegate;
		if (result == null) {
		    delegate = result = initFactory();
		}
	    }
	}
	return result;
    }

    private ValidatorFactory initFactory()
    {
	return Validation.buildDefaultValidatorFactory();
    }

    public Validator getValidator()
    {
	return getDelegate().getValidator();
    }
    
    
    
}
