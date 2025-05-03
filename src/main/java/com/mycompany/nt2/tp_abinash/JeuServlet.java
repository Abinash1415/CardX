/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nt2.tp_abinash;

/**
 *
 * @author roy_ab
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/JeuServlet")
@MultipartConfig
public class JeuServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Part filePart = request.getPart("fichier");
        InputStream fileContent = filePart.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));

        int rows = 0, cols = 0;
        List<List<Integer>> carte = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim(); // nettoyage
            System.out.println("Ligne lue : [" + line + "]");

            String[] parts = line.split(":");

            if (parts[0].equals("TAILLE")) {
                rows = Integer.parseInt(parts[1]);
                cols = Integer.parseInt(parts[2]);
                System.out.println("==> TAILLE CHARGÉE : " + rows + " x " + cols);
            } else if (parts[0].equals("LIGNE")) {
                List<Integer> row = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    row.add(Integer.parseInt(parts[i]));
                }
                carte.add(row);
            }
        }
        
        // test taille carte
        // System.out.println("TAILLE CHARGÉE : " + rows + " x " + cols);

        // Contrôle de la carte lue
        if (carte.size() != rows) {
            throw new ServletException("Erreur : le nombre de lignes LIGNE: (" + carte.size() + ") ne correspond pas à la taille TAILLE (" + rows + ").");
        }

        for (int ligne = 0; ligne < carte.size(); ligne++) {
            if (carte.get(ligne).size() != cols) {
                throw new ServletException("Erreur : la ligne " + (ligne + 1) + " contient " + carte.get(ligne).size() + " colonnes au lieu de " + cols + ".");
            }
        }

        
        // Enregistrement de la carte en session
        HttpSession session = request.getSession();
        session.setAttribute("carte", carte);
        session.setAttribute("rows", rows);
        session.setAttribute("cols", cols);

        // Générer les positions de X et des 4 agents
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
        session.setAttribute("tour", 0); // compteur de tours

        // Rediriger vers la page de jeu
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
