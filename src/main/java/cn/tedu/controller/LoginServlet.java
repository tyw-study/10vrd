package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginaction")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rem = request.getParameter("rem");
        System.out.println(rem);
        //创建UserDao 并调用login方法
        UserDao dao = new UserDao();
        //如果登陆成功返回user对象，否则返回null
        User user = dao.login(username, password);

        if (user != null) {
            if (rem != null) {//需要记住用户名和密码
                //创建Cookie把用户名和密码保存到cookie中
                Cookie c1 = new Cookie("username", username);
                Cookie c2 = new Cookie("password", password);
                //把Cookie下发给浏览器
                response.addCookie(c1);
                response.addCookie(c2);
                //修该cookie保存时间
                c1.setMaxAge(60*60*24*30);
            }
            //获取Session对象
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.println("登录成功！");
            //重定向到列表页面
            response.sendRedirect("/home");
        } else {
            System.out.println("登录失败");
            //重定向到登录页面
            response.sendRedirect("/showlogin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
