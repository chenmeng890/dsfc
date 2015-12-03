/**
 * 
 */
package net.easipay.dsfc.ws.jws;

import net.easipay.dsfc.ws.WsException;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class JwsException extends WsException
{
    private static final long serialVersionUID = 808110904313739436L;

    public JwsException(String message)
    {
	super("999990", message);
    }

    public JwsException(String message, final Throwable throwable)
    {
	super("999990", message, throwable);
    }

    public JwsException(String code, String message)
    {
	super(code, message);
    }

    public JwsException(final String code, final String message, final Throwable throwable)
    {
	super(code, message, throwable);
    }

    public static JwsException wrap(String code, String message)
    {
	return new JwsException(code, message);
    }

    public static JwsException wrap(Throwable t)
    {
	return wrap("999990", t.getMessage());
    }
}
