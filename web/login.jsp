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
        <title>Login</title>
    </head>
    <body>
        <h1></h1>
        <h1>Login</h1>
        <table>
            <form action="welcome.jsp" method="POST">
                <tr>
                    <td><label for="email">Email: </label></td>
                    <td><input type="email" id="email" name="email"><br></td>
                </tr>
                <tr>
                    <td><label for="password">Password: </label></td>
                    <td><input type="password" id="password" name="password"><br></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Login"></td>
                </tr>
                <tr>
                    <td><a href='index.jsp'>Back</a></td>
                    <td></td>
                </tr>
            </form>
        </table>
    </body>
</html>
