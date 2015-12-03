package net.easipay.dsfc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class ApplicationContextLogger
{
    private final static Log logger = LogFactory.getLog(ApplicationContextLogger.class);

    public static void info(Object message)
    {
	logger.info(message);
    }

    public static void info(Object message, Throwable t)
    {
	logger.info(message, t);
    }

    public static void error(Object message)
    {
	logger.info(message);
    }

    public static void error(Object message, Throwable t)
    {
	logger.info(message, t);
    }

    public static void debug(Object message)
    {
	logger.debug(message);
    }

    public static void debug(Object message, Throwable t)
    {
	logger.debug(message, t);
    }
    
    
    public static boolean isDebugEnabled()
    {
	return logger.isDebugEnabled();

    }
    
}
