package net.easipay.dsfc.ws;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class WsInputStream
{
    private InputStream in;

    public WsInputStream(InputStream in)
    {
	this.in = in;
    }

    public InputStream getInputStream()
    {
	return in;
    }

    public int read() throws IOException
    {
	return in.read();
    }

    public abstract byte[] readBytes() throws IOException;

}
