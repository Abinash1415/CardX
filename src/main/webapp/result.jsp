<%-- 
    Document   : result
    Created on : 28 avr. 2025, 18:11:52
    Author     : obi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html>
<head>
    <title>Résultat du jeu</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Résultat</h2>

        <%
            String messageFin = (String) session.getAttribute("messageFin");
            if (messageFin != null) {
        %>
            <p><strong><%= messageFin %></strong></p>
        <%
            } else {
        %>
            <p>Erreur : aucun résultat disponible.</p>
        <%
            }
        %>

        <br>
        <a href="RestartGameServlet">🔁 Recommencer la manche</a></br>
        <a href="menu.jsp">🏠 Retour au menu principal</a><br>
        <a href="index.html">🔄 Nouvelle connexion</a>
    </div>
</body>
</html>
