<%-- 
    Document   : welcome
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
        <title>Welcome Page</title>
    </head>
    <header>
        <h1>IoTBay</h1>
    </header>
    <body>
        <%
        String userType = (String) session.getAttribute("userType");
        if (userType.equals("admin")) {
        %>
            <h1>Welcome back Sys Admin!</h1>
            <div class="container">
                <a class="mainbtn" href="accessLogs.jsp">Access Logs</a>
                <a class="mainbtn" href="products.jsp">View Products</a>
                <a class="mainbtn" href="logout.jsp">Logout</a>
            </div>
        <%
            } else if (userType.equals("customer")) {
            Customer customer = (Customer) session.getAttribute("user");
            String fname = customer.getFirstName();
            String lname = customer.getLastName();
        %>
            <h1>Hi <%=fname%> <%=lname%>!</h1>
            <div class="container">
                <a class="mainbtn" href="main.jsp">Profile</a>
                <a class="mainbtn" href="products.jsp">View Products</a>
                <a class="mainbtn" href="savedOrder.jsp">Saved Order</a>
                <a class="mainbtn" href="orderHistory.jsp">Order History</a>
                <a class="mainbtn" href="shipmentHistory.jsp">Shipment History</a>
                <a class="mainbtn" href="paymentHistory.jsp">Payment History</a>
                <a class="mainbtn" href="accessLogs.jsp">Access Logs</a>
                <a class="mainbtn" href="logout.jsp">Logout</a>
            </div>
        <%
            } else if (userType.equals("staff")) {
            Staff staff = (Staff) session.getAttribute("user");
            String fname = staff.getFirstName();
            String lname = staff.getLastName();
        %>
            <h1>Hi <%=fname%> <%=lname%>!</h1>
            <div class="container">
                <a class="mainbtn" href="main.jsp">Profile</a>
                <a class="mainbtn" href="accessLogs.jsp">Access Logs</a>
                <a class="mainbtn" href="products.jsp">View Products</a>
                <a class="mainbtn" href="logout.jsp">Logout</a>
            </div>
                <% }%>
    </body>
</html>
