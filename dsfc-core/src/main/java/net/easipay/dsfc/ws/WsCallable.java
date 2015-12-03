/**
 * 
 */
package net.easipay.dsfc.ws;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public interface WsCallable
{
    public WsResult call(boolean paramBoolean) throws WsException;
}
