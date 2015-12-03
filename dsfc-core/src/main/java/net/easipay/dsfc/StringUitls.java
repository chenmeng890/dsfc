/**
 * 
 */
package net.easipay.dsfc;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class StringUitls
{

    public static boolean isNotBlank(String str)
    {
	return !isBlank(str);
    }

    public static boolean isBlank(String str)
    {
	int strLen;
	if (str == null || (strLen = str.length()) == 0) {
	    return true;
	}
	for (int i = 0; i < strLen; i++) {
	    if ((Character.isWhitespace(str.charAt(i)) == false)) {
		return false;
	    }
	}
	return true;
    }

    public static String splicingLink(String... paths)
    {
	StringBuffer sb = new StringBuffer("/");
	for (int i = 0; i < paths.length; i++) {
	    if (isBlank(paths[i])) continue;

	    sb.append(paths[i]);

	    if (i != paths.length - 1) {
		sb.append("/");
	    }
	}
	return sb.toString().replaceAll("[/]+", "/");
    }

    public static String skipsString(String input, int size)
    {
	if (input == null) return "";
	return input.length() < size ? input : (input.substring(0, size - 1) + "...skips...");
    }

    public static String deleteLastString(String str, String last)
    {
	if (str == null) return str;
	int lastIndexOf = str.lastIndexOf(last);
	return lastIndexOf != -1 ? str.substring(0, lastIndexOf) + str.substring(lastIndexOf + 1, str.length()) : str;
    }

}
