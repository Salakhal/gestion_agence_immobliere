
    
/*
 * DashboardForm.java
 */
/*
 * DashboardForm.java
 */
package presentation;

import services.BienDao;
import services.LocationDao;
import services.ProprietaireDao;
import entities.Location;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class DashboardForm extends javax.swing.JPanel {

    // --- 1. DECLARATION DES SERVICES ---
    private ProprietaireDao propService = new ProprietaireDao();
    private BienDao bienService = new BienDao();
    private LocationDao locationService = new LocationDao();

    /**
     * CONSTRUCTEUR
     */
    public DashboardForm() {
        initComponents(); // <--- HADI DYAL NETBEANS (Matqishach!)
        
        // N3iyto 3la l'Code Dyalna
        calculerTout();
    }

    // --- 2. METHODE BACH N7SBO W NRSSMO ---
    public void calculerTout() {
        try {
            // A. STATISTIQUES (KPIs)
            lblNbProp.setText("" + propService.findAll().size());
            lblNbBien.setText("" + bienService.findAll().size());
            
            double total = 0;
            Map<String, Double> mapVilles = new HashMap<>();
            
            // B. PREPARATION DONNEES GRAPHIQUE
            for(Location l : locationService.findAll()){
                total += l.getLoyer();
                
                String v = l.getBien().getVille().toUpperCase();
                mapVilles.put(v, mapVilles.getOrDefault(v, 0.0) + l.getLoyer());
            }
            lblRevenu.setText(total + " DH");
            
            // C. RASSM HISTOGRAMME
            // Kancreer Rricha (Panel)
            HistogrammePanel graphPanel = new HistogrammePanel();
            graphPanel.setDonnees(mapVilles);
            graphPanel.setPreferredSize(new java.awt.Dimension(600, 400));
            
            // HNA FIN KANLASSQO L'RRICHA M3A DESIGN DYALK
            // panelGraphique howa JScrollPane li derti f Design
            panelGraphique.setViewportView(graphPanel);
            
        } catch(Exception e) {
            System.out.println("Erreur Dashboard: " + e.getMessage());
        }
    }

    /**
     * CLASS INTERNE : RRICHA LI KATRSSM L'A3MIDA
     */
    public class HistogrammePanel extends JPanel {
        private Map<String, Double> donnees = new HashMap<>();

        public void setDonnees(Map<String, Double> donnees) {
            this.donnees = donnees;
            repaint(); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            
            // Background Byed
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());

            if (donnees.isEmpty()) {
                g2.setColor(Color.GRAY);
                g2.drawString("Pas de données disponibles", getWidth()/2 - 50, getHeight()/2);
                return;
            }

            // Calcul Max
            double maxVal = 0;
            for(Double v : donnees.values()) if(v > maxVal) maxVal = v;

            // Dimensions
            int marge = 40;
            int h = getHeight() - 2*marge;
            int w = getWidth() - 2*marge;
            int barWidth = (donnees.size() > 0) ? w / donnees.size() - 20 : 50;
            int x = marge;

            // Axes
            g2.setColor(Color.BLACK);
            g2.drawLine(marge, getHeight()-marge, getWidth()-marge, getHeight()-marge); // X
            g2.drawLine(marge, getHeight()-marge, marge, marge); // Y

            // Boucle Rssm
            for (Map.Entry<String, Double> e : donnees.entrySet()) {
                int barHeight = (int) ((e.getValue() / maxVal) * h);
                
                // L'Barre (Azraq Fonce)
                g2.setColor(new Color(0, 102, 204));
                g2.fillRect(x, getHeight() - marge - barHeight, barWidth, barHeight);
                
                // Cadre
                g2.setColor(Color.BLACK);
                g2.drawRect(x, getHeight() - marge - barHeight, barWidth, barHeight);
                
                // Texte (Ville + Montant)
                g2.setFont(new Font("Arial", Font.BOLD, 12));
                g2.drawString(e.getKey(), x, getHeight() - marge + 15);
                
                g2.setColor(new Color(255, 102, 0)); // Orange
                g2.drawString(e.getValue().intValue() + " DH", x, getHeight() - marge - barHeight - 5);
                
                x += barWidth + 20;
            }
        }
    }

    /**
     * CODE DESIGN (GENERÉ PAR NETBEANS - NE PAS TOUCHER)
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblNbProp = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblNbBien = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblRevenu = new javax.swing.JLabel();
        panelGraphique = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(204, 204, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Propriétaires", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        lblNbProp.setFont(new java.awt.Font("Tahoma", 1, 36)); 
        lblNbProp.setForeground(new java.awt.Color(51, 51, 255));
        lblNbProp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNbProp.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNbProp, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNbProp)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Biens", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        lblNbBien.setFont(new java.awt.Font("Tahoma", 1, 36)); 
        lblNbBien.setForeground(new java.awt.Color(255, 153, 51));
        lblNbBien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNbBien.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNbBien, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNbBien)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Revenu Mensuel", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        lblRevenu.setFont(new java.awt.Font("Tahoma", 1, 36)); 
        lblRevenu.setForeground(new java.awt.Color(102, 255, 102));
        lblRevenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRevenu.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRevenu, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblRevenu)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelGraphique.setBackground(new java.awt.Color(255, 255, 255));
        panelGraphique.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Graphique : Loyers par Ville", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); 

        // LAYOUT (Adapter à ton Design)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGraphique)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelGraphique, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblNbBien;
    private javax.swing.JLabel lblNbProp;
    private javax.swing.JLabel lblRevenu;
    private javax.swing.JScrollPane panelGraphique;
    // End of variables declaration                   
}
