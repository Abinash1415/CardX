/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nt2.tp_abinash;

/**
 *
 * @author roy_ab
 */

public class Joueur {
    private String nom, prenom, login, motDePasse;
    private int age;

    public Joueur(String nom, String prenom, int age, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public int getAge() { return age; }
    public String getLogin() { return login; }
    public String getMotDePasse() { return motDePasse; }
}
