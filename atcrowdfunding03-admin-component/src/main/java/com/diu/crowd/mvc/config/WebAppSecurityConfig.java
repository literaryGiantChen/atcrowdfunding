package com.diu.crowd.mvc.config;

import com.diu.crowd.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author DIU
 * @date 2021/11/10 17:19
 */
// 注意！这个类一定要放在自动扫描的包下，否则所有配置都不会生效！
// 将当前类标记为配置类
@Configuration
// 启用Web环境下权限控制功能
@EnableWebSecurity
// 启用全局方法权限控制功能，并且设置 prePostEnabled = true。保证@PreAuthority、@PostAuthority、@PreFilter、@PostFilter 生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    public CrowdUserDetailsService userDetailsService;
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebAppSecurityConfig(CrowdUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()  // 对请求进行授权
                .antMatchers("/admin/to/login/page.html")       // 针对登录页进行设置
                .permitAll()         // 无条件访问
                .antMatchers("/bootstrap/**") // 针对静态资源进行设置，无条件访问
                .permitAll() // 针对静态资源进行设置，无条件访问
                .antMatchers("/crowd/**") // 针对静态资源进行设置，无条件访问
                .permitAll() // 针对静态资源进行设置，无条件访问
                .antMatchers("/css/**") // 针对静态资源进行设置，无条件访问
                .permitAll() // 针对静态资源进行设置，无条件访问
                .antMatchers("/fonts/**") // 针对静态资源进行设置，无条件访问
                .permitAll() // 针对静态资源进行设置，无条件访问
                .antMatchers("/img/**") // 针对静态资源进行设置，无条件访问
                .permitAll() // 针对静态资源进行设置，无条件访问
                .antMatchers("/jquery/**") // 针对静态资源进行设置，无条件访问
                .permitAll() // 针对静态资源进行设置，无条件访问
                .antMatchers("/layer/**") // 针对静态资源进行设置，无条件访问
                .permitAll() // 针对静态资源进行设置，无条件访问
                .antMatchers("/script/**") // 针对静态资源进行设置，无条件访问
                .permitAll() // 针对静态资源进行设置，无条件访问
                .antMatchers("/ztree/**") // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/") // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/admin/get/page.html")
                .access("hasRole('经理') OR hasRole('次长') OR hasRole('组长')")
                .antMatchers("/role/to/page.html")
                .access("hasRole('次长') OR hasRole('部长')")
                .antMatchers("/menu/to/permission.html")
                .access("hasRole('次长') OR hasAuthority('menu:select')")
                .anyRequest()
                .authenticated()
                .anyRequest() // 其他任意请求
                .authenticated() // 认证后访问
                .and()
                .csrf() // 防跨站请求伪造功能
                .disable() // 禁用
                .formLogin() // 开启表单登录的功能
                .loginPage("/admin/to/login/page.html") // 指定登录页面
                .loginProcessingUrl("/security/do/login.html") // 指定处理登录请求的地址 将不会走自己写的LoginHandler方法了 所以在后期要使用数据库登录，就要修改security登陆的方法
                .defaultSuccessUrl("/admin/to/main/page.html")// 指定登录成功后前往的地址
                .usernameParameter("loginAcct") // 账号的请求参数名称
                .passwordParameter("userPswd") // 密码的请求参数名称
                .and()
                .logout()
                .logoutUrl("/security/do/logout.html")
                .logoutSuccessUrl("/admin/to/login/page.html")
                .and()
                .exceptionHandling()
                //自己定义security的异常处理器 new AccessDeniedHandler()
                .accessDeniedHandler((request, response, accessDeniedException) -> request.setAttribute("exception", new Exception(CrowdConstant.MESSAGE_ACCESS_DENIED)))
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 设置固定的账号密码
        // builder.inMemoryAuthentication().withUser("tom").password("tom123").roles("ADMIN");

        // 通过数据库查询登录
        builder.userDetailsService(this.userDetailsService).passwordEncoder(this.bCryptPasswordEncoder);
    }

}
