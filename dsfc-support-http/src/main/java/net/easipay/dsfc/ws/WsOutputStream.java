package net.easipay.dsfc.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public abstract class WsOutputStream
{
    private OutputStream out;

    public WsOutputStream(OutputStream out)
    {
	this.out = out;
    }

    public OutputStream getOutputStream()
    {
	return out;
    }

    public void write(int b) throws IOException
    {
	out.write(b);
    }

    public void write(byte[] b) throws IOException
    {
	out.write(b);
    }

    public abstract void write(String paramString, String charset) throws UnsupportedEncodingException, IOException;
}
