package net.easipay.dsfc.cache;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class CacheNode
{
    private String type;
    private Object value;
    private long timeInterval;
    private long timeOut;
    private boolean expired;
    private CacheHandler handle;
    
    public String getType()
    {
	return type;
    }

    public void setType(String type)
    {
	this.type = type;
    }

    public Object getValue()
    {
	return value;
    }

    public void setValue(Object value)
    {
	this.value = value;
    }

    public long getTimeOut()
    {
	return timeOut;
    }

    public void setTimeOut(long timeOut)
    {
	this.timeOut = timeOut;
    }

    public boolean isExpired()
    {
	return expired;
    }

    public void setExpired(boolean expired)
    {
	this.expired = expired;
    }

    public CacheHandler getHandle()
    {
        return handle;
    }

    public void setHandle(CacheHandler handle)
    {
        this.handle = handle;
    }

    public long getTimeInterval()
    {
        return timeInterval;
    }

    public void setTimeInterval(long timeInterval)
    {
        this.timeInterval = timeInterval;
    }

}
