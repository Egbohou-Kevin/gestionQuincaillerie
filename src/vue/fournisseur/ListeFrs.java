/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.fournisseur;

import gestion.Article.Fournisseur;
import gestion.Authentification.AbstractDAO;
import gestion.Authentification.Connexion;
import gestion.Authentification.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import vue.IVue;
import vue.article.Button;
import vue.article.ButtonRenderer;
import vue.article.TModel;

/**
 *
 * @author VEK
 */
public class ListeFrs extends javax.swing.JPanel implements IVue{
    private boolean Admin=false;
    private int IDGest=0;
    private ArrayList<ArrayList<Object>> data;
    private ArrayList<String> title= new ArrayList<String>();
    TModel modele;
    Connection con = Connexion.getConnection();
    /**
     * Creates new form ListeFr
     */
    public ListeFrs(boolean ad,int gest) {
        initComponents();
        jTextField_adresse.setVisible(false);
        jTextField_nom.setVisible(false);
        jTextField_tel.setVisible(false);
        Admin=ad;
        IDGest=gest;
        title.add("Id");
        title.add("Nom");
        title.add("mail");
        title.add("Telephone");
        title.add("Adresse");
        title.add("Modification");
        title.add("Suppression");
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Fournisseur> frsdao=fac.getFournisseurDAO();
            data=new ArrayList<ArrayList<Object>>();
            for(Fournisseur frs:frsdao.recuperer()){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+frs.getIdfrs());
                ligne.add(frs.getNomfrs());
                ligne.add(frs.getMailfrs());
                ligne.add(frs.getTelfrs());
                ligne.add(frs.getAdressfrs());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                ligne.add(bt);
                ligne.add(bt1);
                data.add(ligne);
            }
            modele=new TModel(data, title);
            tableau.setModel(modele);
            tableau.getColumn("Modification").setCellRenderer(new ButtonRenderer());
            tableau.getColumn("Suppression").setCellRenderer(new ButtonRenderer());
            tableau.getColumn("Modification").setCellEditor(new ButtonModifEditorFrs(new JCheckBox(),Admin,IDGest)); 
            tableau.getColumn("Suppression").setCellEditor(new ButtonSuppEditorFrs(new JCheckBox(),Admin,IDGest));
    }
    @Override
    public void afficher() {
        this.setVisible(true);
    }

    @Override
    public void cacher() {
        this.hide();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox_nom = new javax.swing.JCheckBox();
        jTextField_nom = new javax.swing.JTextField();
        jCheckBox_tel = new javax.swing.JCheckBox();
        jTextField_tel = new javax.swing.JTextField();
        jCheckBox_adresse = new javax.swing.JCheckBox();
        jTextField_adresse = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableau = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Type recherche :");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Recherche :");

        jCheckBox_nom.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCheckBox_nom.setText("Nom");
        jCheckBox_nom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_nomItemStateChanged(evt);
            }
        });

        jTextField_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_nomKeyReleased(evt);
            }
        });

        jCheckBox_tel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCheckBox_tel.setText("Telephone");
        jCheckBox_tel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_telItemStateChanged(evt);
            }
        });

        jTextField_tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_telKeyReleased(evt);
            }
        });

        jCheckBox_adresse.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCheckBox_adresse.setText("Adresse");
        jCheckBox_adresse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_adresseItemStateChanged(evt);
            }
        });

        jTextField_adresse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_adresseKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextField_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox_nom)))
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox_tel))
                .addGap(57, 57, 57)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox_adresse))
                .addContainerGap(381, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_nom)
                    .addComponent(jLabel9)
                    .addComponent(jCheckBox_adresse)
                    .addComponent(jCheckBox_tel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_adresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableau.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tableau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableau.setRowHeight(25);
        tableau.setShowHorizontalLines(false);
        tableau.setShowVerticalLines(false);
        tableau.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(tableau);

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ajouter");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 963, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        IVue vue=new Ajouter(tableau);
        vue.afficher();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private boolean isNumeric(char c){
        try{
            Integer.parseInt(String.valueOf(c));
        }
        catch(NumberFormatException ex){
            return false;
        }
        return true;
    }
    
    private void jCheckBox_nomItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_nomItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox_nom.isSelected()){
            jTextField_nom.setVisible(true);
            jPanel4.validate();
            affichageNom();
        }
        else{
            jTextField_nom.setVisible(false);
            jPanel4.validate();
        }
    }//GEN-LAST:event_jCheckBox_nomItemStateChanged

    private void jTextField_nomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_nomKeyReleased
        // TODO add your handling code here:
        ArrayList<Fournisseur> tab=nom_selected();
        if(jCheckBox_nom.isSelected()){
           if(Admin){
            data=new ArrayList<ArrayList<Object>>();
            for(Fournisseur frs:tab){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+frs.getIdfrs());
                ligne.add(frs.getNomfrs());
                ligne.add(frs.getMailfrs());
                ligne.add(frs.getTelfrs());
                ligne.add(frs.getAdressfrs());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                ligne.add(bt);
                ligne.add(bt1);
                data.add(ligne);
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
            } 
           
        }
    }//GEN-LAST:event_jTextField_nomKeyReleased

    private void jCheckBox_telItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_telItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox_tel.isSelected()){
            jTextField_tel.setVisible(true);
            jPanel4.validate();
            affichageNom();
        }
        else{
            jTextField_tel.setVisible(false);
            jPanel4.validate(); 
        }
    }//GEN-LAST:event_jCheckBox_telItemStateChanged

    private void jTextField_telKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_telKeyReleased
        // TODO add your handling code here:
        if(!isNumeric(evt.getKeyChar())){
            jTextField_tel.setText(jTextField_tel.getText().replace(String.valueOf(evt.getKeyChar()),""));
        }
        ArrayList<Fournisseur> tab=tel_selected();
        if(jCheckBox_tel.isSelected()){
           if(Admin){
            data=new ArrayList<ArrayList<Object>>();
            for(Fournisseur frs:tab){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+frs.getIdfrs());
                ligne.add(frs.getNomfrs());
                ligne.add(frs.getMailfrs());
                ligne.add(frs.getTelfrs());
                ligne.add(frs.getAdressfrs());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                ligne.add(bt);
                ligne.add(bt1);
                data.add(ligne);
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
            } 
           
        }
    }//GEN-LAST:event_jTextField_telKeyReleased

    private void jCheckBox_adresseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_adresseItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox_adresse.isSelected()){
            jTextField_adresse.setVisible(true);
            jPanel4.validate();
            affichageNom();
        }
        else{
            jTextField_adresse.setVisible(false);
            jPanel4.validate();
        }
    }//GEN-LAST:event_jCheckBox_adresseItemStateChanged

    private void jTextField_adresseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_adresseKeyReleased
        // TODO add your handling code here:
        ArrayList<Fournisseur> tab=adresse_selected();
        if(jCheckBox_adresse.isSelected()){
           if(Admin){
            data=new ArrayList<ArrayList<Object>>();
            for(Fournisseur frs:tab){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+frs.getIdfrs());
                ligne.add(frs.getNomfrs());
                ligne.add(frs.getMailfrs());
                ligne.add(frs.getTelfrs());
                ligne.add(frs.getAdressfrs());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                ligne.add(bt);
                ligne.add(bt1);
                data.add(ligne);
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
            } 
           
        }
    }//GEN-LAST:event_jTextField_adresseKeyReleased
    
    public ArrayList<Fournisseur> nom_selected(){
        String nom;
        nom=jTextField_nom.getText();
        Connection con=Connexion.getConnection();
        ArrayList<Fournisseur> tab=new ArrayList<Fournisseur>();
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Fournisseur> frsdao=fac.getFournisseurDAO();
        try{
            Statement req=con.createStatement();
            ResultSet result=req.executeQuery("SELECT * FROM fournisseur WHERE nomfrs like "+" \"%"+nom+"%\"");
            while(result.next()){
                tab.add(frsdao.rechercher(result.getInt("idfrs")));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return tab;
    }
    public ArrayList<Fournisseur> tel_selected(){
        String tel;
        tel=jTextField_tel.getText();
        Connection con=Connexion.getConnection();
        ArrayList<Fournisseur> tab=new ArrayList<Fournisseur>();
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Fournisseur> frsdao=fac.getFournisseurDAO();
        try{
            Statement req=con.createStatement();
            ResultSet result=req.executeQuery("SELECT * FROM fournisseur WHERE telfrs like "+" \"%"+tel+"%\"");
            while(result.next()){
                tab.add(frsdao.rechercher(result.getInt("idfrs")));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return tab;
    }
    public ArrayList<Fournisseur> adresse_selected(){
        String adr;
        adr=jTextField_adresse.getText();
        Connection con=Connexion.getConnection();
        ArrayList<Fournisseur> tab=new ArrayList<Fournisseur>();
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Fournisseur> frsdao=fac.getFournisseurDAO();
        try{
            Statement req=con.createStatement();
            ResultSet result=req.executeQuery("SELECT * FROM fournisseur WHERE adressefrs like "+" \"%"+adr+"%\"");
            while(result.next()){
                tab.add(frsdao.rechercher(result.getInt("idfrs")));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return tab;
    }
    
    public void affichageNom(){
        if(Admin){
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Fournisseur> frsdao=fac.getFournisseurDAO();
            data=new ArrayList<ArrayList<Object>>();
            for(Fournisseur frs:frsdao.recuperer()){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+frs.getIdfrs());
                ligne.add(frs.getNomfrs());
                ligne.add(frs.getMailfrs());
                ligne.add(frs.getTelfrs());
                ligne.add(frs.getAdressfrs());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                ligne.add(bt);
                ligne.add(bt1);
                data.add(ligne);
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
            }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox_adresse;
    private javax.swing.JCheckBox jCheckBox_nom;
    private javax.swing.JCheckBox jCheckBox_tel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_adresse;
    private javax.swing.JTextField jTextField_nom;
    private javax.swing.JTextField jTextField_tel;
    private javax.swing.JTable tableau;
    // End of variables declaration//GEN-END:variables
}