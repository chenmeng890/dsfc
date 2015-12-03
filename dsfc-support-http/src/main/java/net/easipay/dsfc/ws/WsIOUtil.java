package net.easipay.dsfc.ws;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsIOUtil
{
    public static void closeQuietly(Closeable closeable)
    {
	try {
	    if (closeable != null) {
		closeable.close();
		closeable = null;
	    }
	} catch ( IOException e ) {
	    WsLogger.error(e);
	}
    }

    public static void flushQuietly(Flushable flushable)
    {
	try {
	    if (flushable != null) {
		flushable.flush();
	    }
	} catch ( IOException e ) {
	    WsLogger.error(e);
	}
    }
}
