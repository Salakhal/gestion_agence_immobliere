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

import java.sql.Date; 
public class Location {
    private int id_location;
    private Date dateDebut;
    private Date dateFin;
    private double loyer;
    
    // L'objet Bien kaml
    private Bien bien;

    public Location() {
    }

    // Constructeur bla ID (l insertion)
    public Location(Date dateDebut, Date dateFin, double loyer, Bien bien) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.loyer = loyer;
        this.bien = bien;
    }
    
    // Constructeur kaml
    public Location(int id_location, Date dateDebut, Date dateFin, double loyer, Bien bien) {
        this.id_location = id_location;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.loyer = loyer;
        this.bien = bien;
    }

    // --- Getters & Setters ---
    public int getId_location() {
        return id_location;
    }
    public void setId_location(int id_location) {
        this.id_location = id_location;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public double getLoyer() {
        return loyer;
    }
    public void setLoyer(double loyer) {
        this.loyer = loyer;
    }
    public Bien getBien() {
        return bien;
    }
    public void setBien(Bien bien) {
        this.bien = bien;
    }
}
