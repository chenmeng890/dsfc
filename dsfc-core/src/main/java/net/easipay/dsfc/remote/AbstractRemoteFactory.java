/**
 * 
 */
package net.easipay.dsfc.remote;

import net.easipay.dsfc.ApplicationContextLogger;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class AbstractRemoteFactory
{
    private volatile AbstractRemoteService delegate;

    public AbstractRemoteService getDelegate()
    {
	AbstractRemoteService result = delegate;
	if (result == null) {
	    synchronized (this) {
		result = delegate;
		if (result == null) {
		    delegate = result = initFactory();
		}
	    }
	}
	return result;
    }

    private AbstractRemoteService initFactory()
    {
	return buildDefaultRemoteFactory();
    }

    private AbstractRemoteService buildDefaultRemoteFactory()
    {
	try {
	    Class<? extends AbstractRemoteService> loadClass = AbstractRemoteService.loadClass(AbstractRemoteService.DEFAULT_REMOTE_CALL);
	    return loadClass.newInstance();
	} catch ( Exception e ) {
	    ApplicationContextLogger.error("AbstractRemoteFactory.buildDefaultRemoteFactory throw exception", e);
	}
	return null;
    }
}
