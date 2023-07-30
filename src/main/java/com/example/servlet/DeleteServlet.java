package com.example.servlet;

import com.example.mapper.StudentMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    List<String>idList=new ArrayList<>();
    SqlSessionFactory factory;
    @SneakyThrows
    @Override
    public void init() throws ServletException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); //设置请求编码
        resp.setContentType("text/html;charset=utf-8"); //设置响应编码
        String ids = req.getParameter("ids");
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            String id1 = "";
        for (int i = 0; i <id.length(); i++) {
            if (id.charAt(i)>=48&&id.charAt(i)<=57){
                id1+=id.charAt(i);
            }
        }
        idList.add(id1);
        }
        for (String s : idList) {
            try (SqlSession sqlSession=factory.openSession(true)){
                StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);
                studentMapper.insertBackup(studentMapper.getStudentById(s).get(0));//备份删除
                studentMapper.deleteStudent(Integer.parseInt(s));
            }
        }

    }

}
