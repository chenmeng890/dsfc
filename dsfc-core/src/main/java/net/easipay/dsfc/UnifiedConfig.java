/**
 * 
 */
package net.easipay.dsfc;

import java.io.Serializable;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class UnifiedConfig implements Serializable
{
    private static final long serialVersionUID = -4862093549878751791L;

    private String dicCode;
    private String dicValue;
    private String dicType;
    private String dicDesc;
    private String dicTypeDesc;
    private String memo;

    public String getDicCode()
    {
	return dicCode;
    }

    public void setDicCode(String dicCode)
    {
	this.dicCode = dicCode;
    }

    public String getDicValue()
    {
	return dicValue;
    }

    public void setDicValue(String dicValue)
    {
	this.dicValue = dicValue;
    }

    public String getDicType()
    {
	return dicType;
    }

    public void setDicType(String dicType)
    {
	this.dicType = dicType;
    }

    public String getDicDesc()
    {
	return dicDesc;
    }

    public void setDicDesc(String dicDesc)
    {
	this.dicDesc = dicDesc;
    }

    public String getDicTypeDesc()
    {
        return dicTypeDesc;
    }

    public void setDicTypeDesc(String dicTypeDesc)
    {
        this.dicTypeDesc = dicTypeDesc;
    }

    public String getMemo()
    {
	return memo;
    }

    public void setMemo(String memo)
    {
	this.memo = memo;
    }

}
