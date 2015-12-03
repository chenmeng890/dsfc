package net.easipay.dsfc.ws;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsTimeout
{
    private static int connectTimeout = 20000;
    private static int readTimeout = 20000;

    public static int getConnectTimeout()
    {
	return connectTimeout;
    }

    public static void setConnectTimeout(int connectTimeout)
    {
	WsTimeout.connectTimeout = connectTimeout;
    }

    public static int getReadTimeout()
    {
	return readTimeout;
    }

    public static void setReadTimeout(int readTimeout)
    {
	WsTimeout.readTimeout = readTimeout;
    }

}
