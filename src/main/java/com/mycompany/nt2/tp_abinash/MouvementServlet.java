/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.nt2.tp_abinash;

/**
 *
 * @author obi
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/MouvementServlet")
public class MouvementServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        HashMap<String, int[]> positions = (HashMap<String, int[]>) session.getAttribute("positions");
        List<List<Integer>> carte = (List<List<Integer>>) session.getAttribute("carte");
        int rows = (Integer) session.getAttribute("rows");
        int cols = (Integer) session.getAttribute("cols");

        String agent = request.getParameter("agent");
        String direction = request.getParameter("direction");

        int[] pos = positions.get(agent);
        int newRow = pos[0];
        int newCol = pos[1];

        // Calculer la nouvelle position selon la direction choisie
        switch (direction) {
            case "haut":
                newRow -= 1;
                break;
            case "bas":
                newRow += 1;
                break;
            case "gauche":
                newCol -= 1;
                break;
            case "droite":
                newCol += 1;
                break;
        }

        // Vérifier si le déplacement est possible (pas sortir de la carte ni mur)
        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && carte.get(newRow).get(newCol) == 0) {
            pos[0] = newRow;
            pos[1] = newCol;
        }
        positions.put(agent, pos); // Mettre à jour la position de l'agent

        // Déplacer Monsieur X aléatoirement
        int[] posX = positions.get("X");
        int moveX = posX[0];
        int moveY = posX[1];

        String[] directions = {"haut", "bas", "gauche", "droite"};
        Random random = new Random();
        boolean moved = false;

        for (int tries = 0; tries < 4 && !moved; tries++) {
            String dir = directions[random.nextInt(directions.length)];
            int tempRow = moveX;
            int tempCol = moveY;

            switch (dir) {
                case "haut":
                    tempRow -= 1;
                    break;
                case "bas":
                    tempRow += 1;
                    break;
                case "gauche":
                    tempCol -= 1;
                    break;
                case "droite":
                    tempCol += 1;
                    break;
            }

            boolean occupied = false;
            // Vérifie si un agent est déjà sur la case
            for (String agentKey : new String[]{"A", "B", "C", "D"}) {
                int[] agentPos = positions.get(agentKey);
                if (agentPos[0] == tempRow && agentPos[1] == tempCol) {
                    occupied = true;
                    break;
                }
            }

            if (tempRow >= 0 && tempRow < rows && tempCol >= 0 && tempCol < cols
                    && carte.get(tempRow).get(tempCol) == 0 && !occupied) {
                posX[0] = tempRow;
                posX[1] = tempCol;
                moved = true;
            }
        }
        positions.put("X", posX); // Mise à jour position de X

        // Vérifier victoire (capture) ou continuer
        boolean capture = true;
        int xRow = posX[0];
        int xCol = posX[1];

        for (String dir : directions) {
            int testRow = xRow;
            int testCol = xCol;
            switch (dir) {
                case "haut":
                    testRow -= 1;
                    break;
                case "bas":
                    testRow += 1;
                    break;
                case "gauche":
                    testCol -= 1;
                    break;
                case "droite":
                    testCol += 1;
                    break;
            }

            if (testRow >= 0 && testRow < rows && testCol >= 0 && testCol < cols) {
                if (carte.get(testRow).get(testCol) == 0) {
                    boolean occupied = false;
                    for (String agentKey : new String[]{"A", "B", "C", "D"}) {
                        int[] agentPos = positions.get(agentKey);
                        if (agentPos[0] == testRow && agentPos[1] == testCol) {
                            occupied = true;
                            break;
                        }
                    }
                    if (!occupied) {
                        capture = false;
                        break;
                    }
                }
            }
        }

        // Incrémenter compteur de tours
        Integer tour = (Integer) session.getAttribute("tour");
        tour++;
        session.setAttribute("tour", tour);

        if (capture) {
            // Victoire
            session.setAttribute("messageFin", "Félicitations, vous avez capturé Monsieur X en " + tour + " tours !");
            response.sendRedirect("result.jsp");
        } else if (tour >= 20) {
            // Défaite
            session.setAttribute("messageFin", "Vous avez perdu. Monsieur X s'est échappé...");
            response.sendRedirect("result.jsp");
        } else {
            // Continuer la partie
            response.sendRedirect("carte.jsp");
        }
    }
}
