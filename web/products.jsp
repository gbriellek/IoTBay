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
                    <td><label for="productName">Product Name:</label></td>
                    <td><input class="searchField" value="<%=fieldName%>" size=25 placeholder="Enter your product name" type="text" id="productName" name="productName"><br></td>
                    <td><label for="category">Category:</label></td>
                    <td><input class="searchField" value="<%=fieldCategory%>" size=25 placeholder="Enter your category" type="text" id="category" name="category"><br></td>
                    <td><input type="submit" value="Filter" class="filterButton"></td>
                </tr>
            </form>
        </table>
        <%
            String productError = (String) request.getAttribute("productError");
            String productAdd = (String) request.getAttribute("productAdd");
        %>
        <p style="color:red;"><%=productError == null ? "" : productError%></p>
        <p style="color:blue;"><%=productAdd == null ? "" : productAdd%></p>
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
        <table style="width:80%">
            <tr>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Category</th>
                
            </tr>
            <form action="AddProductServlet" method="POST">
                <tr>
                    <td><input name="productName" type="text" placeholder="Enter product name"></input></td>
                    <td><textarea id="descriptionText"style="width:402px;max-width:402px;min-width: 402px" id="description" name="description" placeholder="Enter description"></textarea></td>
                    <td style="width:10%"><input style="width:98%" name="price" type="text" placeholder="Enter price"></input></td>
                    <td style="width:10%"><input style="width:98%" name="stock" type="text" placeholder="Enter stock"></input></td>
                    <td style="width:30%" ><input style="width:98%" name="category" type="text" placeholder="Enter category"></input></td>
                    <td><input value="Add Product" type="submit"></input></td>
                </tr>
            </form>
            <%
                for (Product product : list_products) {
            %>
            <tr>
                <form action="UpdateProductServlet" method="POST">
                    <input name="productID" type="hidden" value="<%=product.getProductID()%>"></input>
                    <td><input name="productName" type="text" value="<%=product.getName()%>"></input></td>
                    <td><textarea id="descriptionText"style="width:402px;max-width:402px;min-width: 402px" id="description" name="description"><%=product.getDescription()%></textarea></td>
                    <td><input style="width:98%" name="price" type="text" value="<%=product.getPrice()%>"></input></td>
                    <td><input style="width:98%" name="stock" type="text" value="<%=product.getStock()%>"></input></td>
                    <td><input style="width:98%" name="category" type="text" value="<%=product.getCategory()%>"></input></td>
                    <td><input style="width:98%" value="Update Product" type="submit"></input></td>
                </form>
                <form action="DeleteProductServlet" method="POST">
                    <input name="productID" type="hidden" value="<%=product.getProductID()%>"></input>
                    <td><input value="Delete Product" type="submit"></input></td>
                </form>
            </tr>
            <%
                }
            %>
        </table>
        <%
        } else {
        %>
        <table id="productTable">
            <tr>
                <th class="productTh">Product Name</th>
                <th class="productTh">Description</th>
                <th class="productTh">Price</th>
                <th class="productTh">Stock</th>
                <th class="productTh">Category</th>
                <th class="productTh">Quantity</th>
                <th class="productTh">Add to Cart</th>
            </tr> 
            <%
                for (Product product : list_products) {
                    boolean inStock = product.getStock() > 0;
                    String quantity = inStock ? "1" : "Out of Stock";
            %>
            <tr>
                <td class="productTd"><%=product.getName()%></td>
                <td style="width:50%" class="productTd"><%=product.getDescription()%></td>
                <td class="productTd">$<%=product.getPrice()%>0</td>
                <td class="productTd"><%=product.getStock()%></td>
                <td class="productTd"><%=product.getCategory()%></td>
            <form action="AddToOrderServlet" method="POST">
                <input name="productID" type="hidden" value="<%=product.getProductID()%>"></input>
                <input name="productName" type="hidden" value="<%=product.getName()%>"></input>
                <input name="productStock" type="hidden" value="<%=product.getStock()%>"></input>
                <input name="productPrice" type="hidden" value="<%=product.getPrice()%>"></input>
                <td class="productTd"><input style="width:80px" name="quantity" type="text" value="<%=quantity%>" size=3></input></td>
                    <%if (inStock) {%>
                <td class="productTd"><input style="cursor:pointer" value="Add to Order" type="submit" ></input></td>
                    <%} else {%>
                <td class="productTd"<input style="cursor:pointer" value="Add to Order" type="submit" disabled></input></td>
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
