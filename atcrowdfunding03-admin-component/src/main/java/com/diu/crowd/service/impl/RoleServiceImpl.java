package com.diu.crowd.service.impl;

import com.diu.crowd.entity.Role;
import com.diu.crowd.entity.RoleExample;
import com.diu.crowd.mapper.RoleMapper;
import com.diu.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DIU
 * @date 2021/11/5 9:58
 */
@Service
public class RoleServiceImpl implements RoleService {

    public final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public PageInfo<Role> getRolePageInfo(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roleLists = roleMapper.selectRoleByKeyword(keyword);
        return new PageInfo<>(roleLists);
    }

    @Override
    public int saveRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int removeRole(List<Integer> roleArray) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        // delete from `t_role` where `id` on (lists[0], lists[1], ...);
        criteria.andIdIn(roleArray);
        return roleMapper.deleteByExample(roleExample);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

}
