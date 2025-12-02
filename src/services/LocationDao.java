package services;

import connexion.Connexion;
import dao.IDao;
import entities.Bien;
import entities.Location;
import entities.Proprietaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocationDao implements IDao<Location> {

    @Override
    public boolean create(Location location) {
        String sql = "INSERT INTO Location (dateDebut, dateFin, loyer, id_bien) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setDate(1, new java.sql.Date(location.getDateDebut().getTime()));
            
            if(location.getDateFin() != null) {
                ps.setDate(2, new java.sql.Date(location.getDateFin().getTime()));
            } else {
                ps.setNull(2, Types.DATE);
            }
            
            ps.setDouble(3, location.getLoyer());
            ps.setInt(4, location.getBien().getId_bien());
            
            int result = ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                location.setId_location(generatedKeys.getInt(1));
            }
            
            return result > 0;

        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Location location) {
        String sql = "UPDATE Location SET dateDebut = ?, dateFin = ?, loyer = ?, id_bien = ? WHERE id_location = ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            
            ps.setDate(1, new java.sql.Date(location.getDateDebut().getTime()));
            
            if(location.getDateFin() != null) {
                 ps.setDate(2, new java.sql.Date(location.getDateFin().getTime()));
            } else {
                 ps.setNull(2, Types.DATE);
            }
            
            ps.setDouble(3, location.getLoyer());
            ps.setInt(4, location.getBien().getId_bien());
            ps.setInt(5, location.getId_location());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Location location) {
        String sql = "DELETE FROM Location WHERE id_location = ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1, location.getId_location());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Location findById(int id) {
        // DOUBLE JOIN: Location -> Bien -> Proprietaire
        String sql = "SELECT * FROM Location l " +
                     "JOIN Bien b ON l.id_bien = b.id_bien " +
                     "LEFT JOIN Proprietaire p ON b.id_prop = p.id_prop " +
                     "WHERE l.id_location = ?";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Proprietaire prop = new Proprietaire(
                    rs.getInt("p.id_prop"), rs.getString("p.nom"),
                    rs.getString("p.contact"), rs.getString("p.adresse")
                );

                Bien bien = new Bien(
                    rs.getInt("b.id_bien"), rs.getString("b.type"),
                    rs.getString("b.ville"), rs.getDouble("b.surface"),
                    rs.getDouble("b.prixMensuel"), prop
                );

                return new Location(
                    rs.getInt("l.id_location"), rs.getDate("l.dateDebut"),
                    rs.getDate("l.dateFin"), rs.getDouble("l.loyer"), bien
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = new ArrayList<>();
        // DOUBLE JOIN: Location -> Bien -> Proprietaire
        String sql = "SELECT * FROM Location l " +
                     "JOIN Bien b ON l.id_bien = b.id_bien " +
                     "LEFT JOIN Proprietaire p ON b.id_prop = p.id_prop";
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                 Proprietaire prop = null;
                 if(rs.getInt("p.id_prop") != 0) {
                     prop = new Proprietaire(
                        rs.getInt("p.id_prop"), rs.getString("p.nom"),
                        rs.getString("p.contact"), rs.getString("p.adresse")
                    );
                 }

                Bien bien = new Bien(
                    rs.getInt("b.id_bien"), rs.getString("b.type"),
                    rs.getString("b.ville"), rs.getDouble("b.surface"),
                    rs.getDouble("b.prixMensuel"), prop
                );

                locations.add(new Location(
                    rs.getInt("l.id_location"), rs.getDate("l.dateDebut"),
                    rs.getDate("l.dateFin"), rs.getDouble("l.loyer"), bien
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locations;
    }
}