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
    <header>
        <h1>IoTBay</h1>
    </header>
    <body>
        <h1>Login</h1>
        <table>
            <form action="welcome.jsp" method="POST">
                <input type="hidden" name="requestType", value="login">
                <tr>
                    <td colspan = "2"><label for="email">Email</label></td>
                </tr>
                <tr>
                    <td colspan = "2"><input class="extendfield"placeholder="Enter your email" type="email" id="email" name="email"><br></td>
                </tr>
                <tr>
                    <td colspan = "2"><label for="password">Password</label></td>
                </tr>
                <tr>
                    <td colspan = "2"><input class="extendfield" placeholder="Enter your password" type="password" id="password" name="password"><br></td>
                </tr>
                <tr>
                    <td><a class="extendfield" id="cancel" href='index.jsp'>Back</a></td>
                    <td><input class="extendfield" id= "submit" type="submit" value="Login"></td>
                </tr>
            </form>
        </table>
    </body>
</html>
