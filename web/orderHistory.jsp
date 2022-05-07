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
        <!--insert nav bar-->
        <h1>Order History</h1>
        <p>search bar stuff</p>
        <form action="FilterOrders" method="POST">
            <table border="0px">
                <tr>
                    <td><label for="orderID">Order ID</label></td>  
                    <td><input type="text" name="orderID"></input></td>
                    <td><label for="lname">Order Date</label></td> 
                    <td><input type="text" name="orderDate"></input></td>
                    <td><input type="submit" value="Filter"></td>
                </tr>
            </table>
        </form>
         <%
            String orderError = (String) request.getAttribute("OrderError");
            if (orderError != null) {
                %>
                <p><%=orderError%></p>
        <%
            }   else {
                // get the past orders + orderlines from session
                ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders"); 
                ArrayList<ArrayList<OrderLine>> orderLines = (ArrayList<ArrayList<OrderLine>>) request.getAttribute("orderLines");
                ArrayList<ArrayList<String>> productNames = (ArrayList<ArrayList<String>>) request.getAttribute("productNames");

                // loop through them and display them
                for (int i = 0; i < orders.size(); i ++) {
                Order o = orders.get(i);
        %>
        <div id="order">
            <p id="orderID"><strong>Order ID:</strong> <%=o.getOrderID()%></p>
            <table id="orderTable" style="border-collapse: collapse">
                <tr class="profile">
                    <td><p style="font-weight:bold">Shipment Detail ID</p></td>
                    <td><p><%=o.getShipmentDetailID()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Payment ID</p></td>  
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
                    <td><p style="font-weight:bold">Price</p></td>
                </tr>

                 <%
                    //spit out orderline
                    for  (int j = 0; j < orderLines.get(i).size(); j++){
                        OrderLine ol = orderLines.get(i).get(j);
                        String pName = productNames.get(i).get(j); 
                        System.out.println(pName);
                %>
                <tr class="profile">
                    <td style="width:40%"><p><%=pName%></p></td>
                    <td style="width:30%"><p><%=ol.getQuantity()%></p></td>
                    <td><p>$<%=ol.getPrice()%>0</p></td>
                </tr>
                <%}%>
                <tr>
                    <td><p style="font-weight:bold">Total Cost</p></td>
                    <td><p>$<%=o.getTotalCost()%>0</p></td>
                </tr> 
            </table>
        </div>            
        <%}}%>
    </body>
</html>
