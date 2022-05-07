<%-- 
    Document   : products
    Created on : 5 May 2022, 11:22:43 pm
    Author     : raunak
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
        <h1>Products</h1>
        <table>
            <form action="FilterProductServlet" method="POST">
                <tr>
                    <td colspan = "2"><label for="productName">Product Name</label></td>
                    <td colspan = "2"><input placeholder="Enter your product name" type="text" id="productName" name="productName"><br></td>
                    <td colspan = "2"><label for="category">Category</label></td>
                    <td colspan = "2"><input placeholder="Enter your category" type="text" id="category" name="category"><br></td>
                    <td><input type="submit" value="Filter"></td>
                </tr>
            </form>
        </table>
        <%
            String productError = (String) request.getAttribute("productError");
        %>
        <p><%=productError == null ? "" : productError%></p>
        <%
            try {
                ArrayList<Product> list_products = new ArrayList<Product>();
                ArrayList<Product> filterCategory = (ArrayList<Product>) request.getAttribute("filterCategory");
                Product filterName = (Product) request.getAttribute("filterName");
                if (filterCategory != null) {
                    list_products = filterCategory;
                } else if (filterName != null) {
                    list_products.add(filterName);
                } else {
                    list_products = (ArrayList<Product>) request.getAttribute("products");
                }
                String userType = (String) session.getAttribute("userType");
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
                <th>Add to Cart</th>
            </tr> 
            <%
                for (Product product : list_products) {
            %>
            <tr>
                <td><%=product.getName()%></td>
                <td><%=product.getDescription()%></td>
                <td><%=product.getPrice()%></td>
                <td><%=product.getStock()%></td>
                <td><%=product.getCategory()%></td>
                <td><p>Add to Cart</p></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
                }
            } catch (Exception e) {

            }
        %>
    </body>
</html>
