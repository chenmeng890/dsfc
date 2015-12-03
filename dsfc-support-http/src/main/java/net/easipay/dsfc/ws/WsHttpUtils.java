/**
 * 
 */
package net.easipay.dsfc.ws;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsHttpUtils
{
    public static String parsePostData(HttpServletRequest request)
    {
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	ServletInputStream inputStream = null;
	try {
	    int c = 0;
	    inputStream = request.getInputStream();
	    while ((c = inputStream.read()) != -1) {
		bos.write(c);
	    }
	    return new String(bos.toByteArray(), WsCharset.CHARST_UTF8);
	} catch ( IOException e ) {
	    WsLogger.error("JwsHttpUtils.parsePostData throw exception", e);
	} finally {
	    WsIOUtil.closeQuietly(bos);
	    WsIOUtil.closeQuietly(inputStream);
	}
	return "";
    }

    public static List<String> readGziplines(HttpServletRequest request)
    {
	BufferedReader reader = null;
	List<String> list = new ArrayList<String>();

	InputStream inputStream = null;
	try {
	    inputStream = request.getInputStream();
	    reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream)));
	    String tempString = null;
	    while ((tempString = reader.readLine()) != null) {
		list.add(tempString);
	    }
	} catch ( IOException e ) {
	    WsLogger.error("WsHttpUtils.readGziplines throw exception", e);
	    throw new WsException("999982", e.getMessage());
	} finally {
	    WsIOUtil.closeQuietly(reader);
	    WsIOUtil.closeQuietly(inputStream);
	}
	return list;
    }
}
