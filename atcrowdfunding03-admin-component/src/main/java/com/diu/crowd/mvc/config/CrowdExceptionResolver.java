package com.diu.crowd.mvc.config;

import com.diu.crowd.constant.CrowdConstant;
import com.diu.crowd.exception.AccessForbiddenException;
import com.diu.crowd.exception.LoginFailedException;
import com.diu.crowd.utils.CrowdUtil;
import com.diu.crowd.utils.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author DIU
 * @date 2021/11/1 9:48
 * - @ControllerAdvice表示当前类是基于注解的异常处理器类
 */
@ControllerAdvice
public class CrowdExceptionResolver {

    /**
     * 未登录处理
     *
     * @return 视图对象
     */
    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolverAccessForbiddenException(AccessForbiddenException accessForbiddenException, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return this.commonResolver(viewName, CrowdConstant.ATTR_NAME_EXCEPTION, accessForbiddenException, request, response);
    }

    /**
     * 将一个具体的异常类型和一个方法关联起来
     * </p>
     * 账号或密码为空异常
     *
     * @return 视图对象
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolverLoginException(LoginFailedException loginFailedException, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        // 参数一：要将异常信息打印在那个视图中，说白就是html页面
        // 参数二：对应着 request.getAttribute("exception") 属性名
        // 参数三：对应着 request.setAttribute("exception", exception) 属性值
        return this.commonResolver(viewName, CrowdConstant.ATTR_NAME_EXCEPTION, loginFailedException, request, response);
    }

    /**
     * 将一个具体的异常类型和一个方法关联起来
     * </p>
     * 算术错误异常
     *
     * @return 视图对象
     */

    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolverArithmeticException(ArithmeticException arithmeticException, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return this.commonResolver(viewName, CrowdConstant.ATTR_NAME_EXCEPTION, arithmeticException, request, response);
    }

    /**
     * 将一个具体的异常类型和一个方法关联起来
     * </p>
     * 空指针异常
     *
     * @return 视图对象
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullPointerException(NullPointerException nullPointerException, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return this.commonResolver(viewName, CrowdConstant.ATTR_NAME_EXCEPTION, nullPointerException, request, response);
    }

    /**
     * @param viewName          处理异常完毕 要前往的页面
     * @param attrNameException 异常模型名
     * @param exception         异常信息
     * @param request           当前请求对象
     * @param response          当前响应对象
     * @return 视图对象
     * @throws IOException 处理IO流异常
     */
    private ModelAndView commonResolver(String viewName, String attrNameException, Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.判断当前请求类型
        boolean judgeRequestResult = CrowdUtil.judgeRequestType(request);

        // 2.如果是ajax请求类型
        if (judgeRequestResult) {
            // 3.获取异常信息
            String message = exception.getMessage();

            // 4.创建 ResultEntity
            ResultEntity<Object> resultEntity = ResultEntity.failed(message);

            // 5.将 resultEntity 转化为 JSON 字符串
            String jsonData = new Gson().toJson(resultEntity);

            // 6.把当前 JSON 字符串作为当前请求的响应体数据返回给 浏览器
            // ①获取 Writer 对象
            PrintWriter writer = response.getWriter();
            // ②写入数据
            writer.println(jsonData);

            // 7.返回 null，不给 SpringMVC 提供 ModelAndView 对象
            // 这样 SpringMVC 就知道不需要框架解析视图来提供响应， 而是程序员自己提供了响应
            return null;
        }

        // 8.创建 ModelAndView 对象
        ModelAndView modelAndView = new ModelAndView();

        // 9.将 Exception 对象存入模型
        modelAndView.addObject(attrNameException, exception);

        // 10.设置对应的视图名
        modelAndView.setViewName(viewName);

        // 11.返回视图对象
        return modelAndView;
    }

}
