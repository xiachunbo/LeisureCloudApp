package com.drops.interceptor;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthorityInterceptor
        implements HandlerInterceptor {
    public Logger logger = Logger.getLogger(AuthorityInterceptor.class);
    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();

    static {
        NOT_INTERCEPT_URI.add("/static/plateform/**");
        NOT_INTERCEPT_URI.add("/login");
        NOT_INTERCEPT_URI.add("/loginAjax");
        NOT_INTERCEPT_URI.add("/error");
        NOT_INTERCEPT_URI.add("/app/index");
    }


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String uri = request.getRequestURI();
        if (NOT_INTERCEPT_URI.contains(uri)) {
            return true;
        }

        HttpSession session = request.getSession();
        String userJson = (String) session.getAttribute("user_info_in_the_session");
        if (userJson == null) {
            this.logger.info("拦截"+ uri);
                    response.sendRedirect(request.getContextPath() + "/loginOut");
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
    }
}
