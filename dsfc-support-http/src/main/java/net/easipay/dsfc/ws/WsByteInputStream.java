package net.easipay.dsfc.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsByteInputStream extends WsInputStream
{
    public WsByteInputStream(InputStream in)
    {
	super(in);
    }

    public byte[] readBytes() throws IOException
    {
	InputStream in = getInputStream();
	int j = 0;
	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	while ((j = in.read()) >= 0)
	    byteArrayOutputStream.write(j);
	return byteArrayOutputStream.toByteArray();
    }

    @Override
    public int read() throws IOException
    {
	return read();
    }
}
