package servlet;

import model.Comment;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

@WebServlet(name = "AddComment")
public class AddComment extends HttpServlet {
    Vector<Comment> comments = new Vector<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nameParameter = request.getParameter("name");
        String emailParameter = request.getParameter("email");
        String commentParameter = request.getParameter("comment");

        Comment comment = new Comment(nameParameter, emailParameter, commentParameter);
        comments.add(comment);
        ServletContext context = getServletContext();
        context.setAttribute("comments", comments);
        response.sendRedirect("guest_book.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
