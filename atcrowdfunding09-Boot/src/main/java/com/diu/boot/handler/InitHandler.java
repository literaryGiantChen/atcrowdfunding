package com.diu.boot.handler;

import com.diu.boot.entity.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author DIU
 * @date 2021/11/12 15:24
 */
@Controller
public class InitHandler {

    @Value(value = "${server.port}")
    public String server;

    @ResponseBody
    @RequestMapping(value = "/get/spring/boot/init/message", method = RequestMethod.GET)
    public String initTest() {
        return "当前服务启动端口号是：" + this.server;
    }

    @RequestMapping(value = "/go/to/index", method = RequestMethod.GET)
    public String goIndex(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("str", "您TM终于获取到我的消息了！");
        modelMap.addAttribute("arr", Arrays.asList(1, 2, 3, 4, 5, 6));
        session.setAttribute("adminStudent", new Student());
        return "index";
    }
}
