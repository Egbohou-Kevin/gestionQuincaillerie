/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue.vente;

import gestion.Authentification.AbstractDAO;
import gestion.Authentification.Connexion;
import gestion.Authentification.DAO;
import gestion.Authentification.Gestionnaire;
import gestion.Vente.Vente;
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
public class Liste extends javax.swing.JPanel {
    private ArrayList<ArrayList<Object>> data;
    private ArrayList<String> title= new ArrayList<String>();
    TModel modele;
    Connection con = Connexion.getConnection();
    private boolean Admin=false;
    private int IDgest=0;
    /**
     * Creates new form Liste
     */
    public Liste(boolean ad,int id) {
        Admin=ad;
        IDgest=id;
        initComponents();
        jTextField_libelle.setVisible(false);
        jTextField_montant.setVisible(false);
        jTextField_date.setVisible(false);
        jComboBox_gest.setVisible(false);
        jComboBox_sup_montant.setVisible(false);
        if(Admin){
            admin();
        }
        else{
            gestionnaire();
            jCheckBox_gest.setVisible(false);
        }
    }
    
    public void gestionnaire(){
        //Affichage pour un gestionnaire
        title.add("Id");
        title.add("Libelle");
        title.add("Montant");
        title.add("Date");
        title.add("Gestionnaire");
        title.add("Detail");
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Vente> vtdao=fac.getVenteDAO();
        data=new ArrayList<ArrayList<Object>>();
        for(Vente vente:vtdao.recuperer()){
            if(IDgest==vente.getGest().getIdgest()){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+vente.getIdvente());
                ligne.add(vente.getDesvente());
                ligne.add(vente.getMontantvente());
                ligne.add(""+vente.getDatevente());
                ligne.add(""+vente.getGest().getNomgest());
                Button bt2=new Button("Detail");
                ligne.add(bt2);
                data.add(ligne);
            }
        }
        modele=new TModel(data, title);
        tableau.setModel(modele);
        tableau.getColumn("Detail").setCellRenderer(new ButtonRenderer());
        tableau.getColumn("Detail").setCellEditor(new ButtonDetailEditorGestVente(new JCheckBox(),Admin,IDgest));
    }
    
    public void admin(){
        title.add("Id");
        title.add("Libelle");
        title.add("Montant");
        title.add("Date");
        title.add("Gestionnaire");
        title.add("Modification");
        title.add("Suppression");
        title.add("Detail");
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Vente> vtdao=fac.getVenteDAO();
        data=new ArrayList<ArrayList<Object>>();
        for(Vente vente:vtdao.recuperer()){
            ArrayList<Object> ligne=new ArrayList<Object>();
            ligne.add(""+vente.getIdvente());
            ligne.add(vente.getDesvente());
            ligne.add(vente.getMontantvente());
            ligne.add(""+vente.getDatevente());
            ligne.add(""+vente.getGest().getNomgest());
            Button bt=new Button("Modifier");
            Button bt1=new Button("Supprimer");
            Button bt2=new Button("Detail");
            ligne.add(bt);
            ligne.add(bt1);
            ligne.add(bt2);
            data.add(ligne);
        }
        modele=new TModel(data, title);
        tableau.setModel(modele);
        tableau.getColumn("Modification").setCellRenderer(new ButtonRenderer());
        tableau.getColumn("Suppression").setCellRenderer(new ButtonRenderer());
        tableau.getColumn("Detail").setCellRenderer(new ButtonRenderer());
        tableau.getColumn("Modification").setCellEditor(new ButtonModifEditorVente(new JCheckBox(),Admin,IDgest));
        tableau.getColumn("Suppression").setCellEditor(new ButtonSuppEditorVente(new JCheckBox(),Admin,IDgest));
        tableau.getColumn("Detail").setCellEditor(new ButtonDetailEditorVente(new JCheckBox(),Admin,IDgest));
        jButton_effectuer.setVisible(false);
    }
    
    public ArrayList<Vente> lib_selected(){
        String lib;
        lib=jTextField_libelle.getText();
        Connection con=Connexion.getConnection();
        ArrayList<Vente> tab=new ArrayList<Vente>();
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Vente> ventedao=fac.getVenteDAO();
        try{
            Statement req=con.createStatement();
            ResultSet result=req.executeQuery("SELECT * FROM vente WHERE desvente like "+" \"%"+lib+"%\"");
            while(result.next()){
                tab.add(ventedao.rechercher(result.getInt("idvente")));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return tab;
    }
    public ArrayList<Vente>  prix_selected(){
        ArrayList<Vente> tab=new ArrayList<Vente>();
        float prix=0;
        if(jTextField_montant.getText().equals("")){
            try{
                Connection con=Connexion.getConnection();
                AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
                DAO<Vente> ventedao=fac.getVenteDAO();
                Statement req=con.createStatement();
                ResultSet result=req.executeQuery("SELECT * FROM vente ORDER BY montantvente ASC");
                while(result.next()){
                   tab.add(ventedao.rechercher(result.getInt("idvente")));
                } 
            }
            catch(Exception e){
                
            }
        }
        else{
            prix=Float.parseFloat(jTextField_montant.getText());
            Connection con=Connexion.getConnection();
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Vente> ventedao=fac.getVenteDAO();
            try{
                if(jComboBox_sup_montant.getSelectedIndex()==0){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente>="+prix);
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
                if(jComboBox_sup_montant.getSelectedIndex()==1){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente<="+prix);
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
                if(jComboBox_sup_montant.getSelectedIndex()==2){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente="+prix);
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return tab;
    }
    public ArrayList<Vente> gestionnaire_selected(){
        Gestionnaire gest;
        gest=(Gestionnaire)jComboBox_gest.getSelectedItem();
        Connection con=Connexion.getConnection();
        ArrayList<Vente> tab=new ArrayList<Vente>();
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Vente> ventedao=fac.getVenteDAO();
        try{
            Statement req=con.createStatement();
            ResultSet result=req.executeQuery("SELECT * FROM vente WHERE idgest="+gest.getIdgest());
            while(result.next()){
                tab.add(ventedao.rechercher(result.getInt("idvente")));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return tab;
    }
    public ArrayList<Vente> Lib_Montant_selected(){
        ArrayList<Vente> tab=new ArrayList<Vente>();
        float prix=0;
        String nom="";
        if(jTextField_montant.getText().equals("")){
            try{
                Connection con=Connexion.getConnection();
                AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
                DAO<Vente> ventedao=fac.getVenteDAO();
                Statement req=con.createStatement();
                ResultSet result=req.executeQuery("SELECT * FROM vente ORDER BY montantvente ASC");
                while(result.next()){
                   tab.add(ventedao.rechercher(result.getInt("idvente")));
                } 
            }
            catch(Exception e){
                
            }
        }
        else{
            prix=Float.parseFloat(jTextField_montant.getText());
            nom=jTextField_libelle.getText();
            Connection con=Connexion.getConnection();
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Vente> ventedao=fac.getVenteDAO();
            try{
                if(jComboBox_sup_montant.getSelectedIndex()==0){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente>="+prix+" AND"+" desvente like "+" \"%"+nom+"%\"");
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
                if(jComboBox_sup_montant.getSelectedIndex()==1){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente<="+prix+" AND"+" desvente like "+" \"%"+nom+"%\"");
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
                if(jComboBox_sup_montant.getSelectedIndex()==2){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente="+prix+" AND"+" desvente like "+" \"%"+nom+"%\"");
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
            }
            catch(Exception e){

            }
        }
        return tab;
    }
    /*public ArrayList<Vente> Lib_Date_selected(){
        
    }*/
    public ArrayList<Vente>Lib_Gestionnaire_selected(){
        String lib;
        lib=jTextField_libelle.getText();
        Gestionnaire gest;
        gest=(Gestionnaire)jComboBox_gest.getSelectedItem();
        Connection con=Connexion.getConnection();
        ArrayList<Vente> tab=new ArrayList<Vente>();
        AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
        DAO<Vente> ventedao=fac.getVenteDAO();
        try{
            Statement req=con.createStatement();
            ResultSet result=req.executeQuery("SELECT * FROM vente WHERE idgest="+gest.getIdgest()+" AND desvente like"+" \"%"+lib+"%\"");
            while(result.next()){
                tab.add(ventedao.rechercher(result.getInt("idvente")));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return tab;
    }
    /*public ArrayList<Vente> Montant_Date_selected(){
        
    }*/
    public ArrayList<Vente> Montant_Geestionnaire_selected(){
        Gestionnaire gest;
        gest=(Gestionnaire)jComboBox_gest.getSelectedItem();
        ArrayList<Vente> tab=new ArrayList<Vente>();
        float prix=0;
        if(jTextField_montant.getText().equals("")){
            try{
                Connection con=Connexion.getConnection();
                AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
                DAO<Vente> ventedao=fac.getVenteDAO();
                Statement req=con.createStatement();
                ResultSet result=req.executeQuery("SELECT * FROM vente ORDER BY montantvente ASC");
                while(result.next()){
                   tab.add(ventedao.rechercher(result.getInt("idvente")));
                } 
            }
            catch(Exception e){
                
            }
        }
        else{
            prix=Float.parseFloat(jTextField_montant.getText());
            Connection con=Connexion.getConnection();
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Vente> ventedao=fac.getVenteDAO();
            try{
                if(jComboBox_sup_montant.getSelectedIndex()==0){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente>="+prix+" AND idgest="+gest.getIdgest());
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
                if(jComboBox_sup_montant.getSelectedIndex()==1){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente<="+prix+" AND idgest="+gest.getIdgest());
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
                if(jComboBox_sup_montant.getSelectedIndex()==2){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente="+prix+" AND idgest="+gest.getIdgest());
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return tab;
    }
    /*public ArrayList<Vente> Date_Gestionnaire_selected(){
        
    }
    public ArrayList<Vente> Lib_Montant_Date_selected(){
        
    }
    public ArrayList<Vente> Lib_Date_Gestionnaire_selected(){
        
    }*/
    private boolean isNumeric(char c){
        try{
            Integer.parseInt(String.valueOf(c));
        }
        catch(NumberFormatException ex){
            return false;
        }
        return true;
    }
    
    public ArrayList<Vente> Lib_Montant_Gestionnaire_selected(){
        Gestionnaire gest;
        gest=(Gestionnaire)jComboBox_gest.getSelectedItem();
        ArrayList<Vente> tab=new ArrayList<Vente>();
        float prix=0;
        String nom="";
        if(jTextField_montant.getText().equals("")){
            try{
                Connection con=Connexion.getConnection();
                AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
                DAO<Vente> ventedao=fac.getVenteDAO();
                Statement req=con.createStatement();
                ResultSet result=req.executeQuery("SELECT * FROM vente ORDER BY montantvente ASC");
                while(result.next()){
                   tab.add(ventedao.rechercher(result.getInt("idvente")));
                } 
            }
            catch(Exception e){
                
            }
        }
        else{
            prix=Float.parseFloat(jTextField_montant.getText());
            nom=jTextField_libelle.getText();
            Connection con=Connexion.getConnection();
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Vente> ventedao=fac.getVenteDAO();
            try{
                if(jComboBox_sup_montant.getSelectedIndex()==0){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente>="+prix+" AND"+" desvente like "+" \"%"+nom+"%\""+" AND idgest="+gest.getIdgest());
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
                if(jComboBox_sup_montant.getSelectedIndex()==1){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente<="+prix+" AND"+" desvente like "+" \"%"+nom+"%\""+" AND idgest="+gest.getIdgest());
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
                if(jComboBox_sup_montant.getSelectedIndex()==2){
                    Statement req=con.createStatement();
                    ResultSet result=req.executeQuery("SELECT * FROM vente WHERE montantvente="+prix+" AND"+" desvente like "+" \"%"+nom+"%\""+" AND idgest="+gest.getIdgest());
                    while(result.next()){
                        tab.add(ventedao.rechercher(result.getInt("idvente")));
                    }
                }
            }
            catch(Exception e){

            }
        }
        return tab;
    }
    /*public ArrayList<Vente> Montant_Date_Gestionnaire_selected(){
        
    }
    public ArrayList<Vente> Lib_Montant_Date_Gestionnaire_selected(){
        
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox_libelle = new javax.swing.JCheckBox();
        jTextField_libelle = new javax.swing.JTextField();
        jCheckBox_montant = new javax.swing.JCheckBox();
        jTextField_montant = new javax.swing.JTextField();
        jCheckBox_date = new javax.swing.JCheckBox();
        jComboBox_sup_montant = new javax.swing.JComboBox<>();
        jComboBox_gest = new javax.swing.JComboBox<>();
        jCheckBox_gest = new javax.swing.JCheckBox();
        jTextField_date = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton_valider = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableau = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton_effectuer = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Type recherche :");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Recherche :");

        jCheckBox_libelle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCheckBox_libelle.setText("Libelle");
        jCheckBox_libelle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_libelleItemStateChanged(evt);
            }
        });

        jTextField_libelle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_libelleKeyReleased(evt);
            }
        });

        jCheckBox_montant.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCheckBox_montant.setText("Montant");
        jCheckBox_montant.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_montantItemStateChanged(evt);
            }
        });

        jTextField_montant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_montantKeyReleased(evt);
            }
        });

        jCheckBox_date.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCheckBox_date.setText("Date");
        jCheckBox_date.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_dateItemStateChanged(evt);
            }
        });

        jComboBox_sup_montant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Supérieur", "Inférieur", "Egale" }));
        jComboBox_sup_montant.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_sup_montantItemStateChanged(evt);
            }
        });

        jComboBox_gest.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_gestItemStateChanged(evt);
            }
        });

        jCheckBox_gest.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jCheckBox_gest.setText("Gestionnaire");
        jCheckBox_gest.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_gestItemStateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));

        jButton_valider.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_valider.setForeground(new java.awt.Color(255, 255, 255));
        jButton_valider.setText("Valider");
        jButton_valider.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_valider.setContentAreaFilled(false);
        jButton_valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_validerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton_valider, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton_valider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_libelle)
                    .addComponent(jTextField_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_montant)
                    .addComponent(jTextField_montant, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_sup_montant, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_date)
                    .addComponent(jTextField_date, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_gest, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox_gest))
                .addContainerGap(150, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox_libelle)
                            .addComponent(jLabel9)
                            .addComponent(jCheckBox_gest)
                            .addComponent(jCheckBox_montant)
                            .addComponent(jCheckBox_date))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_gest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_sup_montant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_montant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        tableau.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(tableau);

        jPanel5.setBackground(new java.awt.Color(0, 102, 0));
        jPanel5.setForeground(new java.awt.Color(0, 102, 0));

        jButton_effectuer.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_effectuer.setForeground(new java.awt.Color(255, 255, 255));
        jButton_effectuer.setText("Effectuer");
        jButton_effectuer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_effectuer.setContentAreaFilled(false);
        jButton_effectuer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_effectuerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton_effectuer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton_effectuer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 958, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addGap(101, 101, 101))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1092, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox_libelleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_libelleItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox_libelle.isSelected()){
            jTextField_libelle.setVisible(true);
            jPanel3.validate();
            affichageLib();
        }
        else
            jTextField_libelle.setVisible(false);
            jPanel3.validate();
        
    }//GEN-LAST:event_jCheckBox_libelleItemStateChanged

    private void jTextField_libelleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_libelleKeyReleased
        // TODO add your handling code here:
        ArrayList<Vente> tab=lib_selected();
        if(jCheckBox_libelle.isSelected()){
            if(Admin){
            data=new ArrayList<ArrayList<Object>>();
            for(Vente vente:tab){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+vente.getIdvente());
                ligne.add(vente.getDesvente());
                ligne.add(vente.getMontantvente());
                ligne.add(""+vente.getDatevente());
                ligne.add(""+vente.getGest().getNomgest());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                Button bt2=new Button("Detail");
                ligne.add(bt);
                ligne.add(bt1);
                ligne.add(bt2);
                data.add(ligne);
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
            }
            else{
                data=new ArrayList<ArrayList<Object>>();
                for(Vente vente:tab){
                    if(IDgest==vente.getGest().getIdgest()){
                        ArrayList<Object> ligne=new ArrayList<Object>();
                        ligne.add(""+vente.getIdvente());
                        ligne.add(vente.getDesvente());
                        ligne.add(vente.getMontantvente());
                        ligne.add(""+vente.getDatevente());
                        ligne.add(""+vente.getGest().getNomgest());
                        Button bt2=new Button("Detail");
                        ligne.add(bt2);
                        data.add(ligne);
                    }
                }
                modele.setData(data);
                tableau.setModel(modele);
                tableau.repaint();
            }
        }
        
    }//GEN-LAST:event_jTextField_libelleKeyReleased

    private void jCheckBox_montantItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_montantItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox_montant.isSelected()){
            jTextField_montant.setVisible(true);
            jComboBox_sup_montant.setVisible(true);
            jPanel3.validate();
            affichagePrix();
        }
        else{
            jTextField_montant.setVisible(false);
            jComboBox_sup_montant.setVisible(false);
            jPanel3.validate();
        }
    }//GEN-LAST:event_jCheckBox_montantItemStateChanged

    private void jTextField_montantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_montantKeyReleased
        // TODO add your handling code here:
        if(!isNumeric(evt.getKeyChar())){
            jTextField_montant.setText(jTextField_montant.getText().replace(String.valueOf(evt.getKeyChar()),""));
        }
        else{
            
        }
        if(jCheckBox_montant.isSelected()){
            ArrayList<Vente> tab=prix_selected();
            if(jCheckBox_montant.isSelected()){//redondance ca n'a pas raison d'etre j'ai la flemme de modifier
                if(Admin){
                data=new ArrayList<ArrayList<Object>>();
                for(Vente vente:tab){
                    ArrayList<Object> ligne=new ArrayList<Object>();
                    ligne.add(""+vente.getIdvente());
                    ligne.add(vente.getDesvente());
                    ligne.add(vente.getMontantvente());
                    ligne.add(""+vente.getDatevente());
                    ligne.add(""+vente.getGest().getNomgest());
                    Button bt=new Button("Modifier");
                    Button bt1=new Button("Supprimer");
                    Button bt2=new Button("Detail");
                    ligne.add(bt);
                    ligne.add(bt1);
                    ligne.add(bt2);
                    data.add(ligne);
                }
                modele.setData(data);
                tableau.setModel(modele);
                tableau.repaint();
                }
                else{
                    data=new ArrayList<ArrayList<Object>>();
                    for(Vente vente:tab){
                        if(IDgest==vente.getGest().getIdgest()){
                            ArrayList<Object> ligne=new ArrayList<Object>();
                            ligne.add(""+vente.getIdvente());
                            ligne.add(vente.getDesvente());
                            ligne.add(vente.getMontantvente());
                            ligne.add(""+vente.getDatevente());
                            ligne.add(""+vente.getGest().getNomgest());
                            Button bt2=new Button("Detail");
                            ligne.add(bt2);
                            data.add(ligne);
                        }
                    }
                    modele.setData(data);
                    tableau.setModel(modele);
                    tableau.repaint();
                }
            }
        }
    }//GEN-LAST:event_jTextField_montantKeyReleased

    private void jCheckBox_dateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_dateItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jCheckBox_dateItemStateChanged

    private void jComboBox_sup_montantItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_sup_montantItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox_sup_montantItemStateChanged

    private void jCheckBox_gestItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_gestItemStateChanged
        // TODO add your handling code here:
        if(jCheckBox_gest.isSelected()){
            jComboBox_gest.removeAllItems();
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Gestionnaire> gestdao=fac.getGestionnaireDAO();
            for(Gestionnaire gest:gestdao.recuperer()){
                jComboBox_gest.addItem(gest);
            }
            jComboBox_gest.setVisible(true);
            jPanel3.validate();
        }
        else{
            jComboBox_gest.setVisible(false);
            jPanel3.validate();
        }
    }//GEN-LAST:event_jCheckBox_gestItemStateChanged
    
     public void affichageLib(){
        if(Admin){
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Vente> vtdao=fac.getVenteDAO();
            data=new ArrayList<ArrayList<Object>>();
            for(Vente vente:vtdao.recuperer()){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+vente.getIdvente());
                ligne.add(vente.getDesvente());
                ligne.add(vente.getMontantvente());
                ligne.add(""+vente.getDatevente());
                ligne.add(""+vente.getGest().getNomgest());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                Button bt2=new Button("Detail");
                ligne.add(bt);
                ligne.add(bt1);
                ligne.add(bt2);
                data.add(ligne);
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
            }
        else{
            AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
            DAO<Vente> vtdao=fac.getVenteDAO();
            data=new ArrayList<ArrayList<Object>>();
            for(Vente vente:vtdao.recuperer()){
                if(IDgest==vente.getGest().getIdgest()){
                    ArrayList<Object> ligne=new ArrayList<Object>();
                    ligne.add(""+vente.getIdvente());
                    ligne.add(vente.getDesvente());
                    ligne.add(vente.getMontantvente());
                    ligne.add(""+vente.getDatevente());
                    ligne.add(""+vente.getGest().getNomgest());
                    Button bt2=new Button("Detail");
                    ligne.add(bt2);
                    data.add(ligne);
                }
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
        }
    }
     public void affichagePrix(){
        if(Admin){
            ArrayList<Vente> tab=new ArrayList<Vente>();
            try{
                Connection con=Connexion.getConnection();
                AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
                DAO<Vente> ventedao=fac.getVenteDAO();
                Statement req=con.createStatement();
                ResultSet result=req.executeQuery("SELECT * FROM vente ORDER BY montantvente ASC");
                while(result.next()){
                   tab.add(ventedao.rechercher(result.getInt("idvente")));
                } 
            }
            catch(Exception e){
                System.out.println(e);
            }
            data=new ArrayList<ArrayList<Object>>();
            for(Vente vente:tab){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+vente.getIdvente());
                ligne.add(vente.getDesvente());
                ligne.add(vente.getMontantvente());
                ligne.add(""+vente.getDatevente());
                ligne.add(""+vente.getGest().getNomgest());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                Button bt2=new Button("Detail");
                ligne.add(bt);
                ligne.add(bt1);
                ligne.add(bt2);
                data.add(ligne);
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
            }
        else{
            ArrayList<Vente> tab=new ArrayList<Vente>();
            try{
                Connection con=Connexion.getConnection();
                AbstractDAO fac = AbstractDAO.getFactory(AbstractDAO.MYSQL_DAO_FACTORY);
                DAO<Vente> ventedao=fac.getVenteDAO();
                Statement req=con.createStatement();
                ResultSet result=req.executeQuery("SELECT * FROM vente ORDER BY montantvente ASC");
                while(result.next()){
                   tab.add(ventedao.rechercher(result.getInt("idvente")));
                } 
            }
            catch(Exception e){
                System.out.println(e);
            }
            data=new ArrayList<ArrayList<Object>>();
            for(Vente vente:tab){
                if(IDgest==vente.getGest().getIdgest()){
                    ArrayList<Object> ligne=new ArrayList<Object>();
                    ligne.add(""+vente.getIdvente());
                    ligne.add(vente.getDesvente());
                    ligne.add(vente.getMontantvente());
                    ligne.add(""+vente.getDatevente());
                    ligne.add(""+vente.getGest().getNomgest());
                    Button bt2=new Button("Detail");
                    ligne.add(bt2);
                    data.add(ligne);
                }
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
        }
    }
    private void jButton_effectuerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_effectuerActionPerformed
        // TODO add your handling code here:
        IVue vue = new Effectuer(Admin, IDgest);
        vue.afficher();
    }//GEN-LAST:event_jButton_effectuerActionPerformed

    private void jButton_validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_validerActionPerformed
        // TODO add your handling code here:
        ArrayList<Vente> tab=new ArrayList<Vente>();//donnees de la table
        if(jCheckBox_libelle.isSelected() && jCheckBox_montant.isSelected()){
            tab=Lib_Montant_selected();
        }
        if(jCheckBox_libelle.isEnabled() && jCheckBox_gest.isSelected()){
            tab=Lib_Gestionnaire_selected();
        }
        if(jCheckBox_montant.isSelected() && jCheckBox_gest.isSelected()){
            tab=Montant_Geestionnaire_selected();
        }
        if(jCheckBox_libelle.isSelected() && jCheckBox_montant.isSelected() && jCheckBox_gest.isSelected()){
            tab=Lib_Montant_Gestionnaire_selected();
        }
        
        if(true){//affichage des donnees
            if(Admin){
                data=new ArrayList<ArrayList<Object>>();
                for(Vente vente:tab){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+vente.getIdvente());
                ligne.add(vente.getDesvente());
                ligne.add(vente.getMontantvente());
                ligne.add(""+vente.getDatevente());
                ligne.add(""+vente.getGest().getNomgest());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                Button bt2=new Button("Detail");
                ligne.add(bt);
                ligne.add(bt1);
                ligne.add(bt2);
                data.add(ligne);
                }
                modele.setData(data);
                tableau.setModel(modele);
                tableau.repaint();
            }
        else{
            data=new ArrayList<ArrayList<Object>>();
            for(Vente vente:tab){
                if(IDgest==vente.getGest().getIdgest()){
                    ArrayList<Object> ligne=new ArrayList<Object>();
                    ligne.add(""+vente.getIdvente());
                    ligne.add(vente.getDesvente());
                    ligne.add(vente.getMontantvente());
                    ligne.add(""+vente.getDatevente());
                    ligne.add(""+vente.getGest().getNomgest());
                    Button bt2=new Button("Detail");
                    ligne.add(bt2);
                    data.add(ligne);
                }
            }
                modele.setData(data);
                tableau.setModel(modele);
                tableau.repaint();
            }
        }
    }//GEN-LAST:event_jButton_validerActionPerformed

    private void jComboBox_gestItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_gestItemStateChanged
        // TODO add your handling code here:
        ArrayList<Vente> tab=gestionnaire_selected();
        if(jCheckBox_gest.isSelected()){
            if(Admin){
            data=new ArrayList<ArrayList<Object>>();
            for(Vente vente:tab){
                ArrayList<Object> ligne=new ArrayList<Object>();
                ligne.add(""+vente.getIdvente());
                ligne.add(vente.getDesvente());
                ligne.add(vente.getMontantvente());
                ligne.add(""+vente.getDatevente());
                ligne.add(""+vente.getGest().getNomgest());
                Button bt=new Button("Modifier");
                Button bt1=new Button("Supprimer");
                Button bt2=new Button("Detail");
                ligne.add(bt);
                ligne.add(bt1);
                ligne.add(bt2);
                data.add(ligne);
            }
            modele.setData(data);
            tableau.setModel(modele);
            tableau.repaint();
            }
            else{
                data=new ArrayList<ArrayList<Object>>();
                for(Vente vente:tab){
                    if(IDgest==vente.getGest().getIdgest()){
                        ArrayList<Object> ligne=new ArrayList<Object>();
                        ligne.add(""+vente.getIdvente());
                        ligne.add(vente.getDesvente());
                        ligne.add(vente.getMontantvente());
                        ligne.add(""+vente.getDatevente());
                        ligne.add(""+vente.getGest().getNomgest());
                        Button bt2=new Button("Detail");
                        ligne.add(bt2);
                        data.add(ligne);
                    }
                }
                modele.setData(data);
                tableau.setModel(modele);
                tableau.repaint();
            }
        }
    }//GEN-LAST:event_jComboBox_gestItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_effectuer;
    private javax.swing.JButton jButton_valider;
    private javax.swing.JCheckBox jCheckBox_date;
    private javax.swing.JCheckBox jCheckBox_gest;
    private javax.swing.JCheckBox jCheckBox_libelle;
    private javax.swing.JCheckBox jCheckBox_montant;
    private javax.swing.JComboBox<Object> jComboBox_gest;
    private javax.swing.JComboBox<String> jComboBox_sup_montant;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_date;
    private javax.swing.JTextField jTextField_libelle;
    private javax.swing.JTextField jTextField_montant;
    private javax.swing.JTable tableau;
    // End of variables declaration//GEN-END:variables
}
