<%-- 
    Document   : carte
    Created on : 11 avr. 2025, 16:53:43
    Author     : roy_ab
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <title>Carte du Jeu</title>
    <link rel="stylesheet" href="style.css">
    <style>
        table {
            margin: auto;
            border-collapse: collapse;
        }
        td {
            width: 40px;
            height: 40px;
            border: 1px solid #999;
            text-align: center;
            font-weight: bold;
            font-size: 20px;
        }
        .mur { background-color: black; }
        .vide { background-color: white; }
        .monsieurx { background-color: red; color: white; }
        .agentA { background-color: blue; color: white; }
        .agentB { background-color: green; color: white; }
        .agentC { background-color: orange; color: white; }
        .agentD { background-color: purple; color: white; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Carte de la ville</h2>

        <%
            List<List<Integer>> carte = (List<List<Integer>>) session.getAttribute("carte");
            HashMap<String, int[]> positions = (HashMap<String, int[]>) session.getAttribute("positions");
            int rows = (Integer) session.getAttribute("rows");
            int cols = (Integer) session.getAttribute("cols");
            Integer tour = (Integer) session.getAttribute("tour");
        %>

        <!-- Tableau de la carte -->
        <table>
            <!-- Ligne d'en-tête -->
            <tr>
                <th></th>
                <% for (int col = 0; col < cols; col++) { %>
                    <th><%= col %></th>
                <% } %>
            </tr>

            <% for (int i = 0; i < rows; i++) { %>
                <tr>
                    <!-- Numéro de ligne -->
                    <th><%= i %></th>

                    <% for (int j = 0; j < cols; j++) {
                        boolean isPersonnage = false;

                        for (Map.Entry<String, int[]> entry : positions.entrySet()) {
                            int[] pos = entry.getValue();
                            if (pos[0] == i && pos[1] == j) {
                                String perso = entry.getKey();
                                isPersonnage = true;
                                if (perso.equals("X")) { %>
                                    <td class="monsieurx">X</td>
                                <% } else if (perso.equals("A")) { %>
                                    <td class="agentA">A</td>
                                <% } else if (perso.equals("B")) { %>
                                    <td class="agentB">B</td>
                                <% } else if (perso.equals("C")) { %>
                                    <td class="agentC">C</td>
                                <% } else if (perso.equals("D")) { %>
                                    <td class="agentD">D</td>
                                <% }
                                break;
                            }
                        }

                        if (!isPersonnage) {
                            if (carte.get(i).get(j) == 1) { %>
                                <td class="mur"></td>
                            <% } else { %>
                                <td class="vide"></td>
                            <% }
                        }
                    } %>
                </tr>
            <% } %>
        </table>

        <br>
        <h3>Tours restants : <%= (20 - tour) %></h3>

        <hr>
        <h3>Déplacer un agent :</h3>

        <div class="agent-grid">
            <% 
            String[] agents = {"A", "B", "C", "D"};
            for (String agent : agents) {
                int[] pos = positions.get(agent);
                int i = pos[0];
                int j = pos[1];
            %>
            
            <form class="agent-form" action="MouvementServlet" method="post">
                <input type="hidden" name="agent" value="<%= agent %>">
                <h4>Agent <%= agent %></h4>

                <label for="haut_<%= agent %>">⬆️ Haut :</label>
                <input type="radio" id="haut_<%= agent %>" name="direction" value="haut"
                    <%= (i - 1 < 0 || carte.get(i - 1).get(j) == 1) ? "disabled" : "" %> required><br>

                <label for="bas_<%= agent %>">⬇️ Bas :</label>
                <input type="radio" id="bas_<%= agent %>" name="direction" value="bas"
                    <%= (i + 1 >= rows || carte.get(i + 1).get(j) == 1) ? "disabled" : "" %>><br>

                <label for="gauche_<%= agent %>">⬅️ Gauche :</label>
                <input type="radio" id="gauche_<%= agent %>" name="direction" value="gauche"
                    <%= (j - 1 < 0 || carte.get(i).get(j - 1) == 1) ? "disabled" : "" %>><br>

                <label for="droite_<%= agent %>">➡️ Droite :</label>
                <input type="radio" id="droite_<%= agent %>" name="direction" value="droite"
                    <%= (j + 1 >= cols || carte.get(i).get(j + 1) == 1) ? "disabled" : "" %>><br>

                <button type="submit">Déplacer Agent <%= agent %></button>
            </form>
            
            <% } %>
            
        </div>


        <a href="RestartGameServlet">🔁 Recommencer la manche</a></br>
        <a href="menu.jsp">🏠 Retour au menu principal</a><br>
        <a href="index.html">🔄 Nouvelle connexion</a>
    </div>
</body>
</html>
