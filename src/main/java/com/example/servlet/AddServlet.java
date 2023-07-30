package com.example.servlet;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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
import java.util.Map;
@WebServlet("/add")
public class AddServlet extends HttpServlet {
    TemplateEngine engine;
    SqlSessionFactory factory;
    @SneakyThrows
    @Override
    public void init() throws ServletException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        super.init();
        engine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        engine.setTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        engine.process("AddStudent", new Context(), resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        if (CheckMap(req)){
            Map<String, String[]> map = req.getParameterMap();
            String id = map.get("id")[0];
            String name = map.get("name")[0];
            String college = map.get("college")[0];
            String major = map.get("major")[0];
            int grade = Integer.parseInt(map.get("grade")[0]);
            String clazz = map.get("clazz")[0];
            int age = Integer.parseInt(map.get("age")[0]);
            try (SqlSession sqlSession=factory.openSession(true)){
                StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);
                Student student=new Student(id,name,college,major,grade,clazz,age);
                studentMapper.insertStudent(student);
                resp.sendRedirect("table");
            }
        }
        else {
            resp.getWriter().write("错误，您的表单数据不完整！");
        }

    }
    public static boolean CheckMap(HttpServletRequest req){
        Map<String, String[]> map = req.getParameterMap();
        if(map.containsKey("id") && map.containsKey("name")&&map.containsKey("college")&&map.containsKey("major")&&map.containsKey("grade")&&map.containsKey("clazz")&&map.containsKey("age")) {
            return true;
        }
        else {
            return false;
        }
    }
}
