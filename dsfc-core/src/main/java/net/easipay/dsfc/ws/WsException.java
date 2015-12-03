/**
 * 
 */
package net.easipay.dsfc.ws;

import net.easipay.dsfc.ApplicationException;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsException extends ApplicationException
{
    private static final long serialVersionUID = 808110904313739436L;

    public WsException(final String code, final String message)
    {
	super(code, message);
    }

    public WsException(final String code, final String message, final Throwable throwable)
    {
	super(code, message, throwable);
    }
}
