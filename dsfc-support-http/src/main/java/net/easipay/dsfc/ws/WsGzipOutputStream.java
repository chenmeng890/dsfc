package net.easipay.dsfc.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/**
 * 	
 * @author mchen
 * @date 2015-11-6
 */
public class WsGzipOutputStream extends WsOutputStream
{
    public WsGzipOutputStream(OutputStream out)
    {
	super(out);
    }

    @Override
    public void write(String paramString, String charset) throws IOException
    {
	GZIPOutputStream gzipOutputStream = new GZIPOutputStream(super.getOutputStream());
	gzipOutputStream.write(paramString.getBytes(Charset.forName(charset)));
	gzipOutputStream.finish();
    }
}
