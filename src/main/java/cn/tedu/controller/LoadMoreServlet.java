package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadMoreServlet", urlPatterns = "/loadmore")
public class LoadMoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String count = request.getParameter("count");
        System.out.println("客户端已有的数量" + count);
        //创建Dao并调用加载更多
        ProductDao dao = new ProductDao();
        List<Product> list = dao.loadMore(count);
        //把集合转换成json 字符串
        ObjectMapper om = new ObjectMapper();
        String jsonStr = om.writeValueAsString(list);
        //把接收到的数据返回给客户端
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(jsonStr);
        pw.close();

    }
}
