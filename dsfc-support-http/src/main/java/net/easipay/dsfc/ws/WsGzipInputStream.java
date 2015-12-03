package net.easipay.dsfc.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsGzipInputStream extends WsInputStream
{

    public WsGzipInputStream(InputStream in)
    {
	super(in);
    }

    public byte[] readBytes() throws IOException
    {
	GZIPInputStream gzipInputStream = new GZIPInputStream(super.getInputStream());
	int j = 0;
	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	while ((j = gzipInputStream.read()) >= 0)
	    byteArrayOutputStream.write(j);
	return byteArrayOutputStream.toByteArray();
    }
}
