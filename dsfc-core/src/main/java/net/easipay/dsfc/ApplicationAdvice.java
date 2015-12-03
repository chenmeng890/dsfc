/**
 * 
 */
package net.easipay.dsfc;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public interface ApplicationAdvice
{
    public void doBefore(ApplicationAdviceMessage message) throws ApplicationAdviceException;

    public void doAround(ApplicationAdviceMessage message) throws ApplicationAdviceException;

    public void doAfter(ApplicationAdviceMessage message) throws ApplicationAdviceException;

    public void doThrowing(ApplicationAdviceMessage message) throws ApplicationAdviceException;
}
