/**
 * 
 */
package net.easipay.dsfc.ws;

import net.easipay.dsfc.ApplicationAdviceMessage;

/**
 * 
 * @author mchen
 * @date 2015-11-6
 */
public class WsApplicationSimpleAdviceMessage extends ApplicationAdviceMessage
{
    private static final long serialVersionUID = -2567581334105114168L;
    private String input;
    private String output;
    public String getInput()
    {
        return input;
    }
    public void setInput(String input)
    {
        this.input = input;
    }
    public String getOutput()
    {
        return output;
    }
    public void setOutput(String output)
    {
        this.output = output;
    }
}
