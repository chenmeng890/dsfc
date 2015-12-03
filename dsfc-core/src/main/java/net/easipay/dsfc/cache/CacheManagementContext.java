/**
 * 
 */
package net.easipay.dsfc.cache;

import net.easipay.dsfc.ServiceCacheHandler;
import net.easipay.dsfc.UnifiedConfigCacheHandler;


/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class CacheManagementContext
{
    
    public static void initCacheContext(){
	CacheRegister.register(ServiceCacheHandler.NET_EASIPAY_DSFC_SERVICE, CacheConfigurator.THREE_MINUTES_REFRESH_TIME, new ServiceCacheHandler());
	
	CacheRegister.register(UnifiedConfigCacheHandler.NET_EASIPAY_DSFC_UNIFIEDCONFIG, CacheConfigurator.THREE_MINUTES_REFRESH_TIME, new UnifiedConfigCacheHandler());
    }
}
