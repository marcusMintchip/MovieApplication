package gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

public class PreFilter extends ZuulFilter {

    private  static volatile AtomicInteger COUNT = new AtomicInteger();
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.containsKey(FORWARD_TO_KEY) && ! context.containsKey(SERVICE_ID_KEY);
    }

    @Override
    public Object run() throws ZuulException {
//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletRequest request = context.getRequest();
//        if(request.getParameter("sample")==null){
//            context.put(SERVICE_ID_KEY,request.getParameter("foo"));
//        }
        return null;
    }

    public void runSQL(){

    }
}
