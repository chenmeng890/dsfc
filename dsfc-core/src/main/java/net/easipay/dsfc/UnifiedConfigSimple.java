/**
 * 
 */
package net.easipay.dsfc;

import java.util.List;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class UnifiedConfigSimple
{

    public static UnifiedConfig getDicCodeConfig(String dicCode)
    {
	List<UnifiedConfig> unifiedConfig = UnifiedConfigCacheHandler.getUnifiedConfig(dicCode, "");

	if (unifiedConfig.size() == 0) return null;

	return unifiedConfig.get(0);
    }

    public static List<UnifiedConfig> getDicTypeConfig(String dicType)
    {
	return UnifiedConfigCacheHandler.getUnifiedConfig("", dicType);
    }

}
