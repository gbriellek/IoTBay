<%-- 
    Document   : orderHistory
    Created on : 5 May 2022, 11:23:49 pm
    Author     : Sarah F
--%>

<%@page import="uts.isd.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Order History</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <h1>Order History</h1>
        <%
            String noOrdersError = (String) request.getAttribute("noOrdersError");
            if (noOrdersError != null) {
        %>
        <p style="color:red;"><%=noOrdersError%></p>
        <%} else{
            // ensures search bar retains the search criteria
            String requestOrderID = (String) request.getAttribute("orderID");
            String requestOrderDate = (String) request.getAttribute("orderDate");
            String fieldOrderID = requestOrderID == null ? "" : requestOrderID;
            String fieldOrderDate = requestOrderDate == null ? "" : requestOrderDate;
        %>
        <form action="FilterOrdersServlet" method="POST">
            <table class="searchTable" border="0px">
                <tr>
                    <td><label for="orderID">Order ID</label></td>  
                    <td><input class="searchField" type="text" name="orderID" placeholder="Enter an Order ID" value="<%=fieldOrderID%>"></input></td>
                    <td><label for="orderDate">Order Date</label></td> 
                    <td><input class="searchField" type="text" name="orderDate" placeholder="yyyy-mm-dd" value="<%=fieldOrderDate%>"></input></td>
                    <td><input type="submit" value="Filter" class="filterButton"></td>
                </tr>
            </table>
        </form>
        <%
            String orderError = (String) request.getAttribute("orderError");
        %>
        <p style="color:red;"><%=orderError == null ? "" : orderError%></p>
        <%
            // make new lists for display
            ArrayList<Order> orders = new ArrayList<Order>();
            ArrayList<ArrayList<OrderLine>> orderLines = new ArrayList<ArrayList<OrderLine>>();
            ArrayList<ArrayList<String>> productNames = new ArrayList<ArrayList<String>>();
            // get the filtered orders from request param
            ArrayList<Order> filterOrders = (ArrayList<Order>) request.getAttribute("filterOrders");
            //if there are filtered result then store them to show
            if (filterOrders != null && filterOrders.size() > 0) {
                orders = filterOrders;
                orderLines = (ArrayList<ArrayList<OrderLine>>) request.getAttribute("filterOrderLines");
                productNames = (ArrayList<ArrayList<String>>) request.getAttribute("filterProductNames");
            } else {
                // else get the past orders + orderlines from session
                orders = (ArrayList<Order>) session.getAttribute("orders");
                orderLines = (ArrayList<ArrayList<OrderLine>>) session.getAttribute("orderLines");
                productNames = (ArrayList<ArrayList<String>>) session.getAttribute("productNames");
            }

            // loop through them and display them
            for (int i = 0; i < orders.size(); i++) {
                Order o = orders.get(i);
        %>
        <div class="order">
            <p class="orderID"><strong>Order ID:</strong> <%=o.getOrderID()%></p>
            <table class="orderTable" style="border-collapse: collapse">
                <tr class="profile">
                    <td><p style="font-weight:bold">Shipment Detail ID</p></td>
                    <td><p><%=o.getShipmentDetailID()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Payment Information ID</p></td>  
                    <td><p><%=o.getPaymentInformationID()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Order Date</p></td>  
                    <td><p><%=o.getOrderDate()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Items</p></td>
                </tr>

                <tr class="profile">
                    <td><p style="font-weight:bold">Item</p></td>
                    <td><p style="font-weight:bold">Quantity</p></td>
                    <td><p style="font-weight:bold">Total Price</p></td>
                </tr>

                <%
                    //spit out orderline
                    for (int j = 0; j < orderLines.get(i).size(); j++) {
                        OrderLine ol = orderLines.get(i).get(j);
                        String pName = productNames.get(i).get(j);
                %>
                <tr class="profile">
                    <td style="width:40%"><p><%=pName%></p></td>
                    <td style="width:30%"><p><%=ol.getQuantity()%></p></td>
                    <td><p>$<%=ol.getPrice()%>0</p></td>
                </tr>
                <%}%>
                <tr class="profile">
                    <td><p style="font-weight:bold">Total Cost</p></td>
                    <td><p>$<%=o.getTotalCost()%>0</p></td>
                </tr> 
                <tr>
                    <td><p style="font-weight:bold">Order Status</p></td>
                    <td><p><%=o.getOrderStatus()%></p></td>
                </tr> 
            </table>
        </div>            
        <%
            }
        }
        %>
    </body>
</html>
