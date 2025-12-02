package services;

import connexion.Connexion;
import dao.IDao;
import entities.Proprietaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Mohim: Zid had l'import
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProprietaireDao implements IDao<Proprietaire> {

    @Override
    public boolean create(Proprietaire prop) {
        try {
            String req = "INSERT INTO Proprietaire (nom, contact, adresse) VALUES (?, ?, ?)";
            
            // !! Zidna: Statement.RETURN_GENERATED_KEYS
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, prop.getNom());
            ps.setString(2, prop.getContact());
            ps.setString(3, prop.getAdresse());
            
            int resultat = ps.executeUpdate();
            
            // !! Hna fin kanjibo l'ID l'jdid w kan3tiwh l l'objet prop
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                prop.setId_prop(rs.getInt(1)); // HADA HOWA L'MAF9OUD
            }
            
            return resultat > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProprietaireDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // ... (Lbaqi dyal l'méthodes update, delete, findAll khallihom kima homa) ...
    
    @Override
    public boolean update(Proprietaire prop) {
        try {
            String req = "UPDATE Proprietaire SET nom = ?, contact = ?, adresse = ? WHERE id_prop = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, prop.getNom());
            ps.setString(2, prop.getContact());
            ps.setString(3, prop.getAdresse());
            ps.setInt(4, prop.getId_prop());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProprietaireDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Proprietaire prop) {
        try {
            String req = "DELETE FROM Proprietaire WHERE id_prop = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, prop.getId_prop());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProprietaireDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Proprietaire findById(int id) {
        try {
            String req = "SELECT * FROM Proprietaire WHERE id_prop = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Proprietaire(
                    rs.getInt("id_prop"), 
                    rs.getString("nom"), 
                    rs.getString("contact"), 
                    rs.getString("adresse")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProprietaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Proprietaire> findAll() {
        List<Proprietaire> proprietaires = new ArrayList<>();
        try {
            String req = "SELECT * FROM Proprietaire";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proprietaires.add(new Proprietaire(
                    rs.getInt("id_prop"), 
                    rs.getString("nom"), 
                    rs.getString("contact"), 
                    rs.getString("adresse")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProprietaireDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proprietaires;
    }
}