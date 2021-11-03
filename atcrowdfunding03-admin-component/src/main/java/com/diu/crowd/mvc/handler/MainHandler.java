package com.diu.crowd.mvc.handler;

import com.diu.crowd.constant.CrowdConstant;
import com.diu.crowd.entity.Admin;
import com.diu.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author DIU
 * @date 2021/11/3 17:44
 */
@Controller
public class MainHandler {

    public final AdminService adminService;
    public final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    @Autowired
    public MainHandler(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/admin/get/page.html", method = RequestMethod.GET)
    public String getPageInfo(
            // 使用@RequestParam注解的defaultValue属性，指定默认值，在请求中没有携带对应参数时使用默认值
            // keyword默认值使用空字符串，和SQL语句配合实现两种情况适配
            @RequestParam(value = "keyword", defaultValue = "") String keyword,

            // pageNum默认值使用1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,

            // pageSize默认值使用5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,

            ModelMap modelMap) {
        //
        PageInfo<Admin> adminPageInfo = adminService.getAdminPageInfo(keyword, pageNum, pageSize);
        //
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, adminPageInfo);
        return "admin-page";
    }

}
