package servlet;

import model.PersonalDetails;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

@WebFilter(filterName = "Login")
public class Login implements Filter {
    Vector<PersonalDetails> personalDetails = new Vector<>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        RequestDispatcher rdObj = null;

        resp.setContentType("text/html");
        resp.setCharacterEncoding("windows-1250");
        PrintWriter out = resp.getWriter();


        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        Enumeration e = personalDetails.elements();

        if(login.isEmpty()){
            out.println("Podaj login");
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req,resp);
        }else if (pass.isEmpty()){
            out.println("Podaj haslo");
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req,resp);
        }else{
            while(e.hasMoreElements()){
                PersonalDetails person = (PersonalDetails) e.nextElement();
                if(person.login.equals(login) && person.pass.equals(pass)) {
                    HttpServletRequest request = (HttpServletRequest) req;
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedPerson", person);
                    chain.doFilter(req, resp);
                }
            }
            out.println("Bledne dane logowania");
            rdObj = req.getRequestDispatcher("/index.jsp");
            rdObj.include(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
        personalDetails.add(new PersonalDetails("login",  "pass", "Luke", "Skywalker"));
        personalDetails.add(new PersonalDetails("login1",  "pass1", "Han", "Solo"));
        personalDetails.add(new PersonalDetails("login2",  "pass2", "Yo", "Da"));
        personalDetails.add(new PersonalDetails("login3",  "pass3", "Obi-Wan", "Kenobi"));


    }

}
