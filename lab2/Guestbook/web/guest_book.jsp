<%@ page import="model.PersonalDetails" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 15.03.19
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <title>Księga gości</title>
</head>
<body>

<%
    try{
        PersonalDetails person = (PersonalDetails) session.getAttribute("loggedPerson");
        out.println("You are logged in as "+ person.name +" "+ person.lastName);
    }catch(NullPointerException e){
        response.sendRedirect("");
    }
%>

<br>
<b>Please submit your feedback:</b>
<form action="add_comment" method="post">
    Your name: <input type="text" name="name"><br>
    Your email: <input type="text" name="email"><br>
    Your comment: <input type="text" name="comment"><br>
    <input type="submit" name="Whaaaat">
    <%--<input type="submit" name="Send Feedback">--%>
</form>

<%
    try {
        Vector<Comment> comments = (Vector<Comment>) application.getAttribute("comments");
        Enumeration enumeration = comments.elements();
        while(enumeration.hasMoreElements()){
            Comment comment = (Comment) enumeration.nextElement();
            out.print("<br>");
            out.println(comment);
            out.print("<br>");
        }
    }catch (Exception e){
        out.println("No comments");
    }
%>


</body>
</html>
