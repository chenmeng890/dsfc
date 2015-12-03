/**
 * 
 */
package net.easipay.dsfc.ws.wss;

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
@Target(ElementType.METHOD)
public @interface WssRequestMapping
{
    String value();
    
    String service();

    WssRequestMethod[] method();

    String desc() default "";
}
