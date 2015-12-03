/**
 * 
 */
package net.easipay.dsfc;


/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ServiceNoAvailableException extends ApplicationException
{
    private static final long serialVersionUID = 808110904313739436L;

    public ServiceNoAvailableException(String code, String message)
    {
	super(code, message);
    }

    public ServiceNoAvailableException(final String code, String message, final Throwable throwable)
    {
	super(code, message, throwable);
    }

    public static ServiceNoAvailableException wrap(String code, String message)
    {
	return new ServiceNoAvailableException(code, message);
    }

    public static ServiceNoAvailableException wrap(Throwable t)
    {
	return wrap("999998", t.getMessage());
    }

}
