package com.diu.crowd.service.impl;

import com.diu.crowd.mapper.RoleMapper;
import com.diu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
}
