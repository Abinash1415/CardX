<%-- 
    Document   : creerCompte
    Created on : 3 mai 2025, 15:47:48
    Author     : obi
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Créer un compte</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="login-container">
    <h1>Créer un compte</h1>
    <form action="CreerCompteServlet" method="post">
        <input type="text" name="nom" placeholder="Nom" required><br>
        <input type="text" name="prenom" placeholder="Prénom" required><br>
        <input type="text" name="login" placeholder="Login" required><br>
        <input type="password" name="password" placeholder="Mot de passe" required><br>
        <input type="text" name="age" placeholder="Âge" required min="1"><br>
        <button type="submit">Créer le compte</button>
    </form>
    <br>
    <a href="index.html">⬅ Retour à la connexion</a>
</div>
</body>
</html>
