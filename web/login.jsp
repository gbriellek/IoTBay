<%-- 
    Document   : LoginPage
    Created on : 30 Mar. 2022, 2:27:28 pm
    Author     : rauna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Login</title>
    </head>
    <body>
        <h1></h1>
        <h1>Login</h1>
        <table border = 1px>
            <form action="welcome.jsp" method="POST">
                <input type="hidden" name="requestType", value="login">
                <tr>
                    <td><label for="email">Email: </label></td>
                </tr>
                <tr>
                    <td colspan = "2"><input type="email" id="email" name="email"><br></td>
                </tr>
                <tr>
                    <td><label for="password">Password: </label></td>
                </tr>
                <tr>
                    <td colspan = "2"><input type="password" id="password" name="password"><br></td>
                </tr>
                <tr>
                    <td><input class="extendfield" id= "submit" type="submit" value="Login"></td>
                    <td><a class="extendfield" href='index.jsp'>Back</a></td>
                </tr>
            </form>
        </table>
    </body>
</html>
