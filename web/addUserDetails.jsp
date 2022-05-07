<%-- 
    Document   : addUserDetails
    Created on : 7 May 2022, 10:32:23 pm
    Author     : Sarah
--%>

<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Add User Details</title>
    </head>
     <%
       String addUserDetailsError = (String) request.getAttribute("addUserDetailsError");
       String errorMessage = addUserDetailsError == null ? "": addUserDetailsError;
       User user = (User) session.getAttribute("user");
       boolean emptyUser = user.getFirstName().length() == 0;
       String title = emptyUser ? "Please Enter Your Details" : "Your Details";
       String submitText = emptyUser ? "Add Details To Order": "Update Details" ; 
    %>
    <body>
        <h1><%=title%></h1>
            <form action="AddUserDetailsServlet" method="POST">
                <table border="0px">
                    <tr>
                        <td><label for="fname">First Name</label></td>  
                        <td><label for="lname">Last Name</label></td> 
                        
                    </tr>
                    <tr>
                        <td><input class="extendfield" type="text" placeholder="Enter your first name" name="fname" value="<%=user.getFirstName()%>"></input></td>
                        <td><input class="extendfield" type="text" placeholder="Enter your last name" name="lname" value="<%=user.getLastName()%>"></input></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email</label></td>  
                    </tr>
                    <tr>
                        <td colspan = "2"><input class="extendfield" type="text" placeholder="Enter your email" name="email" value="<%=emptyUser? "" : user.getEmailAddress()%>"></input></td>
                    </tr>
                    <tr>
                        <td><label for="phone">Phone Number</label></td>  
                        
                    </tr>
                    <tr>
                        <td colspan = "2"><input class="extendfield" type="text" placeholder="Enter your phone number" name="phone" value="<%=user.getPhoneNumber()%>"></input></td>
                    </tr>
                    <tr>
                        <td>         </td>
                    </tr>
                    <tr>
                        <td><a id="cancel" class="extendfield" href="savedOrder.jsp">Cancel</a></td>
                        <td><input style="cursor:pointer" class="extendfield" id = "submit" type="submit" value="<%=submitText%>"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><p><%=errorMessage%></td>
                    </tr>
            </table>
        </form>
    </body>
</html>

