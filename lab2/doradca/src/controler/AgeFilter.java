package controler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "AgeFilter")
public class AgeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        RequestDispatcher rdObj = null;

        resp.setContentType("text/html");
        resp.setCharacterEncoding("windows-1250");
        PrintWriter out = resp.getWriter();

        int age = Integer.parseInt(req.getParameter("age"));

//        if(age<18){
//            out.println("Zawartosc niedostepna dla malolata!");
//            out.close();
//        }else{
//            chain.doFilter(req, resp);
//        }


        if(age<18){
            out.println("Wstep wzbroniony");
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req,resp);
        }else{
            rdObj = req.getRequestDispatcher("/form.html");
            rdObj.include(req,resp);
//            chain.doFilter(req, resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
