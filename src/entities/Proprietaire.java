/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hp
 */
// package com.agence.model; // (Dir l'package dyalk)

public class Proprietaire {
    private int id_prop;
    private String nom;
    private String contact;
    private String adresse;

    // Constructeur vide (Mohim)
    public Proprietaire() {
    }

    // Constructeur pour l'insertion (bla ID)
    public Proprietaire(String nom, String contact, String adresse) {
        this.nom = nom;
        this.contact = contact;
        this.adresse = adresse;
    }
    
    // Constructeur kaml (pour la lecture)
    public Proprietaire(int id_prop, String nom, String contact, String adresse) {
        this.id_prop = id_prop;
        this.nom = nom;
        this.contact = contact;
        this.adresse = adresse;
    }

    // --- Getters & Setters ---
    public int getId_prop() {
        return id_prop;
    }
    public void setId_prop(int id_prop) {
        this.id_prop = id_prop;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        // Mzyana l test f console
        return nom; 
    }
}
