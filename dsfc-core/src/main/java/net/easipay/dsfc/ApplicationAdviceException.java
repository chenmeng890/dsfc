/**
 * 
 */
package net.easipay.dsfc;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ApplicationAdviceException extends ApplicationException
{
    private static final long serialVersionUID = 808110904313739436L;
    public ApplicationAdviceException(String code, String message)
    {
	super(code, message);
    }

    public ApplicationAdviceException(final String code, String message, final Throwable throwable)
    {
	super(code, message, throwable);
    }

    public static ApplicationAdviceException wrap(String code, String message)
    {
	return new ApplicationAdviceException(code, message);
    }

    public static ApplicationAdviceException wrap(Throwable t)
    {
	return wrap("999998", t.getMessage());
    }
}
