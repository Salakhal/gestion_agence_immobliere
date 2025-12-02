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

 import java.util.Date; 

public class Bien {
    private int id_bien;
    private String type;
    private String ville;
    private double surface;
    private double prixMensuel;
    
    // Hada howa l'Mapping (l'objet kaml machi ghi l'ID)
    private Proprietaire proprietaire; 

    public Bien() {
    }

    // Constructeur kaml
    public Bien(int id_bien, String type, String ville, double surface, double prixMensuel, Proprietaire proprietaire) {
        this.id_bien = id_bien;
        this.type = type;
        this.ville = ville;
        this.surface = surface;
        this.prixMensuel = prixMensuel;
        this.proprietaire = proprietaire;
    }
    
    // Constructeur bla ID (l insertion)
    public Bien(String type, String ville, double surface, double prixMensuel, Proprietaire proprietaire) {
        this.type = type;
        this.ville = ville;
        this.surface = surface;
        this.prixMensuel = prixMensuel;
        this.proprietaire = proprietaire;
    }

    // --- Getters & Setters ---
    public int getId_bien() {
        return id_bien;
    }
    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface) {
        this.surface = surface;
    }
    public double getPrixMensuel() {
        return prixMensuel;
    }
    public void setPrixMensuel(double prixMensuel) {
        this.prixMensuel = prixMensuel;
    }
    public Proprietaire getProprietaire() {
        return proprietaire;
    }
    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }
    
    @Override
    public String toString() {
        return type + " à " + ville;
    }
}
