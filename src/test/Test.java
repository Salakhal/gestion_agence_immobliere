package test;

import entities.Bien;
import entities.Location;
import entities.Proprietaire;
import services.BienDao;
import services.LocationDao;
import services.ProprietaireDao;

import java.sql.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        
        // 1. Initialisation dyal les Services (DAOs)
        ProprietaireDao propService = new ProprietaireDao();
        BienDao bienService = new BienDao();
        LocationDao locationService = new LocationDao();

        System.out.println(" 1. CRÉATION DES PROPRIÉTAIRES ");
        // Ncreew 2 proprietaires
        Proprietaire p1 = new Proprietaire("Kamal Oudghiri", "0611223344", "Rabat Agdal");
        Proprietaire p2 = new Proprietaire("Salma Bennani", "salma@gmail.com", "Casa Maarif");

        propService.create(p1);
        propService.create(p2);

        // Nchoufuhom f la base
        System.out.println("  Proprietaires ajoutés avec succès !");
        for (Proprietaire p : propService.findAll()) {
            System.out.println("   ID: " + p.getId_prop() + " | Nom: " + p.getNom());
        }

        System.out.println("\n 2. CRÉATION DES BIENS ");
        // Ncreew Bien l Kamal (p1)
        // Remarque: p1 daba 3ndo ID 7it darna (RETURN_GENERATED_KEYS) f DAO
        Bien b1 = new Bien("Appartement", "Marrakech", 85.0, 5000.0, p1);
        
        // Ncreew Bien l Salma (p2)
        Bien b2 = new Bien("Villa", "Tanger", 300.0, 15000.0, p2);

        bienService.create(b1);
        bienService.create(b2);

        System.out.println("-> Biens ajoutés !");
        for (Bien b : bienService.findAll()) {
            String nomProp = "Aucun (Sans propriétaire)";
            
            if (b.getProprietaire() != null) {
                nomProp = b.getProprietaire().getNom();
            }

            System.out.println("   Bien: " + b.getType() + " à " + b.getVille() + 
                               " | Proprio: " + nomProp);
        }

        System.out.println("\n 3. CRÉATION DES LOCATIONS ");
        // Location 1: Appartement de Marrakech (b1)
        // Date: Aujoud'hui
        long millis = System.currentTimeMillis();
        Date dateDebut = new Date(millis);
        Date dateFin = new Date(millis + (1000 * 60 * 60 * 24 * 30L)); // +30 jours
        
        Location loc1 = new Location(dateDebut, dateFin, 4800.0, b1); // Kraha b 4800 dh
        
        // Location 2: Villa de Tanger (b2)
        Location loc2 = new Location(dateDebut, null, 15000.0, b2); // En cours (Pas de date fin)

        locationService.create(loc1);
        locationService.create(loc2);

        System.out.println(" Locations ajoutées !");

        System.out.println("\n 4. AFFICHAGE GLOBAL & REVENUS ");
        List<Location> locations = locationService.findAll();
        
        double totalRevenus = 0;

        for (Location loc : locations) {
           
            System.out.println("Contrat ID: " + loc.getId_location());
            System.out.println("Bien: " + loc.getBien().getType() + " (" + loc.getBien().getVille() + ")");
            // Hna l'qwwa dyal JOIN: Kanjbo smiyt l'moul chi mn Location
            System.out.println("Propriétaire: " + loc.getBien().getProprietaire().getNom()); 
            System.out.println("Loyer: " + loc.getLoyer() + " DH");
            
            totalRevenus += loc.getLoyer();
        }

        
        System.out.println(" REVENU MENSUEL TOTAL DE L'AGENCE : " + totalRevenus + " DH");
    }
}