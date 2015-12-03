/**
 * 
 */
package net.easipay.dsfc.remote;

import java.io.Serializable;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class SearchParameterQueryForm implements Serializable
{
    private static final long serialVersionUID = -3894281017311472319L;

    private String dicCode;
    private String dicType;

    public SearchParameterQueryForm()
    {
	super();
    }

    public SearchParameterQueryForm(String dicCode, String dicType)
    {
	this.dicCode = dicCode;
	this.dicType = dicType;
    }

    public String getDicCode()
    {
	return dicCode;
    }

    public void setDicCode(String dicCode)
    {
	this.dicCode = dicCode;
    }

    public String getDicType()
    {
	return dicType;
    }

    public void setDicType(String dicType)
    {
	this.dicType = dicType;
    }

}
