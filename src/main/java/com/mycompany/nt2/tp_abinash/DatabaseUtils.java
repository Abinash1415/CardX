/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nt2.tp_abinash;

/**
 *
 * @author obi
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jeu?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = ""; // Mot de passe vide

        return DriverManager.getConnection(url, user, password);
    }
}
