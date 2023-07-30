package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {
    @Select("select * from student ")
    List<Student> getAllStudent();
    @Insert("insert into student(id,name,college,major,grade,clazz,age) values(#{id},#{name},#{college},#{major},#{grade},#{clazz},#{age})")
    public void insertStudent(Student student);
    @Select("select * from student where tid=#{tid}")
    List<Student> getStudentById(String tid);
    @Insert("insert into backup(id,name,college,major,grade,clazz,age) values(#{id},#{name},#{college},#{major},#{grade},#{clazz},#{age})")
    public void insertBackup(Student student);
    @Delete("delete from student where tid=#{tid}")
    public void deleteStudent(int tid);
    @Update("update student set  name=#{name},college=#{college},major=#{major},grade=#{grade},clazz=#{clazz},age=#{age} where id=#{id}")
    public void updateStudent(Student student);
}
