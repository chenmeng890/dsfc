package net.easipay.dsfc.cache;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class CacheRegister
{
    public static void register(String type, long timeInterval, CacheHandler handle)
    {
	CacheLogger.info(String.format("Start to register cache service. type - %s , timeInterval - %s", type, timeInterval));
	CacheNode cacheNode = new CacheNode();
	cacheNode.setType(type);
	cacheNode.setTimeInterval(timeInterval);
	cacheNode.setTimeOut(System.currentTimeMillis() + cacheNode.getTimeInterval());
	cacheNode.setExpired(true);
	cacheNode.setHandle(handle);
	CacheManager.instance.putCacheNode(type, cacheNode);
    }
}
