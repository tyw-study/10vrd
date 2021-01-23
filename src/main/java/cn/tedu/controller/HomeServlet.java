package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取传递过来的参数
        String cid = request.getParameter("cid");
        String keyword = request.getParameter("keyword");


        //显示页面
        Context context = new Context();
        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.fiadAll();
        context.setVariable("list", list);

        BannerDao bannerDao = new BannerDao();
        List<Banner> bList = bannerDao.findAll();
        System.out.println(bList);
        context.setVariable("bList", bList);

        //获取Session对象
        HttpSession session = request.getSession();
        //获取保存的用户对象
        User user = (User) session.getAttribute("user");
        /*if (user == null) {
            System.out.println("没登录过");
        } else {
            System.out.println("登陆过");
        }*/
        //把用户名对象装进容器
        context.setVariable("user", user);

        //查询出所有作品并装进context容器
        ProductDao pDao = new ProductDao();
        if (cid != null) {//说明查询的是某个分类的作品
                //cid有值说明需要查询某个分类的所有作品
                List<Product> pList = pDao.findByCID(cid);
                context.setVariable("pList", pList);
        } else if(keyword!=null){
            List<Product> pList =pDao.findByKeyword(keyword);
            context.setVariable("pList",pList);
        } else {//查询的是所有作品
            List<Product> pList = pDao.findAll();
            context.setVariable("pList", pList);
        }
        //浏览最多
        List<Product> vList = pDao.findViewList();
        context.setVariable("vList", vList);

        //最受欢迎
        List<Product> lList = pDao.findLikeList();
        context.setVariable("lList", lList);

        //搜索框


        ThUtils.print("home.html", context, response);

    }
}
