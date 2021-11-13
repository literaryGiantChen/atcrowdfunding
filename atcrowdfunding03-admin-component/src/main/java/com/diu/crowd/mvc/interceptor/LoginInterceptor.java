package com.diu.crowd.mvc.interceptor;

import com.diu.crowd.constant.CrowdConstant;
import com.diu.crowd.entity.Admin;
import com.diu.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author DIU
 * @date 2021/11/2 16:00
 */
public class LoginInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.通过 request 对象获取 Session 对象
        HttpSession session = request.getSession();
        // 2.尝试从 Session 域中获取 Admin 对象
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        // 3.判断 admin 对象是否为空 
        if (admin == null) {
            // 4.抛出异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        // 5.如果 Admin 对象不为 null，则返回 true 放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public LoginInterceptor() {
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

    }
}
