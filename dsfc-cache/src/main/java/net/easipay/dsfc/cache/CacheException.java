/**
 * 
 */
package net.easipay.dsfc.cache;


/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class CacheException extends RuntimeException
{
    private static final long serialVersionUID = 808110904313739436L;
    private String code = "";
    private String message = "";

    public CacheException(String code, String message)
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

    public static CacheException wrap(String code, String message)
    {
	return new CacheException(code, message);
    }

    public static CacheException wrap(Throwable t)
    {
	return wrap("999998", t.getMessage());
    }
}
