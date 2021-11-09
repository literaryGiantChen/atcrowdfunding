package com.diu.crowd.mvc.handler;

import com.diu.crowd.entity.Admin;
import com.diu.crowd.service.api.AdminService;
import com.diu.crowd.utils.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author DIU
 * @date 2021/10/31 20:30
 */
@Controller
public class InitHandler {

    private final Logger logger = LoggerFactory.getLogger(InitHandler.class);

    private final AdminService adminService;

    @Autowired
    public InitHandler(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "ssm/all/ssm.html", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Admin> listsAdmin = adminService.getAll();
        model.addAttribute("listsAdmin", listsAdmin);
        return "ssm";
    }

    @ResponseBody
    @RequestMapping(value = "/ssm/all/not_exception.html", method = RequestMethod.GET, produces = {"application/json"})
    public ResultEntity<String> test() {
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping(value = "/ssm/all/arithmetic_exception.html", method = RequestMethod.GET, produces = {"application/json"})
    public ResultEntity<String> test1() {
        int i = 10 / 0;
        return ResultEntity.successWithoutData();
    }

}
