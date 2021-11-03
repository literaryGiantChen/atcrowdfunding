package com.diu.crowd.service.impl;

import com.diu.crowd.constant.CrowdConstant;
import com.diu.crowd.entity.Admin;
import com.diu.crowd.exception.LoginFailedException;
import com.diu.crowd.mapper.AdminMapper;
import com.diu.crowd.service.api.AdminService;
import com.diu.crowd.utils.CrowdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author DIU
 * @date 2021/10/31 15:56
 */
@Service
public class AdminServiceImpl implements AdminService {

    public final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public int saveAdmin(Admin admin) {
        if (admin == null) {
            throw new NullPointerException();
        }
        return adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(null);
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1.根据登录账号查询Admin对象
        Admin admin = adminMapper.selectByLoginAcct(loginAcct);

        // 使用AdminExample 获取Admin对象
//        AdminExample adminExample = new AdminExample();
//        adminExample.createCriteria().andLoginAcctEqualTo(loginAcct);
//        List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2.判断Admin对象是否为null
        if (admin == null) {

            // 3.抛出异常 The account or password is empty, please re-enter
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 4.将Admin对象中的密码取出来
        String dataUserPswd = admin.getUserPswd();

        // 5.将表单提交的明文密码进行加密
        String userPswdForm = CrowdUtil.md5(userPswd);

        // 6.对密码进行比较
        assert userPswdForm != null;
        if (!Objects.equals(dataUserPswd, userPswdForm)) {

            // 7.如果不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 8.如果一致则返回Admin对象
        return admin;
    }

    @Override
    public PageInfo<Admin> getAdminPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1.调用PageHelper的静态方法开启分页功能
        Page<Admin> adminPage = PageHelper.startPage(pageNum, pageSize);

        // 2.执行查询
        List<Admin> adminLists = adminMapper.selectAdminByKeyword(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<>(adminLists);
    }


}
