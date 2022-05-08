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
        <!--insert nav bar-->
        <h1>Saved Shipment Details</h1>
        <%
            String shipmentDetailSubmitted = (String) request.getAttribute("shipmentDetailSubmitted");
            String noSavedShipment = (String) request.getAttribute("noSavedShipment");
            if (shipmentDetailSubmitted != null) {
        %>
        <p><%=shipmentDetailSubmitted%></p>
        <a href="ShipmentDetailServlet">View Shipment Detail History</a>
        <%
        } else if (noSavedShipment != null) {
        %>

        <%
        } else {
            //get the saved shipment detail from the session
            ShipmentDetail savedShipmentDetail = (ShipmentDetail) session.getAttribute("savedShipmentDetail");
            Address savedAddress = (Address) session.getAttribute("savedAddress");
            Order savedOrder = (Order) session.getAttribute("savedOrder");
            // then display the shipment detail
            int shipID = savedShipmentDetail.getShipmentDetailID();
            int payID = savedOrder.getPaymentInformationID();
            //  get any savedOrderErrors from the request
            String savedShipmentError = (String) request.getAttribute("savedOrderError");
        %>
        <p><%=savedShipmentError == null ? "" : savedShipmentError%></p>
        <form>
            <div class="order" id="savedOrder">
                <p class="orderID"><strong>Shipment Detail ID:</strong> <%=shipID%></p>

                <table class="orderTable" style="border-collapse: collapse">
                    <input name="shipmentID" type="hidden" value="<%=shipID%>"></input>
                    <tr class="profile">
                        <td><p style="font-weight:bold">Address</p></td>
                    </tr>
                    <tr class="profile">
                        <td style="width:30%"><p>Unit Number</p></td>
                        <td><input name="unitnumber" type="text" value="<%=savedAddress.getUnitNo()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p>Street Number</p></td>
                        <td><input name="streetnumber" type="text" value="<%=savedAddress.getStreetNo()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p>Street Name</p></td>
                        <td><input name="streetname" type="text" value="<%=savedAddress.getStreetName()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p>City</p></td>
                        <td><input name="city" type="text" value="<%=savedAddress.getCity()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p>Postcode</p></td>
                        <td><input name="postcode" type="text" value="<%=savedAddress.getPostcode()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p>State</p></td>
                        <td><input name="state" type="text" value="<%=savedAddress.getState()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p>Delivery Date</p></td>
                        <td><p><%=savedShipmentDetail.getDeliveryDate()%></p></td>
                    </tr>
                    <tr class="profile">
                        <td><p>Delivery Fee</p></td>
                        <td><p><%=savedShipmentDetail.getDeliveryFee()%></p></td>
                    </tr>
                    <tr class="profile">
                        <td><p>Delivery Type</p></td>
                        <td><input name="deliverytype" type="text" value="<%=savedShipmentDetail.getDeliveryMethod()%>"></input></td>
                    </tr>
                    <tr class="profile">
                        <td><p>Delivery Instructions</p></td>
                        <td><input name="deliveryinstructions" type="text" value="<%=savedShipmentDetail.getDeliveryInstructions()%>"></input></td>
                    </tr>

                </table>
            </div>   
            <%
                // check whether it is customer or user

            %>
            <div class="orderBar">
                <input style="cursor:pointer" class="savedItemButtons" value="Update Details" type="submit" ></input>
                <%                String userType = (String) session.getAttribute("userType");
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
                <a class="orderBarButton"><%=shipText%></a>
                <a class="orderBarButton"><%=payText%></a>

                <a class="orderBarButton" href="SubmitOrderServlet">Submit</a>
            </div>
        </form>
        <%
            }
        %>
    </body>
</html>

