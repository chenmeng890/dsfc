/**
 * 
 */
package net.easipay.dsfc.ws.fws;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FwsAnno
{
    int value();
}
