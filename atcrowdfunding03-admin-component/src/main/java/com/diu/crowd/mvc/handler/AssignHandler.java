package com.diu.crowd.mvc.handler;

import com.diu.crowd.entity.Auth;
import com.diu.crowd.entity.Role;
import com.diu.crowd.service.api.AdminService;
import com.diu.crowd.service.api.AuthService;
import com.diu.crowd.service.api.RoleService;
import com.diu.crowd.utils.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author DIU
 * @date 2021/11/8 13:24
 */
@Controller
public class AssignHandler {

    private final Logger logger = LoggerFactory.getLogger(InitHandler.class);
    private final RoleService roleService;
    private final AdminService adminService;
    private final AuthService authService;

    @Autowired
    public AssignHandler(RoleService roleService, AdminService adminService, AuthService authService) {
        this.roleService = roleService;
        this.adminService = adminService;
        this.authService = authService;
    }

    @RequestMapping(value = "/assign/to/assign/role/page.html", method = RequestMethod.GET)
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {

        // 1.查询已分配角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);

        //  2.查询未分配角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);

        // 3.存入模型（本质上其实是：request.setAttribute("attrName",attrValue);
        modelMap.addAttribute("assignedRoleList", assignedRoleList);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);

        return "assign-role";
    }

    @RequestMapping(value = "/assign/do/role/assign.html", method = RequestMethod.POST)
    public String saveAdminRoleRelationship(@RequestParam("adminId") Integer adminId, @RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("keyword") String keyword,
                                            // 我们允许用户在页面上取消所有已分配角色再提交表单，所以可以不提供 roleIdList 请求参数
                                            // 设置 required=false 表示这个请求参数不是必须的
                                            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {
        this.logger.info(String.valueOf(roleIdList));
        adminService.saveAdminRoleRelationship(adminId, roleIdList);

        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @ResponseBody
    @RequestMapping(value = "/assign/get/all/auth.json", method = RequestMethod.POST, produces = {"application/json"})
    public ResultEntity<List<Auth>> getAllAuth() {
        // 执行获取数据方法
        List<Auth> allAuth = authService.getAllAuth();
        return ResultEntity.successWithData(allAuth);
    }

    @ResponseBody
    @RequestMapping(value = "assign/get/assigned/auth/id/by/role/id.json", method = RequestMethod.POST, produces = {"application/json"})
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(@RequestParam("roleId") Integer roleId) {
        // 执行获取角色id 的auth数据
        List<Integer> assignedAuthIdByRoleId = authService.getAssignedAuthIdByRoleId(roleId);
        logger.info("当前Id为{}的角色拥有的auth: {}", roleId, assignedAuthIdByRoleId);
        return ResultEntity.successWithData(assignedAuthIdByRoleId);
    }

    @ResponseBody
    @RequestMapping(value = "/assign/do/role/assign/auth.json", method = RequestMethod.POST, produces = {"application/json"})
    public ResultEntity<String> saveRoleAuthRelationship(@RequestBody Map<String, List<Integer>> map) {
        authService.insertNewRelationship(map);
        return ResultEntity.successWithoutData();
    }
}
