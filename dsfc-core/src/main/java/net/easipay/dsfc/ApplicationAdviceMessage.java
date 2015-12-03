/**
 * 
 */
package net.easipay.dsfc;

import java.io.Serializable;

import net.easipay.dsfc.ws.WsResult;


/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class ApplicationAdviceMessage implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -8795001304845293054L;
    private Service service;
    private WsResult wsResult;
    private String url;
    private long timemillis;
    public Service getService()
    {
        return service;
    }
    public void setService(Service service)
    {
        this.service = service;
    }
    public WsResult getWsResult()
    {
        return wsResult;
    }
    public void setWsResult(WsResult wsResult)
    {
        this.wsResult = wsResult;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public long getTimemillis()
    {
        return timemillis;
    }
    public void setTimemillis(long timemillis)
    {
        this.timemillis = timemillis;
    }
}
