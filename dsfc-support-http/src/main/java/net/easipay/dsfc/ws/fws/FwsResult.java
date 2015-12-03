/**
 * 
 */
package net.easipay.dsfc.ws.fws;

import net.easipay.dsfc.ws.WsResult;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class FwsResult extends WsResult
{

    @Override
    public String toResult()
    {
	return super.getCode() + "#" + super.getMessage();
    }

}
