package net.easipay.dsfc.ws.support;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.easipay.dsfc.ws.fws.FwsHttpRequest;
import net.easipay.dsfc.ws.fws.FwsHttpResponse;
import net.easipay.dsfc.ws.jws.JwsHttpRequest;
import net.easipay.dsfc.ws.jws.JwsHttpResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Supports custom class type to add to the springMvc parameter list.
 * @author mchen
 * @date 2015-11-6
 */
public class SpringMvcHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver
{
    @Override
    public boolean supportsParameter(MethodParameter parameter)
    {
	Class<?> paramType = parameter.getParameterType();
	return JwsHttpRequest.class.isAssignableFrom(paramType) || JwsHttpResponse.class.isAssignableFrom(paramType) || FwsHttpRequest.class.isAssignableFrom(paramType) || FwsHttpResponse.class.isAssignableFrom(paramType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception
    {
	if (mavContainer != null) {
	    mavContainer.setRequestHandled(true);
	}

	Class<?> paramType = parameter.getParameterType();

	if (JwsHttpRequest.class.isAssignableFrom(paramType)) {
	    HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
	    return new JwsHttpRequest(nativeRequest);
	}
	else if (JwsHttpResponse.class.isAssignableFrom(paramType)) {
	    HttpServletResponse nativeResponse = webRequest.getNativeResponse(HttpServletResponse.class);
	    return new JwsHttpResponse(nativeResponse);
	}
	else if (FwsHttpRequest.class.isAssignableFrom(paramType)) {
	    HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
	    return new FwsHttpRequest(nativeRequest);
	}
	else if (FwsHttpResponse.class.isAssignableFrom(paramType)) {
	    HttpServletResponse nativeResponse = webRequest.getNativeResponse(HttpServletResponse.class);
	    return new FwsHttpResponse(nativeResponse);
	}
	// should never happen..
	Method method = parameter.getMethod();
	throw new UnsupportedOperationException("Unknown parameter type: " + paramType + " in method: " + method);
    }

}
