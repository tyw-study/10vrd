package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import com.sun.org.apache.xalan.internal.xslt.Process;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowSendServlet", urlPatterns = "/showsend")
public class ShowSendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否扽登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            //重定向到登录页面
            response.sendRedirect("/showlogin");
            return;//方法结束
        }
        //显示发布页面
        Context context = new Context();
        //查询所有分类
        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.fiadAll();
        context.setVariable("list", list);

        ThUtils.print("send.html", context, response);
    }
}
