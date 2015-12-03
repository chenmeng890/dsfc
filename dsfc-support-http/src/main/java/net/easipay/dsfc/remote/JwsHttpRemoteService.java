/**
 * 
 */
package net.easipay.dsfc.remote;

import java.util.List;

import net.easipay.dsfc.Service;
import net.easipay.dsfc.UnifiedConfig;
import net.easipay.dsfc.remote.AbstractRemoteService;
import net.easipay.dsfc.remote.SearchParameterQueryForm;
import net.easipay.dsfc.ws.jws.JwsClient;
import net.easipay.dsfc.ws.jws.JwsResult;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class JwsHttpRemoteService extends AbstractRemoteService
{
    @Override
    public void registeService(List<Service> services)
    {
	JwsClient jwsClient = new JwsClient("dsfc-0001");
	jwsClient.putParam("dstbServices", services).call();
    }

    @Override
    public List<Service> searchService(List<String> serviceId)
    {
	JwsClient jwsClient = new JwsClient("dsfc-0002");
	JwsResult jwsResult = jwsClient.putParam("dstbServices", serviceId).call();
	return jwsResult.getList("dstbServicesList", Service.class);
    }

    @Override
    public void manageMonitor(String serviceId, Integer cacheNum, String errorNo, String errorDesc, String operateType)
    {
	JwsClient jwsClient = new JwsClient("dsfc-0003");
	jwsClient.putParam("serviceId", serviceId).putParam("cacheNum", cacheNum).putParam("errorNo", errorNo).putParam("errorDesc", errorDesc).putParam("operateType", operateType).call();
    }

    @Override
    public List<UnifiedConfig> searchParameter(List<SearchParameterQueryForm> forms)
    {
	JwsClient jwsClient = new JwsClient("dsfc-0004");
	JwsResult jwsResult = jwsClient.putParam("dsfsSysDics", forms).call();
	return jwsResult.getList("dsfsSysDicList", UnifiedConfig.class);
    }

}
