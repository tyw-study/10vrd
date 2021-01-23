package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowLoginServlet", urlPatterns = "/showlogin")
public class ShowLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ///显示登录页面
        Context context = new Context();
        //取出用户名和密码
        Cookie[] cookies = request.getCookies();
        //判断非空
        if (cookies != null) {
            //遍历Cookie中保存的是否是用户名
            for (Cookie cookie : cookies) {
                //判断Cookie中保存的是否是用户名
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();//取出cookie中的用户名
                    //把用户名保存到Context容器中
                    context.setVariable("username",username);
                }
                //判断cookie中是否是密码
                if (cookie.getName().equals("password")) {
                    String password = cookie.getValue();
                    context.setVariable("password", password);
                }

            }
        }
        ThUtils.print("login.html", context, response);
    }
}
