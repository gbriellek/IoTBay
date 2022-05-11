<%-- 
    Document   : welcome
    Created on : 23 Mar. 2022, 3:34:17 pm
    Author     : Sarah F
--%>

<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Welcome Page</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <%
            String name = ""; 
            if (userType.equals("admin")) {
                name = "Sys Admin";
            }
            else if (userType.equals("customer")) {
                Customer customer = (Customer) session.getAttribute("user");
                name = customer.getFirstName() + " " + customer.getLastName();
            }
            else if (userType.equals("staff")) {
                Staff staff = (Staff) session.getAttribute("user");
                name = staff.getFirstName() + " " + staff.getLastName();
            }
        %>
        <div class="welcome">
            <table id="welcomeTable">
                <tr VALIGN="TOP">
                    <td id="info" width="35%">
                        <h1 class = "hiUser">Hi <%=name%>!</h1>
                        <p class = "hiUser">Welcome to IoTBay.<br> <br> We bring you the best quality products and greatest variety.</p>
                    </td>
                    <td width="20%">
                        
                    </td>
                    <td id="withImg" width="60%">
                        <img id="iotImage" src="iot-graphic.png" alt="iot-graphic">
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
