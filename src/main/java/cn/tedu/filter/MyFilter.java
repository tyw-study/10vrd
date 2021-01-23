package cn.tedu.filter;

import cn.tedu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*urlPartterns设置处理路径通过{}方式设置多个页面*/
@WebFilter(filterName = "MyFilter", urlPatterns = {"/showsend", "/showbanner"})
public class MyFilter implements Filter {
    //对象销毁前执行的方法
    public void destroy() {
    }

    /*开始执行过滤的方法*/
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //chain.doFilter(req, resp);
        //过滤器中的过滤器请求和响应对象为Servlet里面对应的父类
        //使用强转转回HttpServletxxxx
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取session对象
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //判断是否登录
        if (user == null) {//没有登陆过
            System.out.println("没登陆过：拦截！！！");
            response.sendRedirect("/showlogin");//显示登录页面

        } else {//登陆过
            System.out.println("登陆过 放行");
            /*写到下面代码之前的内容 会在触发Servlet或文件资源之前执行*/
            chain.doFilter(req, resp);//放行
            /*写在后面的代码就是执行完Servlet或文件资源之后会触发*/

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
