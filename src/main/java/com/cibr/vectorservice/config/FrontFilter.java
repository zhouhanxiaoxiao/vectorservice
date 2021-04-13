package com.cibr.vectorservice.config;

import com.cibr.vectorservice.util.CommonUtil;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Configuration
@Order(value = 0)
public class FrontFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        request.setAttribute("userId","2c2068bf01fb4e40a6897f365035bd1b");
//        request.setAttribute("userId","24f5851abc6444e79be718325025126f");
        if (CommonUtil.isAjaxRequest(request)){
            HttpSession session = request.getSession(false);
            if (session != null) {
                System.out.println("requst path " + request.getServletPath());
                Assertion assertion = (Assertion) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
                if (assertion != null) {
                    System.out.println("cas user ---------> " + assertion.getPrincipal().getName());
                    Map<String, Object> attributes = assertion.getPrincipal().getAttributes();
                    String userId = (String) attributes.get("id");
                    request.setAttribute("userId", userId);
                    filterChain.doFilter(servletRequest,servletResponse);
                }
            }
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
