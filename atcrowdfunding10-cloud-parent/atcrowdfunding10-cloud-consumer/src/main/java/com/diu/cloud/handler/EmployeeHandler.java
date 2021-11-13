package com.diu.cloud.handler;

import com.diu.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author DIU
 * @date 2021/11/13 13:30
 */
@RestController
public class EmployeeHandler {

    public final RestTemplate restTemplate;

    @Autowired
    public EmployeeHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/consumer/ribbon/get/employee/remote", method = RequestMethod.GET)
    public Employee getEmployeeRemote() {
        return restTemplate.getForObject("http://DIU-PROVIDER/provider/get/employee/remote", Employee.class);
    }

}
