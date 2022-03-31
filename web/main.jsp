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
        <title>Main Page</title>
    </head>
    <body>
        <%
            Customer customer = (Customer)session.getAttribute("customer");
            if (customer != null) {
        %>
        <h1>My Account</h1>
        <table>
            <tr>
                <td><p>First Name:</p></td>
                <td><p><%=customer.getFirstName()%></p></td>
            </tr>
            <tr>
                <td><p>Last Name:</p></td>
                <td><p><%=customer.getLastName()%></p></td>
            </tr>
            <tr>
                <td><p>Email:</p></td>  
                <td><p><%=customer.getEmailAddress()%></p></td>
            </tr>
            <tr>
                <td><p>Phone Number:</p></td>  
                <td><p><%=customer.getPhoneNumber()%></p></td>
            </tr>
            <tr>
                <td><p>Password:</p></td>
                <td><p><%=customer.getPassword()%></p></td>
            </tr>
        </table>
        <a href="logout.jsp">Logout</a>
        <% }else {%>
        <p>You are not logged in</p>
        <a href="index.jsp">home</a>
        <%}%>
    </body>
</html>
