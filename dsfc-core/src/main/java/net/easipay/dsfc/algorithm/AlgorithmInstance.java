package net.easipay.dsfc.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.easipay.dsfc.Service;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class AlgorithmInstance
{

    public static Service algorithmLookup(List<Service> services)
    {
	Service service = services.get(0);
	if ("1".equals(service.getRouteArithmetic())) {
	    return weightAlgorithm(services);
	}
	else {
	    return probabilityAlgorithm(services);
	}
    }

    /**
     * 轮询查找
     * 
     * @param services
     * @return
     */
    public static Service probabilityAlgorithm(List<Service> services)
    {
	Random r = new Random();
	return services.get(r.nextInt(services.size()));
    }

    /**
     * 权重查找
     * 
     * @param services
     * @return
     */
    public static Service weightAlgorithm(List<Service> services)
    {
	List<WeightCategory> categorys = new ArrayList<WeightCategory>();
	WeightCategory weightCategory = null;
	for (int i = 0; i < services.size(); i++) {
	    weightCategory = new WeightCategory(String.valueOf(i), Integer.valueOf(services.get(i).getArithmeticValue()));
	    categorys.add(weightCategory);
	}

	WeightRandom random = new WeightRandom(categorys);
	return services.get(Integer.valueOf(random.getWeight()));
    }

}
