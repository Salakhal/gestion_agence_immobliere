

package services;

import connexion.Connexion;
import dao.IDao;
import entities.Bien;
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

public class BienDao implements IDao<Bien> {

    @Override
    public boolean create(Bien bien) {
        // Zdna Statement.RETURN_GENERATED_KEYS bach nchaddu l'ID
        String sql = "INSERT INTO Bien (type, ville, surface, prixMensuel, id_prop) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, bien.getType());
            ps.setString(2, bien.getVille());
            ps.setDouble(3, bien.getSurface());
            ps.setDouble(4, bien.getPrixMensuel());
            
            if (bien.getProprietaire() != null) {
                ps.setInt(5, bien.getProprietaire().getId_prop());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            
            int result = ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                bien.setId_bien(generatedKeys.getInt(1));
            }
            
            return result > 0;

        } catch (SQLException ex) {
            Logger.getLogger(BienDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Bien> findAll() {
        List<Bien> biens = new ArrayList<>();
        // JOIN bach njibo Bien w Proprietaire dqa wa7da
        String sql = "SELECT * FROM Bien b LEFT JOIN Proprietaire p ON b.id_prop = p.id_prop";
        
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Bien bien = new Bien();
                bien.setId_bien(rs.getInt("b.id_bien"));
                bien.setType(rs.getString("b.type"));
                bien.setVille(rs.getString("b.ville"));
                bien.setSurface(rs.getDouble("b.surface"));
                bien.setPrixMensuel(rs.getDouble("b.prixMensuel"));
                
                int idProp = rs.getInt("b.id_prop");
                if (!rs.wasNull()) {
                    Proprietaire prop = new Proprietaire();
                    prop.setId_prop(idProp);
                    prop.setNom(rs.getString("p.nom"));
                    prop.setContact(rs.getString("p.contact"));
                    prop.setAdresse(rs.getString("p.adresse"));
                    bien.setProprietaire(prop);
                } else {
                    bien.setProprietaire(null);
                }
                
                biens.add(bien);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return biens;
    }

    @Override
    public Bien findById(int id) {
        String sql = "SELECT * FROM Bien b LEFT JOIN Proprietaire p ON b.id_prop = p.id_prop WHERE b.id_bien = ?";
        
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Bien bien = new Bien();
                bien.setId_bien(rs.getInt("b.id_bien"));
                bien.setType(rs.getString("b.type"));
                bien.setVille(rs.getString("b.ville"));
                bien.setSurface(rs.getDouble("b.surface"));
                bien.setPrixMensuel(rs.getDouble("b.prixMensuel"));
                
                int idProp = rs.getInt("b.id_prop");
                if (!rs.wasNull()) {
                    Proprietaire prop = new Proprietaire();
                    prop.setId_prop(idProp);
                    prop.setNom(rs.getString("p.nom"));
                    prop.setContact(rs.getString("p.contact"));
                    prop.setAdresse(rs.getString("p.adresse"));
                    bien.setProprietaire(prop);
                }
                return bien;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(Bien bien) {
        String sql = "UPDATE Bien SET type = ?, ville = ?, surface = ?, prixMensuel = ?, id_prop = ? WHERE id_bien = ?";
        
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            
            ps.setString(1, bien.getType());
            ps.setString(2, bien.getVille());
            ps.setDouble(3, bien.getSurface());
            ps.setDouble(4, bien.getPrixMensuel());
            
            if (bien.getProprietaire() != null) {
                ps.setInt(5, bien.getProprietaire().getId_prop());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            
            ps.setInt(6, bien.getId_bien());
            
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(BienDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Bien bien) {
        String sql = "DELETE FROM Bien WHERE id_bien = ?";
        
        try {
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setInt(1, bien.getId_bien());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(BienDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
