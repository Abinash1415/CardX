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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try (Connection conn = DatabaseUtils.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comptes WHERE login = ? AND motdepasse = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                int age = rs.getInt("age");

                HttpSession session = request.getSession();
                session.setAttribute("nom", prenom + " " + nom);

                if (age >= 18) {
                    session.setAttribute("message", "Vous êtes autorisé à jouer.");
                } else {
                    session.setAttribute("message", "Désolé, vous n’êtes pas autorisé à jouer.");
                }

                response.sendRedirect("menu.jsp");
            } else {
                response.sendRedirect("index.html");
            }

        } catch (SQLException e) {
            throw new ServletException("Erreur DB : " + e.getMessage(), e);
        }
    }
}
