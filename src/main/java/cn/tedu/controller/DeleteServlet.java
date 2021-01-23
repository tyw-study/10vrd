package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;
import com.sun.media.jfxmediaimpl.HostUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDao pDao = new ProductDao();
        //得到作品的详细信息，从详细信息中得到租聘图片的路径
        Product p = pDao.findById(id);
        //通过图片的相对路径得到绝对路径
        System.out.println(p.getUrl());
        String path = request.getServletContext().getRealPath(p.getUrl());
        System.out.println("图片完整路径" + path);
        //删除路径对应的文件
        new File(path).delete();

        pDao.deleteById(id);
        response.sendRedirect("/home");
    }
}
