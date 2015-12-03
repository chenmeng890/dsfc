/**
 * 
 */
package net.easipay.dsfc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.easipay.dsfc.cache.CacheHandler;
import net.easipay.dsfc.cache.CacheManager;
import net.easipay.dsfc.remote.AbstractRemoteService;
import net.easipay.dsfc.remote.SearchParameterQueryForm;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class UnifiedConfigCacheHandler implements CacheHandler
{
    /**
     * The keys of all the config.
     */
    public static final String NET_EASIPAY_DSFC_UNIFIEDCONFIG = "net.easipay.dsfc.UnifiedConfig";

    /**
     * Collection of all the SearchParameterQueryForm.
     */
    private static Map<String, SearchParameterQueryForm> formMap = new ConcurrentHashMap<String, SearchParameterQueryForm>();

    @Override
    public Object execute() throws Exception
    {
	AbstractRemoteService delegate = ApplicationContext.context.getRemoteFactory().getDelegate();

	ArrayList<SearchParameterQueryForm> list = new ArrayList<SearchParameterQueryForm>();
	list.addAll(formMap.values());
	List<UnifiedConfig> unifiedConfigs = delegate.searchParameter(list);
	return unifiedConfigs;
    }

    @SuppressWarnings("unchecked")
    public static List<UnifiedConfig> getUnifiedConfig(String dicCode, String dicType)
    {
	String key = dicCode + "#" + dicType;
	boolean containsKey = formMap.containsKey(key);
	if (!containsKey) {
	    SearchParameterQueryForm form = new SearchParameterQueryForm(dicCode, dicType);
	    formMap.put(key, form);
	}

	List<UnifiedConfig> _unifiedConfigs = new ArrayList<UnifiedConfig>();

	List<UnifiedConfig> unifiedConfigs = (List<UnifiedConfig>) CacheManager.getCache(UnifiedConfigCacheHandler.NET_EASIPAY_DSFC_UNIFIEDCONFIG, !containsKey);

	if (unifiedConfigs == null) return _unifiedConfigs;

	for (UnifiedConfig unifiedConfig : unifiedConfigs) {
	    if (StringUitls.isNotBlank(dicCode) && StringUitls.isBlank(dicType) && unifiedConfig.getDicCode().equals(dicCode)) {
		_unifiedConfigs.add(unifiedConfig);
		continue;
	    }
	    if (StringUitls.isBlank(dicCode) && StringUitls.isNotBlank(dicType) && unifiedConfig.getDicType().equals(dicType)) {
		_unifiedConfigs.add(unifiedConfig);
		continue;
	    }
	    if (StringUitls.isNotBlank(dicCode) && StringUitls.isNotBlank(dicType) && unifiedConfig.getDicCode().equals(dicCode) && unifiedConfig.getDicType().equals(dicType)) {
		_unifiedConfigs.add(unifiedConfig);
		continue;
	    }
	}
	return _unifiedConfigs;
    }
}
