package com.springboot.dive.mapper;

import com.springboot.dive.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface StudentMapper {
    @Insert("insert into student(sno, sname,ssex) values(#{sno},#{sname},#{sex})")
    int add(Student student);

    @Update("update student set sname=#{name}, ssex=#{sex} where ssno=#{sno}")
    int update(Student student);

    @Delete("delete from student where sno=#{sno}")
    int deleteBySno(String sno);

    @Select("select * from student where sno=#{sno}")
    @Results(id = "student", value = {
            @Result(property = "sno", column = "sno", javaType = String.class),
            @Result(property = "name", column = "sname", javaType = String.class),
            @Result(property = "sex", column = "ssex", javaType = String.class)
    })
    Student queryStudentBySno(String sno);
}
