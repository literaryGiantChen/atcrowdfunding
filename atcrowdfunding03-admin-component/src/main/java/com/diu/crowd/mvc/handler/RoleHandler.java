package com.diu.crowd.mvc.handler;

import com.diu.crowd.entity.Role;
import com.diu.crowd.service.api.RoleService;
import com.diu.crowd.utils.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author DIU
 * @date 2021/11/5 12:40
 */
@Controller
public class RoleHandler {

    public RoleService roleService;

    @Autowired
    public RoleHandler(RoleService roleService) {
        this.roleService = roleService;
    }

    @ResponseBody
    @RequestMapping(value = "role/get/page/info.json", method = RequestMethod.POST)
    public ResultEntity<PageInfo<Role>> getPageInfo(
            // 使用@RequestParam注解的defaultValue属性，指定默认值，在请求中没有携带对应参数时使用默认值
            // keyword默认值使用空字符串，和SQL语句配合实现两种情况适配
            @RequestParam(value = "keyword", defaultValue = "") String keyword,

            // pageNum默认值使用1  当前分页
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,

            // pageSize默认值使用5 分页总数
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        // 执行分页查询操作
        PageInfo<Role> rolePageInfo = roleService.getRolePageInfo(keyword, pageNum, pageSize);

        return ResultEntity.successWithData(rolePageInfo);
    }
}
