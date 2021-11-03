package com.diu.crowd.mvc.handler;

import com.diu.crowd.constant.CrowdConstant;
import com.diu.crowd.entity.Admin;
import com.diu.crowd.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author DIU
 * @date 2021/11/1 21:53
 */
@Controller
public class LoginHandler {

    public final AdminService adminService;
    public final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    @Autowired
    public LoginHandler(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/admin/do/admin-login.html", method = RequestMethod.POST)
    public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd, HttpSession session) {
        Admin adminByLoginAcct = adminService.getAdminByLoginAcct(loginAcct, userPswd);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN, adminByLoginAcct);
        // 将session中的数据设置为 一小时过期
        session.setMaxInactiveInterval(60 * 60);
        // 重定向到没有业务处理的方法，直接就访问admin页面，防止用户再次提交表单。
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping(value = "/admin/do/logout.html", method = RequestMethod.GET)
    public String doLogout(HttpSession session) {
        // 停止使用HttpSession
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

}
