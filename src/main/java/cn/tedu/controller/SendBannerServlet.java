package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entity.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendBannerServlet", urlPatterns = "/sendbanner")
public class SendBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        /*获取上传文件信息*/
        String info = filePart.getHeader("content-disposition");
        /*获取后缀名*/
        String suffix = info.substring(info.lastIndexOf("."), info.length() - 1);
        /*获取唯一标识*/
        String fileName = UUID.randomUUID() + suffix;
        /*得到Tomcat管辖images路径*/
        String path = request.getServletContext().getRealPath("images/");
        /*保存文件*/
        filePart.write(path + fileName);
        /*保存轮播图路径到数据库中*/
        Banner banner = new Banner(0, "images/" + fileName);
        BannerDao dao = new BannerDao();
        dao.insert(banner);
        //重定向到轮播图页面
        response.sendRedirect("/showbanner");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
