package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.media.jfxmediaimpl.HostUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Json1Servlet", urlPatterns = "/json1")
public class Json1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器端只能给客户端返回json字符串
        ProductDao dao = new ProductDao();
        List<Product> list = dao.findAll();
        //把集合装换成json字符串  需要利用一些三方框架进行转换
        ObjectMapper om = new ObjectMapper();
        //这个方法可以将任何对象转换成json字符串
        String jsonStr = om.writeValueAsString(list);
        System.out.println(jsonStr);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(jsonStr);
        pw.close();
    }
}
