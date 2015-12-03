package net.easipay.dsfc.algorithm;

import java.util.List;
import java.util.Random;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WeightRandom
{
    static Integer weightSum = 0;
    private static Random random = new Random();
    List<WeightCategory> categorys;

    public List<WeightCategory> getCategorys()
    {
	return categorys;
    }

    public void setCategorys(List<WeightCategory> categorys)
    {
	this.categorys = categorys;
    }

    public WeightRandom(List<WeightCategory> categorys)
    {

	this.categorys = categorys;

	for (WeightCategory wc : categorys) {
	    weightSum += wc.getWeight();
	}
	if (weightSum <= 0) {
	    System.err.println("Error: weightSum=" + weightSum.toString());
	}
    }

    public String getWeight()
    {
	Integer n = random.nextInt(weightSum);
	Integer m = 0;
	String returnWeight = null;
	for (WeightCategory wc : categorys) {
	    if (m <= n && n < m + wc.getWeight()) {
		returnWeight = wc.getCategory();
		break;
	    }
	    m += wc.getWeight();
	}
	return returnWeight;
    }

}
