/**
 * 
 */
package net.easipay.dsfc.ws;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class WsResult
{
    private String code;
    private String message;
    public String getCode()
    {
        return code;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public boolean isSuccess()
    {
	return ("000000".equals(this.code));
    }
    
    public void setSuccess(String code, String message)
    {
	setError(code, message);
    }

    public void setError(String code, String message)
    {
	this.code = code;
	this.message = message;
    }
    
    public abstract String toResult();
}
