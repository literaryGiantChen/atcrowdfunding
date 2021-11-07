package com.diu.crowd.mvc.handler;

import com.diu.crowd.entity.Role;
import com.diu.crowd.service.api.RoleService;
import com.diu.crowd.utils.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DIU
 * @date 2021/11/5 12:40
 */
@RestController
public class RoleHandler {

    public RoleService roleService;

    @Autowired
    public RoleHandler(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/role/get/page/info.json", method = RequestMethod.POST)
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        // 执行分页查询操作
        PageInfo<Role> rolePageInfo = roleService.getRolePageInfo(keyword, pageNum, pageSize);

        return ResultEntity.successWithData(rolePageInfo);
    }

    @RequestMapping(value = "/role/save.json", method = RequestMethod.POST, produces = {"application/json"})
    public ResultEntity<String> saveRole(Role role) {
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping(value = "role/update.json", method = RequestMethod.POST, produces = {"application/json"})
    public ResultEntity<String> updateRole(Role role) {
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping(value = "/role/remove/by/role/id/array.json", method = RequestMethod.POST, produces = {"application/json"})
    public ResultEntity<String> deleteByArray(@RequestBody List<Integer> roleArray) {
        roleService.removeRole(roleArray);
        return ResultEntity.successWithoutData();
    }

}
