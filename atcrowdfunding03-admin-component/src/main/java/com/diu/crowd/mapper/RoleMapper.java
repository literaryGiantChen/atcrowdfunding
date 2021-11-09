package com.diu.crowd.mapper;

import com.diu.crowd.entity.Role;
import com.diu.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectRoleByKeyword(String keyword);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    void updateByPrimaryKey(Role record);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}