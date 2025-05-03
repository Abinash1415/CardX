<%-- 
    Document   : jeu
    Created on : 11 avr. 2025, 16:53:02
    Author     : roy_ab
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Jeu</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="container">
            <h2>Charger une carte</h2>
            <form action="JeuServlet" method="post" enctype="multipart/form-data">
                <input type="file" name="fichier" accept=".csv" required><br>
                Cliquer sur le bouton pour afficher la carte : <button type="submit">Afficher</button>
            </form>
            <a href="menu.jsp">️Retour au menu</a>
        </div>
    </body>
</html>
