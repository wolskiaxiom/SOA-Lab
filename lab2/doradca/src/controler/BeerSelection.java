package controler;

import model.BeerExpert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;

@WebServlet(name = "BeerSelection")
public class BeerSelection extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String beerColor = request.getParameter("color");
        BeerExpert be = new BeerExpert(beerColor);

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        out.println("<html>");
        out.println("<head><title>Dowolna</title></head>");
        out.println("<body>");

        out.println(be.toString());

        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
