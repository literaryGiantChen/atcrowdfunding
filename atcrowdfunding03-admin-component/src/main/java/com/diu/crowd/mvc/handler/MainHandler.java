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
import org.springframework.web.bind.annotation.PathVariable;
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

            // pageNum默认值使用1  当前分页
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,

            // pageSize默认值使用5 分页总数
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,

            ModelMap modelMap) {
        // 查询得到分页数据
        PageInfo<Admin> adminPageInfo = adminService.getAdminPageInfo(keyword, pageNum, pageSize);
        // 将分页数据存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, adminPageInfo);
        return "admin-page";
    }

    @RequestMapping(value = "/admin/remove/{adminId}/{pageNum}/{keyword}.html", method = RequestMethod.GET)
    public String removeAdmin(@PathVariable("adminId") Integer adminId,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("keyword") String keyword) {
        // 物理删除ID号的数据 推荐使用逻辑删除
        adminService.remove(adminId);

        // 同时为了保持原本所在的页面和查询关键词再附加pageNum和keyword两个请求参数
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping(value = "/admin/save.html", method = RequestMethod.POST)
    public String saveAdmin(Admin admin) {
        logger.info("保存用户信息：{}", admin);
        // 执行保存数据
        adminService.saveAdmin(admin);

        // 重定向到分页页面，使用重定向是为了避免刷新浏览器重复提交表单
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    @RequestMapping(value = "/admin/to/edit/page.html", method = RequestMethod.GET)
    public String updateAdmin(@RequestParam("adminId") Integer adminId,
                              @RequestParam("pageNum") String pageNum,
                              @RequestParam("keyword") String keyword,
                              ModelMap modelMap) {
        // 执行获取数据
        Admin byAdminId = adminService.getByAdminId(adminId);

        // 将数据存入视图Map中
        modelMap.addAttribute("admin", byAdminId);
        return "admin-edit";
    }

    @RequestMapping(value = "/admin/update.html", method = RequestMethod.POST)
    public String adminUpdateAndSave(Admin admin,
                                     @RequestParam("pageNum") String pageNum,
                                     @RequestParam("keyword") String keyword) {
        logger.info("修改用户信息：{}", admin);
        // 执行修改数据
        adminService.updateAdmin(admin);

        // 执行完修改 跳回之前修改的分页模块
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }
}
