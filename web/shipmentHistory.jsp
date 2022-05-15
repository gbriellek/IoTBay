<%-- 
    Document   : shipmentHistory
    Created on : 5 May 2022, 11:23:49 pm
    Author     : Gabrielle K
--%>

<%@page import="uts.isd.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Shipment Detail History</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <h1>Shipment Detail History</h1>
        <%
            String noShipmentsError = (String) request.getAttribute("noShipmentsError");
            if (noShipmentsError != null) {
        %>
        <p style="color:red;"><%=noShipmentsError%></p>
        <%} else{
            // ensures search bar retains the search criteria
            String requestShipmentID = (String) request.getAttribute("shipmentID");
            String requestShipmentDate = (String) request.getAttribute("shipmentDate");
            String fieldShipmentID = requestShipmentID == null ? "" : requestShipmentID;
            String fieldShipmentDate = requestShipmentDate == null ? "" : requestShipmentDate;
        %>
        <form action="FilterShipmentDetailServlet" method="POST">
            <table class="searchTable"border="0px">
                <tr>
                    <td><label for="shipmentID">Shipment ID</label></td>  
                    <td><input class="searchField" type="text" name="shipmentID" placeholder="Enter Shipment Detail ID" value="<%=fieldShipmentID%>"></input></td>
                    <td><label for="shipmentDate">Shipment Date</label></td> 
                    <td><input class="searchField" type="text" name="shipmentDate" placeholder="yyyy-mm-dd" value="<%=fieldShipmentDate%>"></input></td>
                    <td><input type="submit" value="Filter" class="filterButton"></td>
                </tr>
            </table>
        </form>
        <%
            String shipmentDetailError = (String) request.getAttribute("shipmentDetailError");
        %>
        <p><%=shipmentDetailError == null ? "" : shipmentDetailError%></p>
        <%
            // make new lists for display
            ArrayList<ShipmentDetail> shipmentDetails = new ArrayList<ShipmentDetail>();
            ArrayList<Address> addresses = new ArrayList<Address>();
            ArrayList<Order> orders = new ArrayList<Order>();
            // get the filtered orders from request param
            ArrayList<ShipmentDetail> filterShipmentDetail = (ArrayList<ShipmentDetail>) request.getAttribute("filterShipmentDetail");
            //if there are filtered result then store them to show
            if (filterShipmentDetail != null && filterShipmentDetail.size() > 0) {
                shipmentDetails = filterShipmentDetail;
                addresses = (ArrayList<Address>) request.getAttribute("filterAddress");
                orders = (ArrayList<Order>) request.getAttribute("filterOrder");
            } else {
                // else get the past orders + orderlines from session
                shipmentDetails = (ArrayList<ShipmentDetail>) session.getAttribute("shipmentDetails");
                addresses = (ArrayList<Address>) session.getAttribute("addresses");
                orders = (ArrayList<Order>) session.getAttribute("orders");
            }

            // loop through them and display them
            for (int i = 0; i < shipmentDetails.size(); i++) {
                ShipmentDetail s = shipmentDetails.get(i);
                Address a = addresses.get(i);
                Order o = orders.get(i);
        %>
        <div class="order">
            <p class="orderID"><strong>Shipment Detail ID:</strong> <%=s.getShipmentDetailID()%></p>
            <table class="orderTable" style="border-collapse: collapse">
                <tr class="profile">
                    <td><p style="font-weight:bold">Order ID</p></td>
                    <td><p><%=o.getOrderID()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Payment ID</p></td>  
                    <td><p><%=o.getPaymentInformationID()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Address</p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Unit Number</p></td>  
                    <td><p><%=a.getUnitNo()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Street Number</p></td>  
                    <td><p><%=a.getStreetNo()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Street Name</p></td>  
                    <td><p><%=a.getStreetName()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">City</p></td>  
                    <td><p><%=a.getCity()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Postcode</p></td>  
                    <td><p><%=a.getPostcode()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">State</p></td>  
                    <td><p><%=a.getState()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Delivery Date</p></td>  
                    <td><p><%=s.getDeliveryDate()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Delivery Fee</p></td>  
                    <td><p><%=s.getDeliveryFee()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Delivery Method</p></td>  
                    <td><p><%=s.getDeliveryMethod()%></p></td>
                </tr>
                <tr>
                    <td><p style="font-weight:bold">Delivery Instructions</p></td>  
                    <td><p><%=s.getDeliveryInstructions()%></p></td>
                </tr>
            </table>
        </div>            
        <%
            }
            }
        %>
    </body>
</html>
