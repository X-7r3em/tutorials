package com.example.zuulproxyserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Component
public class PostFilter extends ZuulFilter {

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulResponseHeader("POST", "This is POST filter FROM ZUUL.");

        requestContext.getResponse().addHeader("POST NON-ZUUL", "This is post, NON-ZUUL");

        return null;
    }
}
