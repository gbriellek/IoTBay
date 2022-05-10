<%-- 
    Document   : products
    Created on : 5 May 2022, 11:22:43 pm
    Author     : Jemma S
--%>

<%@page import="uts.isd.model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webpage.css"> 
        <title>Products</title>
    </head>
    <body>
        <%@include file="./navbar.jsp"%>
        <h1>Products</h1>
        <%
            // ensures search bar retains the search criteria
            String requestName = (String) request.getAttribute("requestName");
            String requestCategory = (String) request.getAttribute("requestCategory");
            String fieldName = requestName == null ? "" : requestName;
            String fieldCategory = requestCategory == null ? "" : requestCategory;
        %>
        <table class="searchTable">
            <form action="FilterProductServlet" method="POST">
                <tr>
                    <td><label for="productName">Product Name</label></td>
                    <td><input class="searchField" value="<%=fieldName%>" placeholder="Enter your product name" type="text" id="productName" name="productName"><br></td>
                    <td><label for="category">Category</label></td>
                    <td><input class="searchField" value="<%=fieldCategory%>" placeholder="Enter your category" type="text" id="category" name="category"><br></td>
                    <td><input type="submit" value="Filter" class="filterButton"></td>
                </tr>
            </form>
        </table>
        <%
            String productError = (String) request.getAttribute("productError");
        %>
        <p><%=productError == null ? "" : productError%></p>
        <%
            ArrayList<Product> list_products = new ArrayList<Product>();
            ArrayList<Product> filterProducts = (ArrayList<Product>) request.getAttribute("filterProducts");
            if (filterProducts != null) {
                list_products = filterProducts;
            } else {
                list_products = (ArrayList<Product>) session.getAttribute("products");
            }
            if (userType != null && userType.equals("admin") || userType != null && userType.equals("staff")) {
        %>
        <table>
            <tr>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Category</th>
                <th>Update Product</th>
            </tr>
            <form action="AddProductServlet" method="POST">
                <tr>
                    <td><input name="productName" type="text" placeholder="Enter product name"></input></td>
                    <td><input name="description" type="text" placeholder="Enter product description"></input></td>
                    <td><input name="price" type="text" placeholder="Enter product price"></input></td>
                    <td><input name="stock" type="text" placeholder="Enter product stock"></input></td>
                    <td><input name="category" type="text" placeholder="Enter product category"></input></td>
                    <td><input value="Add Product" type="submit"></input></td>
                </tr>
            </form>
            <%
                for (Product product : list_products) {
            %>
            <form action="UpdateProductServlet" method="POST">
                <tr>
                <input name="productID" type="hidden" value="<%=product.getProductID()%>"></input>
                <td><input name="productName" type="text" value="<%=product.getName()%>"></input></td>
                <td><input name="description" type="text" value="<%=product.getDescription()%>"></input></td>
                <td><input name="price" type="text" value="<%=product.getPrice()%>"></input></td>
                <td><input name="stock" type="text" value="<%=product.getStock()%>"></input></td>
                <td><input name="category" type="text" value="<%=product.getCategory()%>"></input></td>
                <td><input name="isActive" type="text" value="<%=product.getIsActive()%>"></input></td>
                <td><input value="Update Product" type="submit"></input></td>
                </tr>
            </form>
            <%
                }
            %>
        </table>
        <%
        } else {
        %>
        <table>
            <tr>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Category</th>
                <th>Quantity</th>
                <th>Add to Cart</th>
            </tr> 
            <%
                for (Product product : list_products) {
                    boolean inStock = product.getStock() > 0;
                    String quantity = inStock ? "1" : "Out of Stock";
            %>
            <tr>
                <td><%=product.getName()%></td>
                <td><%=product.getDescription()%></td>
                <td><%=product.getPrice()%></td>
                <td><%=product.getStock()%></td>
                <td><%=product.getCategory()%></td>
            <form action="AddToOrderServlet" method="POST">
                <input name="productID" type="hidden" value="<%=product.getProductID()%>"></input>
                <input name="productName" type="hidden" value="<%=product.getName()%>"></input>
                <input name="productStock" type="hidden" value="<%=product.getStock()%>"></input>
                <input name="productPrice" type="hidden" value="<%=product.getPrice()%>"></input>
                <td><input name="quantity" type="text" value="<%=quantity%>"></input></td>
                    <%if (inStock) {%>
                <td><input style="cursor:pointer" value="Add to Order" type="submit" ></input></td>
                    <%} else {%>
                <td><input style="cursor:pointer" value="Add to Order" type="submit" disabled></input></td>
                    <%}%>
            </form>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }

    %>
</body>
</html>
