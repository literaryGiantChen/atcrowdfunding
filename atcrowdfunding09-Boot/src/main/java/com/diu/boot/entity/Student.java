package com.diu.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    // 编号
    private Integer id;
    // 班级
    private Integer gradeId;
    // 姓名
    private String name;
    // 学生密码
    private String password;
    // 年龄
    private Integer age;
    // 现住地址
    private String address;
    // 手机号码
    private String phone;
    // 入学时间
    private java.sql.Timestamp admission;
    // 1表示毕业生 0表示在读生
    private Integer empStatus;

}
