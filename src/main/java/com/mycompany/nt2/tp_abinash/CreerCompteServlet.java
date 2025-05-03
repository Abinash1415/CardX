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
import java.sql.*;

@WebServlet("/CreerCompteServlet")
public class CreerCompteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));

        try (Connection conn = DatabaseUtils.getConnection()) {
            // Vérifie si le login existe déjà
            PreparedStatement check = conn.prepareStatement("SELECT * FROM comptes WHERE login = ?");
            check.setString(1, login);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                request.setAttribute("message", "Ce login existe déjà !");
                request.getRequestDispatcher("creerCompte.jsp").forward(request, response);
                return;
            }

            // Insertion
            PreparedStatement insert = conn.prepareStatement("INSERT INTO comptes(nom, prenom, age, login, motdepasse) VALUES (?, ?, ?, ?, ?)");
            insert.setString(1, nom);
            insert.setString(2, prenom);
            insert.setInt(3, age);
            insert.setString(4, login);
            insert.setString(5, password);
            insert.executeUpdate();

            // Retour à la connexion
            response.sendRedirect("index.html");

        } catch (SQLException e) {
            throw new ServletException("Erreur BDD : " + e.getMessage(), e);
        }
    }
}
