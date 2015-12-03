/**
 * 
 */
package net.easipay.dsfc;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ApplicationContextInitializeException extends ApplicationException
{
    private static final long serialVersionUID = 808110904313739436L;

    public ApplicationContextInitializeException(String code, String message)
    {
	super(code, message);
    }
    
    public ApplicationContextInitializeException(final String code, String message,final Throwable throwable)
    {
	super(code, message, throwable);
    }
    
    public static ApplicationContextInitializeException wrap(String code, String message)
    {
	return new ApplicationContextInitializeException(code, message);
    }

    public static ApplicationContextInitializeException wrap(Throwable t)
    {
	return wrap("999998", t.getMessage());
    }
}
