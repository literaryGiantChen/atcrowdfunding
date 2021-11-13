package com.diu.crowd.service.impl;

import com.diu.crowd.entity.Auth;
import com.diu.crowd.entity.AuthExample;
import com.diu.crowd.mapper.AuthMapper;
import com.diu.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DIU
 * @date 2021/11/9 10:14
 */
@Service
public class AuthServiceImpl implements AuthService {

    public AuthMapper authMapper;

    @Autowired
    public AuthServiceImpl(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }

    @Override
    public List<Auth> getAllAuth() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public int deleteOldRelationship(Integer roleId) {
        return authMapper.deleteOldRelationship(roleId);
    }

    @Override
    public void insertNewRelationship(Map<String, List<Integer>> map) {
        // 1.获取 roleId 的值 
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);

        // 2.删除旧关联关系数据
        authMapper.deleteOldRelationship(roleId);

        // 3.获取 authIdList
        List<Integer> authIdList = map.get("authIdArray");

        // 4.判断 authIdList 是否有效
        if (authIdList != null && authIdList.size() > 0) {
            int result = authMapper.insertNewRelationship(roleId, authIdList);
        }
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }

}
