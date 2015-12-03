/**
 * 
 */
package net.easipay.dsfc.ws.wss;


/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WssHttpDispatherException extends RuntimeException
{
    private static final long serialVersionUID = 808110904313739436L;
    private String code = "";
    private String message = "";

   public WssHttpDispatherException(String code, String message)
    {
	this.code = code;
	this.message = message;
    }

    public String getLocalMessage()
    {
	return this.message;
    }

    public String getMessage()
    {
	return this.message + " [" + this.code + "]";
    }

    public String getCode()
    {
	return this.code;
    }

    public static WssHttpDispatherException wrap(String code, String message)
    {
	return new WssHttpDispatherException(code, message);
    }

    public static WssHttpDispatherException wrap(Throwable t)
    {
	return wrap("999998", t.getMessage());
    }
}
