package com.diu.crowd.mvc.config;

import com.diu.crowd.entity.Admin;
import com.diu.crowd.entity.Role;
import com.diu.crowd.service.api.AdminService;
import com.diu.crowd.service.api.AuthService;
import com.diu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DIU
 * @date 2021/11/10 23:21
 */
@Component
public class CrowdUserDetailsService implements UserDetailsService {

    private final AdminService adminService;
    private final RoleService roleService;
    private final AuthService authService;

    @Autowired
    public CrowdUserDetailsService(AdminService adminService, RoleService roleService, AuthService authService) {
        this.adminService = adminService;
        this.roleService = roleService;
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.根据账号名称查询 Admin 对象
        Admin admin = adminService.getAdminByLoginAcct(username);

        // 2.获取 admin Id
        Integer adminId = admin.getId();

        // 3.根据 adminId 查询角色信息
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);

        // 4.根据 adminId 查询权限信息
        List<String> authNameList = authService.getAssignedAuthNameByAdminId(adminId);

        // 5.创建集合对象用来存储 GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 6.遍历 assignedRoleList 存入角色信息
        for (Role role : assignedRoleList) {
            // 注意：不要忘了加前缀！
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new
                    SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }

        // 7.遍历 authNameList 存入权限信息
        for (String authName : authNameList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new
                    SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        }

        // 8.封装 SecurityAdmin 对象
        return new SecurityAdmin(admin, authorities);
    }

}
