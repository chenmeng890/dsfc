/**
 * 
 */
package net.easipay.dsfc;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class CommonUtils
{

    public static String getClientIp()
    {
	try {
	    for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
		NetworkInterface item = e.nextElement();

		for (InterfaceAddress address : item.getInterfaceAddresses()) {
		    if (address.getAddress() instanceof Inet4Address) {
			Inet4Address inet4Address = (Inet4Address) address.getAddress();
			if (!inet4Address.isLinkLocalAddress() && !inet4Address.isLoopbackAddress() && !inet4Address.isMCGlobal() && !inet4Address.isMulticastAddress()) {
			    return inet4Address.getHostAddress();
			}
		    }
		}
	    }

	} catch ( IOException ex ) {
	    ex.printStackTrace();
	}
	return "";
    }
}
