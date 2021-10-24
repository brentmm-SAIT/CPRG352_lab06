<%-- 
    Document   : shoppingList
    Created on : Oct 16, 2021, 3:05:48 PM
    Author     : 771684
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <!--Welcome message plus a logout hyperlink-->
        <p>Hello, ${username} <a name="action" value="logout" href="ShoppingList?logout" >Log out</a></p>
        <h2>List</h2>

        <!--form to add an item to list-->
        <form method="POST">
            <label>Add item:</label>
            <!--input for item-->
            <input type="text" name="item">
            
              <!--Submit button to add entered value-->
            <input type="submit" value="Add"> 
            <input type="hidden" name="action" value="add">

        </form>

        <!--form to delete item from displayed list-->
        <form method="POST">
            <!--JSTL to display the array of items-->
            <c:forEach var="groceryItem" items="${groceryList}" >
                <!--Displays a radio button of an item-->
                <input type="radio" name="selectedItem" value="${groceryItem}">${groceryItem}
                <br>
            </c:forEach>
            <br>

            <!--Submit button to delete selected value-->
            <input type="submit"value="Delete">  
            <input type="hidden" name="action" value="delete">
        </form>

    </body>
</html>
