package net.easipay.dsfc.cache;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class CacheExecuter
{
    public static CacheNode execute(CacheNode cacheNode) throws Exception
    {
	CacheHandler handle = cacheNode.getHandle();
	if (handle == null) {
	    throw new CacheException("999999", String.format("Not found cacheNode [ type - %s ]", cacheNode.getType()));
	}

	CacheLogger.info(String.format("[ type - %s ] Start refresh data", cacheNode.getType()));

	Object object = handle.execute();
	if (object == null) {
	    throw new CacheException("999999", String.format("[ type - %s ] Returns the result to null", cacheNode.getType()));
	}
	cacheNode.setValue(object);
	cacheNode.setTimeOut(System.currentTimeMillis() + cacheNode.getTimeInterval());
	cacheNode.setExpired(false);
	CacheManager.instance.putCacheNode(cacheNode.getType(), cacheNode);

	return cacheNode;
    }

}
