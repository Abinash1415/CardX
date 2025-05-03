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

@WebServlet("/RestartGameServlet")
public class RestartGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<List<Integer>> carte = (List<List<Integer>>) session.getAttribute("carte");
        int rows = (Integer) session.getAttribute("rows");
        int cols = (Integer) session.getAttribute("cols");

        // Regénérer les positions de X et des 4 agents
        HashMap<String, int[]> positions = new HashMap<>();
        Random random = new Random();
        List<int[]> usedPositions = new ArrayList<>();
        String[] personnages = {"X", "A", "B", "C", "D"};

        for (String perso : personnages) {
            int r, c;
            do {
                r = random.nextInt(rows);
                c = random.nextInt(cols);
            } while (carte.get(r).get(c) == 1 || isOccupied(r, c, usedPositions));
            positions.put(perso, new int[]{r, c});
            usedPositions.add(new int[]{r, c});
        }

        session.setAttribute("positions", positions);
        session.setAttribute("tour", 0); // Réinitialiser compteur
        session.removeAttribute("messageFin"); // Supprimer message de fin éventuel

        response.sendRedirect("carte.jsp");
    }

    private boolean isOccupied(int r, int c, List<int[]> usedPositions) {
        for (int[] pos : usedPositions) {
            if (pos[0] == r && pos[1] == c) {
                return true;
            }
        }
        return false;
    }
}
