<%-- 
    Document   : index
    Created on : 23 Mar. 2022, 2:33:00 pm
    Author     : Mia Z
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>IoT Bay</title>
    </head>
    <header>
        <h1>IoTBay</h1>
    </header>
    <body class="noNavBody">
        <h1>Welcome to IoTBay!</h1>
        <div class="container">
            <a class="mainbtn" href="registerSelect.jsp" >Register</a> 
            <a class="mainbtn" href="login.jsp" >Login</a>
            <a class="mainbtn" href="UserEnterServlet">View Products</a>
        </div>


        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
