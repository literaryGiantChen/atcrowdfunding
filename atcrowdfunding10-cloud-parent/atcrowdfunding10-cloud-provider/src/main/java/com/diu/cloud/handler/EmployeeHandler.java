package com.diu.cloud.handler;

import com.diu.cloud.entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DIU
 * @date 2021/11/13 13:30
 */
@RestController
public class EmployeeHandler {

    @RequestMapping(value = "/provider/get/employee/remote", method = RequestMethod.GET)
    public Employee getEmployeeRemote() {
        return new Employee(555, "tom555", 555.55);
    }

}
