package net.easipay.dsfc.ws.wss;

import net.easipay.dsfc.ws.WsException;

/**
 * 
 * @author mchen
 * @date 2015-11-25
 */
public class WssException extends WsException
{
    private static final long serialVersionUID = 1L;

    public WssException(final String code, final String message)
    {
	super(code, message);
    }

    public WssException(final String code, final String message, final Throwable throwable)
    {
	super(code, message, throwable);
    }

    public static WssException wrap(String code, String message)
    {
	return new WssException(code, message);
    }

    public static WssException wrap(Throwable t)
    {
	return wrap("999998", t.getMessage());
    }
}
