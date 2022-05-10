<%-- 
    Document   : paymentHistory
    Created on : 5 May 2022, 11:23:49 pm
    Author     : Mia Z
--%>

<%@page import="uts.isd.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Payment Information History</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <h1>Payment Information History</h1>
        <%
            // ensures search bar retains the search criteria
            String requestPayID = (String) request.getAttribute("payID");
            String requestPayDate = (String) request.getAttribute("payDate");
            String fieldPayID = requestPayID == null ? "" : requestPayID;
            String fieldPayDate = requestPayDate == null ? "" : requestPayDate;
        %>
        <form action="FilterPaymentInformationServlet" method="POST">
            <table class="searchTable"border="0px">
                <tr>
                    <td><label for="payID">Payment ID</label></td>  
                    <td><input class="searchField" type="text" name="paymentID" placeholder="Enter an Payment Information ID" value="<%=fieldPayID%>"></input></td>
                    <td><label for="PaymentDate">Payment Date</label></td> 
                    <td><input class="searchField" type="text" name="paymentDate" placeholder="yyyy-mm-dd" value="<%=fieldPayDate%>"></input></td>
                    <td><input type="submit" value="Filter" class="filterButton"></td>
                </tr>
            </table>
        </form>
        <%
            String paymentError = (String) request.getAttribute("paymentError");
        %>
        <p><%=paymentError == null ? "" : paymentError%></p>
        <%
            // make new lists for display
            ArrayList<PaymentInformation> paymentInfo = new ArrayList<PaymentInformation>();
            ArrayList<Order> orders = new ArrayList<Order>();
            ArrayList<ShipmentDetail> ships = new ArrayList<ShipmentDetail>();
            // get the filtered payments from request param
            ArrayList<PaymentInformation> filterPayments = (ArrayList<PaymentInformation>) request.getAttribute("filterPayments");
            //if there are filtered result then store them to show
            if (filterPayments != null && filterPayments.size() > 0) {
                paymentInfo = filterPayments;
                orders = (ArrayList<Order>) request.getAttribute("filterOrders");
                ships = (ArrayList<ShipmentDetail>) request.getAttribute("filterShips");
            } else {
                // else get the past payments from session
                paymentInfo = (ArrayList<PaymentInformation>) session.getAttribute("paymentInfo");
                orders = (ArrayList<Order>) session.getAttribute("orders");
                ships = (ArrayList<ShipmentDetail>) session.getAttribute("ships");
            }

            // loop through them and display them
            for (int i = 0; i < paymentInfo.size(); i++) {
                PaymentInformation p = paymentInfo.get(i);
                Order o = orders.get(i);
                ShipmentDetail s = ships.get(i);
                double totalCost = s.getDeliveryFee() + o.getTotalCost();
        %>
        <div class="order">
            <p class="orderID"><strong>Payment Information ID:</strong> <%=p.getPaymentInformationID()%></p>
            <table class="orderTable" style="border-collapse: collapse">
                <tr class="profile">
                    <td><p style="font-weight:bold">Order ID</p></td>
                    <td><p><%=o.getOrderID()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Shipment Detail ID</p></td>  
                    <td><p><%=s.getShipmentDetailID()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Payment Date</p></td>  
                    <td><p><%=o.getOrderDate()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Card Number</p></td>
                    <td><p><%=p.getCardNumber()%></p></td>
                </tr>
                <tr class="profile">
                    <td><p style="font-weight:bold">Card Type</p></td>
                    <td><p><%=p.getCardType()%></p></td>
                </tr> 
                <tr class="profile">
                    <td><p style="font-weight:bold">Expiry Date</p></td>
                    <td><p><%=p.getExpiryDate()%></p></td>
                </tr> 
                <tr>
                    <td><p style="font-weight:bold">Amount Paid</p></td>
                    <td><p>$<%=totalCost%>0</p></td>
                </tr> 
            </table>
        </div>            
        <%
            }
        %>
    </body>
</html>
