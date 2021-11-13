package com.diu.boot.test;

import com.diu.boot.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Atcrowdfunding09BootApplicationTests {

    public StudentMapper studentMapper;

    @Autowired
    public Atcrowdfunding09BootApplicationTests(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Test
    public void contextLoads() {
        studentMapper.selectByAllStudent().forEach(System.out::println);
    }

}
