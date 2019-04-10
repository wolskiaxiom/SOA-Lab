<%--
  Created by IntelliJ IDEA.
  User: lukasz
  Date: 15.03.19
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Logowanie</title>
  </head>
  <body>

  <form action="goToGuestBook" method="post">
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="pass"/>
    <input type="submit" value="Zaloguj"/>
  </form>

  </body>
</html>
