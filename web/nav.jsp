<%-- 
    Document   : nav
    Created on : 10 May 2022, 6:20:39 pm
    Author     : raunak
--%>
<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Navbar</title>
    </head>
    <body>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <h3 id="name">IoTBay</h1>
                </li>
                <li class="nav-item">
                    <a  class="nav-link" href="#">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="products.jsp">View Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="savedOrder.jsp">Saved Order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="OrderHistoryServlet">Order History</a>
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
                <li id="logout-btn">
                    <a id="nav-right" href="logout.jsp">Logout</a>
                </li>
            </ul>
    </body>
</html>
