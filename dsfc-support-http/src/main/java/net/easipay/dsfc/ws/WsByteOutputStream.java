package net.easipay.dsfc.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsByteOutputStream extends WsOutputStream
{
    public WsByteOutputStream(OutputStream out)
    {
	super(out);
    }

    @Override
    public void write(String paramString, String charset) throws UnsupportedEncodingException, IOException
    {
	super.write(paramString.getBytes(charset));
    }

}
