package com.diu.boot.mapper;

import com.diu.boot.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author DIU
 * @date 2021/11/12 16:27
 */
@Mapper
public interface StudentMapper {

    public List<Student> selectByAllStudent();

}
