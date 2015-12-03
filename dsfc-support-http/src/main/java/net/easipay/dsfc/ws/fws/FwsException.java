/**
 * 
 */
package net.easipay.dsfc.ws.fws;

import net.easipay.dsfc.ws.WsException;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class FwsException extends WsException
{
    private static final long serialVersionUID = -6739078995113922267L;
    
    public FwsException(String message)
    {
	super("999990", message);
    }

    public FwsException(String code, String message)
    {
	super(code, message);
    }
    
    public FwsException(final String code, final String message, final Throwable throwable)
    {
	super(code, message, throwable);
    }
    
    public static FwsException wrap(String code, String message)
    {
	return new FwsException(code, message);
    }

    public static FwsException wrap(Throwable t)
    {
	return wrap("999990", t.getMessage());
    }
}
