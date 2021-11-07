package com.diu.crowd.service.api;

import com.diu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

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

    /**
     * 增加角色信息
     *
     * @param role 角色信息
     * @return 受影响的行数
     */
    public int saveRole(Role role);

    /**
     * 删除角色信息
     *
     * @param roleArray 角色ID
     * @return 受影响的行数
     */
    public int removeRole(List<Integer> roleArray);

    /**
     * 修改角色信息
     *
     * @param role 角色信息
     */
    public void updateRole(Role role);

}
