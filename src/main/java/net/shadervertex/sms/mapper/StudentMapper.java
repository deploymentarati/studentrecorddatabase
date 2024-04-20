package net.shadervertex.sms.mapper;

import net.shadervertex.sms.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    int updateStudent(Student student); // Change return type to int
}
