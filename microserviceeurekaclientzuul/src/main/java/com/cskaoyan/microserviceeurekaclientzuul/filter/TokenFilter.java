package com.cskaoyan.microserviceeurekaclientzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

@Component
public class TokenFilter extends ZuulFilter {

    //fitter是前置执行 还请求完成之后执行
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //数字越小 优先级越高
    @Override
    public int filterOrder() {
        return  SERVLET_DETECTION_FILTER_ORDER-1;
    }

    //判断是否执行
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //当前filter具体要做的事情
    //如果带了token，就放行，不带就返回404
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            currentContext.setResponseStatusCode(404);
            currentContext.setSendZuulResponse(false);
        }
        return null;
    }
}
