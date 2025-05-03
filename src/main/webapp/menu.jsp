<%-- 
    Document   : menu
    Created on : 11 avr. 2025, 14:39:40
    Author     : roy_ab
--%>

<%@ page session="true" %>

<%
    String nom = (String) session.getAttribute("nom");
    String message = (String) session.getAttribute("message");
%>

<html>
    <head>
        <title>Menu</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <h2>Menu principal</h2>
            <p>Bonjour <strong><%= nom != null ? nom : "?" %></strong></p>
            <p><%= message != null ? message : "" %></p>
            <a href="jeu.jsp">Commencer une partie</a>
        </div>
    </body>
</html>
