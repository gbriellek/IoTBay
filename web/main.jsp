<%-- 
    Document   : main.jsp
    Created on : 23 Mar. 2022, 3:34:17 pm
    Author     : Sarah
--%>

<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Main Page</title>
    </head>
    <header>
        <h1>IoTBay</h1>
    </header>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            if (customer != null) {
        %>
        <h1>My Account</h1>
        <table style="border-collapse: collapse">
            <tr class="profile">
                <td><p style="font-weight:bold">First Name</p></td>
                <td><p><%=customer.getFirstName()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Last Name</p></td>
                <td><p><%=customer.getLastName()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Email</p></td>  
                <td><p><%=customer.getEmailAddress()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Phone Number</p></td>  
                <td><p><%=customer.getPhoneNumber()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Password</p></td>
                <td><p><%=customer.getPassword()%></p></td>
            </tr>
        </table>
            <div class="container">
                <a class="mainbtn" href="logout.jsp">Logout</a>
            </div>
        <% }else {%>
        <p>You are not logged in</p>
        <a href="index.jsp">home</a>
        <%}%>
    </body>
</html>
