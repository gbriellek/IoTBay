<%-- 
    Document   : savedOrder
    Created on : 5 May 2022, 11:23:49 pm
    Author     : Sarah F
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Saved Order</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <h1>Saved Order</h1>
        <%
            String orderSubmitted = (String) request.getAttribute("orderSubmitted");
            String noSavedOrderError = (String) request.getAttribute("noSavedOrderError");
            if (orderSubmitted != null) {
        %>
        <p><%=orderSubmitted%></p>
        <a href="OrderHistoryServlet">View Order History</a>
        <%
        } else if (noSavedOrderError != null) {
        %>
        <p><%=noSavedOrderError%></p>
        <a href="ProductServlet">View Products</a>
        <%
        } else {
            //get the saved order from the session
            Order savedOrder = (Order) session.getAttribute("savedOrder");
            ArrayList<OrderLine> orderLines = (ArrayList<OrderLine>) session.getAttribute("savedOrderLines");
            ArrayList<String> productNames = (ArrayList<String>) session.getAttribute("savedProductNames");
            // then display the order
            int shipID = savedOrder.getShipmentDetailID();
            int payID = savedOrder.getPaymentInformationID();
            //  get any savedOrderErrors from the request
            String savedOrderError = (String) request.getAttribute("savedOrderError");
        %>
        <p><%=savedOrderError == null ? "" : savedOrderError%></p>
        <div class="order" id="savedOrder">
            <p class="orderID"><strong>Order ID:</strong> <%=savedOrder.getOrderID()%></p>
            <table class="orderTable" style="border-collapse: collapse">
                <tr class="profile">
                    <td><p style="font-weight:bold">Shipment Detail ID</p></td>
                    <td colspan="3"><p><%=shipID > 0 ? shipID : "N/A"%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Payment ID</p></td>  
                    <td colspan="3"><p><%=payID > 0 ? payID : "N/A"%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Order Date</p></td>  
                    <td colspan="3"><p><%=savedOrder.getOrderDate()%></p></td>
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
                    for (int j = 0; j < orderLines.size(); j++) {
                        OrderLine ol = orderLines.get(j);
                        String pName = productNames.get(j);
                %>
                <tr class="profile">
                <form action="UpdateSavedOrderServlet" method="POST">
                    <input name="productID" type="hidden" value="<%=ol.getProductID()%>"></input>
                    <td style="width:30%"><p><%=pName%></p></td>
                    <td style="width:12%"><input style="width:80%" name="quantity" type="text" value="<%=ol.getQuantity()%>"></input></td>
                    <td style="width:10%"><p>$<%=ol.getPrice()%>0</p></td>
                    <td style="width:10%"><input style="cursor:pointer" class="savedItemButtons" value="Update Quantity" type="submit" ></input></td>                        
                </form>
                <form action="DeleteSavedItemServlet" method="POST">
                    <input name="productID" type="hidden" value="<%=ol.getProductID()%>"></input>
                    <td style="width:15%"><input style="cursor:pointer" class="savedItemButtons" value="Delete Item" type="submit" ></input></td>
                </form>
                </tr>
                <%}%>
                <tr>
                    <td><p style="font-weight:bold">Total Cost</p></td>
                    <td colspan="3"><p>$<%=savedOrder.getTotalCost()%>0</p></td>
                </tr> 
            </table>
        </div>
        <div class="orderBar">
            <%
                if (userType.equals("user")) {
                    // get user object and check if name is empty or not
                    User user = (User) session.getAttribute("user");
                    boolean emptyUser = user.getFirstName().length() == 0;
                    String userDetailButton = emptyUser ? "Add User Details" : "View User Details";
            %>
            <a class="orderBarButton" href="addUserDetails.jsp"><%=userDetailButton%></a>
            <%}%>
            <a class="orderBarButton" href="DeleteOrderServlet">Delete Order</a>

            <%
                // set the text depending on shipment/payment info being set or not
                String shipText = shipID == 0 ? "Add Shipment" : "View Shipment";
                String payText = payID == 0 ? "Add Payment" : "View Payment";
            %>
            <a class="orderBarButton" href="SavedShipmentDetailServlet"><%=shipText%></a>
            <a class="orderBarButton" href="SavedPaymentServlet"><%=payText%></a>
            <a class="orderBarButton" href="SubmitOrderServlet">Submit</a>
        </div>
        <%
            }
        %>
    </body>
</html>
