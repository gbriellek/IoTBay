<%-- 
    Document   : logoutPage
    Created on : 30 Mar. 2022, 2:47:59 pm
    Author     : rauna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <h1>Logout</h1>
        <p>You have been logged out. <br> Click <a href = index.jsp>here</a> to return to the homepage.</p>
        <%session.invalidate();%>
    </body>
</html>
