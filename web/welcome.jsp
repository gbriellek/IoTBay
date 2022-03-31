<%-- 
    Document   : welcome
    Created on : 23 Mar. 2022, 3:34:17 pm
    Author     : Sarah
--%>

<%@page import="uts.isd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--insert css ref-->
        <title>Welcome Page</title>
    </head>
    <body>
       <%
            String requestType = request.getParameter("requestType");
            String tos = request.getParameter("tos");
            if (requestType.equals("register") && tos != null) {
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String email = request.getParameter("email");
                String password = request.getParameter("password");  
                String phone = request.getParameter("phone");  
                Customer customer = new Customer(email, fname, lname, phone, password);
                session.setAttribute("customer", customer);
        %>
        <h1>Hello <%=fname%> <%=lname%>!</h1>
        <a href="main.jsp">main</a>
        <a href="logout.jsp">logout</a>
        <% } else if (requestType.equals("register") && tos == null) { %> <!--if they registered and tos is null-->
        <p>Sorry, you must agree to the Terms of Service.</p>
        <a href="register.jsp">Register</a>
        <%
            }
            else { // if requestType != register (ignore tos)
                //need to distinguish staff and customer login
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Customer customer = new Customer(email, null, null, null, password);
                session.setAttribute("customer", customer);
        %>
        <h1>Welcome back <%=email%>!</h1>
        <a href="main.jsp">main</a>
        <a href="logout.jsp">logout</a>
        <% }%>
    </body>
</html>
