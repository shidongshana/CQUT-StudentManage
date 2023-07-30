package com.example.servlet;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
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
import java.io.PrintWriter;
import java.util.Map;

@Log
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
        PrintWriter writer = resp.getWriter();
        Context context = new Context();
        engine.process("login", context, writer);
    }
 @Override
    protected  void  doPost(HttpServletRequest req ,HttpServletResponse resp)throws ServletException,IOException{
     //首先设置一下响应类型

     req.setCharacterEncoding("UTF-8");
     resp.setCharacterEncoding("UTF-8");
     resp.setContentType("text/html;charset=UTF-8");
     //获取POST请求携带的表单数据
     PrintWriter out=resp.getWriter();
     //判断表单是否完整
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        try (SqlSession session = factory.openSession(true)){
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user=mapper.getUser(username,password);
            User user1=mapper.getUserByName(username);
            if(user!=null){
                out.print("success");
            }else if(user1!=null){
                out.print("fail1");
            }
            else{
                out.print("fail2");
            }
        }

 }
}