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
        <link rel="stylesheet" href="nav.css"> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Main Page</title>
    </head>
    <body>
        <%
            // only staff and customer can view this page
           String userType = (String) session.getAttribute("userType");
            if (userType.equals("customer")) {
            Customer customer = (Customer)session.getAttribute("user");
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
        <% }else {
            //staff is viewing the page
            Staff staff = (Staff)session.getAttribute("user");
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
        
        <h1>My Account</h1>
        <table style="border-collapse: collapse">
            <tr class="profile">
                <td><p style="font-weight:bold">First Name</p></td>
                <td><p><%=staff.getFirstName()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Last Name</p></td>
                <td><p><%=staff.getLastName()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Email</p></td>  
                <td><p><%=staff.getEmailAddress()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Phone Number</p></td>  
                <td><p><%=staff.getPhoneNumber()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Staff Number</p></td>
                <td><p><%=staff.getStaffNumber()%></p></td>
            </tr>
            <tr class="profile">
                <td><p style="font-weight:bold">Password</p></td>
                <td><p><%=staff.getPassword()%></p></td>
            </tr>
        </table>
        <%}%>
    </body>
</html>
