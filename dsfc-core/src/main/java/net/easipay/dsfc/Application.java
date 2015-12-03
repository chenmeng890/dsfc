/**
 * 
 */
package net.easipay.dsfc;


/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class Application
{
    private String name;
    
    private String localIp;
    
    private int localPort;
    
    private String contextPath;
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocalIp()
    {
        return localIp;
    }

    public void setLocalIp(String localIp)
    {
        this.localIp = localIp;
    }

    public int getLocalPort()
    {
        return localPort;
    }

    public void setLocalPort(int localPort)
    {
        this.localPort = localPort;
    }

    public String getContextPath()
    {
        return contextPath;
    }

    public void setContextPath(String contextPath)
    {
        this.contextPath = contextPath;
    }
}
