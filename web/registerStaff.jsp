<%-- 
    Document   : registerStaff
    Created on : 5 May 2022, 2:16:02 pm
    Author     : raunak K
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Register Staff Page</title>
    </head>
    <header>
        <h1>IoTBay</h1>
    </header>
    <%
        String registerError = (String) request.getAttribute("registerError");
        String errorMessage = registerError == null ? "" : registerError;
    %>
    <body class="noNavBody">
        <h1>Register Staff for IoTBay</h1>
        <form action="RegisterStaffServlet" method="POST">
            <table border="0px">
                <tr>
                    <td><label for="fname">First Name</label></td>  
                    <td><label for="lname">Last Name</label></td> 
                </tr>
                <tr>
                    <td><input class="extendfield" type="text" placeholder="Enter your first name" name="fname"></input></td>
                    <td><input class="extendfield" type="text" placeholder="Enter your last name" name="lname"></input></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>  
                </tr>
                <tr>
                    <td colspan = "2"><input class="extendfield" type="text" placeholder="Enter your email" name="email"></input></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone Number</label></td>  
                </tr>
                <tr>
                    <td colspan = "2"><input class="extendfield" type="text" placeholder="Enter your phone number" name="phone"></input></td>
                </tr>
                <tr>
                    <td><label for="phone">Staff Number</label></td>  
                </tr>
                <tr>
                    <td colspan = "2"><input class="extendfield" type="text" placeholder="Enter your staff number" name="staffNo"></input></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                </tr>
                <tr>
                    <td colspan = "2"><input class="extendfield" type="password" placeholder="Enter your password" name="password"></input></td>
                </tr>
                <tr>
                    <td colspan = "2"><input type="checkbox" name="tos">   I agree to the terms of service.</input></td>
                </tr>
                <tr>
                    <td>         </td>
                </tr>
                <tr>
                    <td colspan="2"><p style="color:red"><%=errorMessage%></td>
                </tr>
                <tr>
                    <td><a id="cancel" class="extendfield" id="cancel" href="index.jsp">Cancel</a></td>
                    <td><input style="cursor:pointer" class="extendfield" id = "submit" type="submit" value="Sign Up"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
