/**
 * 
 */
package net.easipay.dsfc.ws;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsClientCalledException extends WsException
{
    private static final long serialVersionUID = 808110904313739436L;

    public WsClientCalledException(final String code, final String message)
    {
	super(code, message);
    }

    public WsClientCalledException(final String code, final String message, final Throwable throwable)
    {
	super(code, message, throwable);
    }

    public static WsClientCalledException wrap(String code, String message)
    {
	return new WsClientCalledException(code, message);
    }

    public static WsClientCalledException wrap(Throwable t)
    {
	return wrap("999998", t.getMessage());
    }
}
