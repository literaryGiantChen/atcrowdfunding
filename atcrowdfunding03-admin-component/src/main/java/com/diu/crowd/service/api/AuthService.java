package com.diu.crowd.service.api;

import com.diu.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author DIU
 * @date 2021/11/9 10:14
 */
public interface AuthService {

    /**
     * 查询已分配的 Auth 的 id 组成的数组
     *
     * @param roleId 角色id
     * @return
     */
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    /**
     * 获取全部Auth数据
     *
     * @return
     */
    public List<Auth> getAllAuth();

    /**
     * 删除
     *
     * @param roleId 角色id
     * @return
     */
    public int deleteOldRelationship(Integer roleId);

    /**
     * 增加Auth数据
     *
     * @return
     */
    public void insertNewRelationship(Map<String, List<Integer>> map);
}
