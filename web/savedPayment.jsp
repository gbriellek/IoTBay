<%-- 
    Document   : savedPayment
    Created on : 8 May 2022, 4:23:49 pm
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
        <title>Saved Payment Information</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <h1>Saved Payment Information</h1>
        <%
            //get the saved payment, order, shipment details from the session
            PaymentInformation savedPayment = (PaymentInformation) session.getAttribute("savedPayment");
            Order savedOrder = (Order) session.getAttribute("savedOrder");
            ShipmentDetail savedShip = (ShipmentDetail) session.getAttribute("savedShipmentDetail");
            String amountToPay = "Please add shipment details";
            if (savedShip != null) {
                amountToPay = "$" + (savedOrder.getTotalCost() + savedShip.getDeliveryFee()) + "0";
            }
            // then display the payment info
            int shipID = savedOrder.getShipmentDetailID();
            int payID = savedOrder.getPaymentInformationID();
            //  get any savedOrderErrors from the request
            String savedPaymentError = (String) request.getAttribute("savedPaymentError");
            String updatedSavedPayment = (String) request.getAttribute("updatedSavedPayment");
        %>
        <p style="color:red;"><%=updatedSavedPayment == null ? "" : updatedSavedPayment%></p>
        <p style="color:red;"><%=savedPaymentError == null ? "" : savedPaymentError%></p>
        <form action="UpdateSavedPaymentServlet" method="POST">
            <div class="order" id="savedOrder">
                <p class="orderID"><strong>Payment Information ID:</strong> <%=payID == 0 ? "N/A" : payID%></p>
                <table class="orderTable" style="border-collapse: collapse">
                    <tr class="profile">
                        <td><p style="font-weight:bold">Card Number</p></td>
                        <td><input name="cardNo" type="text" value="<%=savedPayment.getCardNumber()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Card Type</p></td>
                        <td><input name="cardType" type="text" value="<%=savedPayment.getCardType()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Expiry Date</p></td>
                        <td><input name="expiryDate" type="text" placeholder="yyyy-mm" value="<%=savedPayment.getExpiryDate()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">CVV</p></td>
                        <td><input name="cvv" type="text" value="<%=savedPayment.getCVV() == 0 ? "" : savedPayment.getCVV()%>"></input></td>
                    </tr>
                    <tr>
                        <td><p style="font-weight:bold">Amount To Pay</p></td>
                        <td colspan="3"><p><%=amountToPay%></p></td>
                    </tr>
                </table>
            </div>   
            <!--to fix up-->
            <div class="orderBar">

                <input style="cursor:pointer" id="updateOrderBarButton" class="orderBarButton" value="Update Payment Info" type="submit">
                <%
                    if (payID != 0) {
                %>
                <a class="orderBarButton" href="DeletePaymentServlet">Delete Payment Info</a>
                <%
                    }
                    // set the text depending on shipment being set or not
                    String shipText = shipID == 0 ? "Add Shipment" : "View Shipment";
                %>
                <a class="orderBarButton" href="SavedShipmentDetailServlet"><%=shipText%></a>
                <a class="orderBarButton" href="SavedOrderServlet">View Order</a>
            </div>
        </form>
    </body>
</html>
