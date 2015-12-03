/**
 * 
 */
package net.easipay.dsfc;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class ApplicationException extends RuntimeException
{
    private static final long serialVersionUID = -3328317651624442801L;

    private String code = "";
    private String message = "";

    public ApplicationException(final String code, String message)
    {
	super();
	this.code = code;
	this.message = message;
    }
    
    public ApplicationException(final String code, String message,final Throwable throwable)
    {
	super(throwable);
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
}
