<%-- 
    Document   : register
    Created on : Oct 16, 2021, 3:05:35 PM
    Author     : 771684
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="POST">
            <!--input for username-->
            <label>Username:</label>
            <input type="text" name="username">
            
            <!--Submit button to register user-->
            <input type="submit" value="Register name"> 
            <input type="hidden" name="action" value="register">
        </form>

        <!--output box for info/error messages-->
        <h2>${message}</h2>


    </body>
</html>
