<%-- 
    Document   : navbar
    Created on : 3 May 2022, 7:55:46 pm
    Author     : raunak
--%>
<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="nav.css"> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Navbar</title>
    </head>
    <body>
        <%
        String userType = (String) session.getAttribute("userType");
        if (userType.equals("admin")) {
        %>
        <nav id="navbar" class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container-fluid">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <h3 id="name">IoTBay</h1>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="accessLogs.jsp">Access Logs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="products.jsp">View Products</a>
                </li>
                <li class=" testing">
                    <a id="logout" class="nav-link test" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
        </nav>
        <%
            } else if (userType.equals("customer")) {
            Customer customer = (Customer) session.getAttribute("user");
        %>
        
        <nav id="navbar" class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container-fluid">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <h3 id="name">IoTBay</h1>
                </li>
                <li class="nav-item">
                    <a  class="nav-link" href="main.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="products.jsp">View Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="savedOrder.jsp">Saved Order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="orderHistory.jsp">Order History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="shipmentHistory.jsp">Shipment History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="paymentHistory.jsp">Payment History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="accessLogs.jsp">Access Logs</a>
                </li>
                <li class=" testing">
                    <a id="logout" class="nav-link test" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
        </nav>
        
        <%
            } else if (userType.equals("staff")) {
            Staff staff = (Staff) session.getAttribute("user");
        %>
        
        <nav id="navbar" class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container-fluid">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <h3 id="name">IoTBay</h1>
                </li>
                <li class="nav-item">
                    <a  class="nav-link" href="main.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="accessLogs.jsp">Access Logs</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="products.jsp">View Products</a>
                </li>
                <li class=" testing">
                    <a id="logout" class="nav-link test" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
        </nav>
        
        <% 
            } else {
        %>
                <nav id="navbar" class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container-fluid">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <h3 id="name">IoTBay</h1>
                </li>
                <li class="nav-item">
                    <a  class="nav-link" href="main.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="products.jsp">View Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="savedOrder.jsp">Saved Order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="accessLogs.jsp">Access Logs</a>
                </li>
                <li class=" testing">
                    <a id="logout" class="nav-link test" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
        </nav>
        <% } %>
    </body>
</html>
