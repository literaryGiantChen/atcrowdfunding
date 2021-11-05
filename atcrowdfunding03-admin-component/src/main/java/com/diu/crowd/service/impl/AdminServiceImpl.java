package com.diu.crowd.service.impl;

import com.diu.crowd.constant.CrowdConstant;
import com.diu.crowd.entity.Admin;
import com.diu.crowd.exception.LoginAcctAlreadyInUseException;
import com.diu.crowd.exception.LoginFailedException;
import com.diu.crowd.mapper.AdminMapper;
import com.diu.crowd.service.api.AdminService;
import com.diu.crowd.utils.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

        // 生成当前系统时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);

        // 考虑到该方法 是增加和修改共用的 所以做一下判断 密码没有值就是update操作
        if (admin.getUserPswd() != null) {
            // 将用户的密码加密
            admin.setUserPswd(CrowdUtil.md5(admin.getUserPswd()));
        } else {
            throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_PWD);
        }

        try {
            // 前提是要在数据库中将账号该字段 设置唯一
            // 执行保存，如果账户被占用会抛出异常
            return adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            // 检测当前捕获的异常对象，如果是 DuplicateKeyException 类型说明是账号重复导 致的
            if (e instanceof DuplicateKeyException) {
                // 抛出自定义的 LoginAcctAlreadyInUseException 异常
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            // 为了不掩盖问题，如果当前捕获到的不是 DuplicateKeyException 类型的异常，则 把当前捕获到的异常对象继续向上抛出
            throw e;
        }
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
        PageHelper.startPage(pageNum, pageSize);

        // 2.执行查询
        List<Admin> adminLists = adminMapper.selectAdminByKeyword(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<>(adminLists);
    }

    @Override
    public int remove(Integer adminId) {
        return adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getByAdminId(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

}
