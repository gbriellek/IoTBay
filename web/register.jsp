<%-- 
    Document   : register.jsp
    Created on : 23 Mar. 2022, 3:21:39 pm
    Author     : Jemma Swaak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register for IoTBay</h1>
            <form action="welcome.jsp" method="POST">
                <input type="hidden" name="requestType", value="register">
                <table>
                    <tr>
                        <td><label for="fname">First Name:</label></td>  
                        <td><label for="lname">Last Name:</label></td> 
                        
                    </tr>
                    <tr>
                         <td><input type="text" placeholder="Enter your first name" name="fname" required></input></td>
                        <td><input type="text" placeholder="Enter your last name" name="lname" required></input></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email:</label></td>  
                    </tr>
                    <tr>
                        <td colspan = "2"><input class="extendfield" type="email" placeholder="Enter your email" name="email" required></input></td>
                    </tr>
                    <tr>
                        <td><label for="phone">Phone Number:</label></td>
                    </tr>
                    <tr>
                        <td colspan = "2"><input class="extendfield" type="tel" placeholder="Enter your phone number" name="phone" required></input></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password:</label></td>
                    </tr>
                    <tr>
                        <td colspan = "2"><input class="extendfield" type="password" placeholder="Enter your password" name="password" required></input></td>
                    </tr>
                    <tr>
                        <td><label for="tos">Terms of Service:</label><input type="checkbox" name="tos">I agree</input></td>
                    </tr>
            </table>
            <div>
                <input type="submit" value="Sign Up">
                <a class="button" href="index.jsp">Cancel</a>
            </div>
        </form>
    </body>
</html>
