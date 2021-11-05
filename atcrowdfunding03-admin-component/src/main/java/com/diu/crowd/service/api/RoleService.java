package com.diu.crowd.service.api;

import com.diu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

/**
 * 角色业务接口
 *
 * @author DIU
 * @date 2021/11/5 9:57
 */
public interface RoleService {

    /**
     * 分页搜索
     *
     * @param keyword  搜索信息
     * @param pageNum  当前分页的页数
     * @param pageSize 分页总数
     * @return
     */
    public PageInfo<Role> getRolePageInfo(String keyword, Integer pageNum, Integer pageSize);

}
