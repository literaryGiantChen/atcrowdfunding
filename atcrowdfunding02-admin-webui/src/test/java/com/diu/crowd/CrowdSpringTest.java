package com.diu.crowd;

import com.diu.crowd.entity.Admin;
import com.diu.crowd.mapper.AdminMapper;
import com.diu.crowd.service.api.AdminService;
import com.diu.crowd.utils.CrowdUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DIU
 * @date 2021/10/30 16:35
 */
// 指定 Spring 给 Junit 提供的运行器类
@RunWith(SpringJUnit4ClassRunner.class)
// 加载 Spring 配置文件的注解
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdSpringTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testDataSource() throws SQLException {
        // 1.通过数据源对象获取数据源连接
        Connection connection = dataSource.getConnection();
        // 2.打印数据库连接
        System.out.println(connection);
    }

    /**
     * <insert id="insert" parameterType="com.diu.crowd.entity.Admin">
     * insert into t_admin (id, login_acct, user_pswd,
     * user_name, email, create_time)
     * values (#{id,jdbcType=INTEGER}, #{loginAcct,jdbcType=VARCHAR}, #{userPswd,jdbcType=CHAR},
     * #{userName,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{createTime,jdbcType=CHAR})
     * </insert>
     */
    @Test
    public void insertAdmin() {
        Admin admin = new Admin(null, "Camille", CrowdUtil.md5("camille123"), "卡蜜拉", "camille123@gamin.com", "2021-11-03");
        int insert = adminMapper.insert(admin);
        System.out.println("受影响的行数：" + insert);
    }

    private final Log log = LogFactory.getLog(CrowdSpringTest.class);
    private final Logger logger = LoggerFactory.getLogger(CrowdSpringTest.class);

    @Test
    public void testLog() {
        this.log.info("info  ->> 执行成功！！！");
    }

    @Autowired
    public AdminService adminService;

    @Test
    public void test02() {
        for (int i = 0; i < 351; i++) {
            adminMapper.insert(new Admin(null, "Edith" + i, CrowdUtil.md5("edith123" + i), "伊蒂丝" + i, "edith321" + i + "@gamin.com", "2021-11-04"));
        }
    }

}