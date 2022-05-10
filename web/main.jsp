<%-- 
    Document   : main.jsp
    Created on : 23 Mar. 2022, 3:34:17 pm
    Author     : Raunak K
--%>

<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Main Page</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <%
            // get feedback message from request
            String updateMsg = (String) request.getAttribute("updatedDetails");
            
            // only staff and customer can view this page
            String emailText = "";
            String fnameText = "";
            String lnameText = "";
            String phoneText = "";
            if (userType.equals("customer")) {
                Customer customer = (Customer)session.getAttribute("user");
                emailText = customer.getEmailAddress();
                fnameText = customer.getFirstName();
                lnameText = customer.getLastName();
                phoneText = customer.getPhoneNumber();
            }
            else{
                Staff staff = (Staff)session.getAttribute("user");
                emailText = staff.getEmailAddress();
                fnameText = staff.getFirstName();
                lnameText = staff.getLastName();
                phoneText = staff.getPhoneNumber();
            }
        %>
        
        <h1>My Account</h1>
        <p><%=updateMsg==null?"":updateMsg%></p>
        <table style="border-collapse: collapse">
            <tr>
                <td><p style="font-weight:bold">Email</p></td>  
                <td><p><%=emailText%></p></td>
            </tr>
            <%
                if (userType.equals("staff")) {
                    Staff staff = (Staff)session.getAttribute("user");
            %>
            <tr >
                <td><p style="font-weight:bold">Staff Number</p></td>
                <td><p><%=staff.getStaffNumber()%></p></td>
            </tr>
            <%
                }
            %>
            <form action="UpdateUserDetailsServlet" method="POST">
            <tr>
                <td><p style="font-weight:bold">First Name</p></td>
                <td><input class="extendfield" type="text" placeholder="Enter your first name" name="fname" value="<%=fnameText%>"></input></td>
            </tr>
            <tr>
                <td><p style="font-weight:bold">Last Name</p></td>
                <td><input class="extendfield" type="text" placeholder="Enter your last name" name="lname" value="<%=lnameText%>"></input></td>
            </tr>
            <tr>
                <td><p style="font-weight:bold">Phone Number</p></td>
                <td><input class="extendfield" type="text" placeholder="Enter your phone number" name="phone" value="<%=phoneText%>"></input></td>
            </tr>
            <tr>
                <td><p style="font-weight:bold">Password</p></td>
                <td><input class="extendfield" type="password" placeholder="Enter a new password" name="password"></input></td>
            </tr>
            <tr>
                <td colspan="2"><input style="cursor:pointer" class="extendfield" id = "submit" type="submit" value="Update Details"></td>
            </tr>
            </form>
            <tr>
                <td colspan="2"><a id="cancel" class="extendfield" href="CancelAccountServlet">Cancel Account</a></td>
            </tr>
        </table>    
    </body>
</html>
