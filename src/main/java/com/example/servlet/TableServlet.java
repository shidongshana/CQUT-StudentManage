package com.example.servlet;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Log
@WebServlet("/table")
public class TableServlet  extends HttpServlet {
    public List<Student> studentList=new ArrayList<>();
    TemplateEngine engine;
    SqlSessionFactory factory;
    @SneakyThrows
    @Override
    public void init() throws ServletException {
        super.init();
        engine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        engine.setTemplateResolver(resolver);
        //初始化数据
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
    }
    public void SelectAll(){
        try (org.apache.ibatis.session.SqlSession sqlSession = factory.openSession(true)) {
           StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            studentList=studentMapper.getAllStudent();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Context context = new Context();
        SelectAll();
        context.setVariable("studentList",studentList);
        engine.process("table", context, writer);
    }
}
