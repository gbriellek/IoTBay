<%-- 
    Document   : savedShipment
    Created on : 8 May 2022, 4:22:31 pm
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
        <title>Saved Shipment Details</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <h1>Saved Shipment Details</h1>

        <%
            //get the saved shipment detail from the session
            ShipmentDetail savedShipmentDetail = (ShipmentDetail) session.getAttribute("savedShipmentDetail");
            Address savedAddress = (Address) session.getAttribute("savedAddress");
            Order savedOrder = (Order) session.getAttribute("savedOrder");
            // then display the shipment detail
            int shipID = savedShipmentDetail.getShipmentDetailID();
            int payID = savedOrder.getPaymentInformationID();

            String unitNo = savedAddress.getUnitNo();
            // fee and date if not new shipment detail
            String feeText = shipID == 0 ? "Please Add Shipment Details" : "$" + savedShipmentDetail.getDeliveryFee() + 0;
            String dateText = shipID == 0 ? "Please Add Shipment Details" : savedShipmentDetail.getDeliveryDate() + "";
            String postcodeText = shipID == 0 ? "" : savedAddress.getPostcode() + "";

            //  get errors or feedback messages
            String savedShipmentError = (String) request.getAttribute("savedShipmentError");
            String updatedSavedShipment = (String) request.getAttribute("updatedSavedShipment");
        %>
        <p><%=savedShipmentError == null ? "" : savedShipmentError%></p>
        <p><%=updatedSavedShipment == null ? "" : updatedSavedShipment%></p>
        <form action="UpdateShipmentDetailServlet" method="POST">
            <div class="order" id="savedOrder">
                <p class="orderID"><strong>Shipment Detail ID:</strong> <%=shipID == 0 ? "N/A" : shipID%></p>

                <table class="orderTable" style="border-collapse: collapse">
                    <input name="shipmentID" type="hidden" value="<%=shipID%>"></input>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Address</p></td>
                    </tr>
                    <tr class="profile">
                        <td style="width:30%"><p style="font-weight:bold">Unit Number</p></td>
                        <td><input name="unitNo" type="text" value="<%=unitNo == null ? "" : unitNo%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Street Number</p></td>
                        <td><input name="streetNo" type="text" value="<%=savedAddress.getStreetNo()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Street Name</p></td>
                        <td><input name="streetName" type="text" value="<%=savedAddress.getStreetName()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">City</p></td>
                        <td><input name="city" type="text" value="<%=savedAddress.getCity()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Postcode</p></td>
                        <td><input name="postcode" type="text" value="<%=postcodeText%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">State</p></td>
                        <td><input name="state" type="text" value="<%=savedAddress.getState()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Delivery Date</p></td>
                        <td><p><%=dateText%></p></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Delivery Fee</p></td>
                        <td><p><%=feeText%></p></td>
                    </tr>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Delivery Type</p></td>
                        <td><input name="deliveryMethod" type="text" placeholder="express/standard" value="<%=savedShipmentDetail.getDeliveryMethod()%>"></input></td>
                    </tr>
                    <tr>
                        <td><p style="font-weight:bold">Delivery Instructions</p></td>
                        <td><input name="deliveryInstructions" type="text" value="<%=savedShipmentDetail.getDeliveryInstructions() == null ? "" : savedShipmentDetail.getDeliveryInstructions()%>"></input></td>
                    </tr>

                </table>
            </div>   
            <div class="orderBar">
                <input style="cursor:pointer" id="updateOrderBarButton" class="orderBarButton" value="Update Shipment Details" type="submit" ></input>
                <%
                    if (shipID != 0) {
                %>
                <a class="orderBarButton" href="DeleteShipmentDetailServlet">Delete Shipment Details</a>

                <%
                    }
                    // set the text depending on shipment/payment info being set or not
                    String payText = payID == 0 ? "Add Payment" : "View Payment";
                %>                
                <a class="orderBarButton" href="SavedPaymentServlet"><%=payText%></a>
                <a class="orderBarButton" href="SavedOrderServlet">View Order</a>
            </div>
        </form>
    </body>
</html>

