package com.diu.crowd.service.api;

import com.diu.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 用户业务接口
 *
 * @author DIU
 * @date 2021/10/31 15:56
 */
public interface AdminService {

    /**
     * 增加管理用户
     *
     * @param admin 管理员信息
     * @return 受影响的行数
     */
    public int saveAdmin(Admin admin);

    /**
     * 获取全部管理用户信息
     *
     * @return
     */
    public List<Admin> getAll();

    /**
     * 登录验证
     *
     * @param loginAcct 账号
     * @param userPswd  密码
     * @return admin对象
     */
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    /**
     * 分页搜索
     *
     * @param keyword  搜索信息
     * @param pageNum  当前分页的页数
     * @param pageSize 分页总数
     * @return
     */
    public PageInfo<Admin> getAdminPageInfo(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 删除Id号的数据
     *
     * @param adminId 用户ID
     * @return
     */
    public int remove(Integer adminId);

    /**
     * 查询用户ID数据
     *
     * @param adminId 用户ID
     * @return
     */
    public Admin getByAdminId(Integer adminId);

    /**
     * 修改
     *
     * @param admin 用户信息
     * @return
     */
    public int updateAdmin(Admin admin);
}
