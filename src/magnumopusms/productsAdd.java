/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magnumopusms;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author A100519
 */
public class productsAdd extends javax.swing.JFrame {


    /**
     * Creates new form productsAdd
     */
    public productsAdd() {
        initComponents();
        
        
    }
    public void  getCategorias(){
        categorias categorias = new categorias();
        ArrayList<Cat> list = categorias.catList();
        String nome;
        for(int i=0; i<list.size();i++){
            nome=list.get(i).getNome_cat();
            categoriaComboBox.addItem(nome);
        }
    }
    public int getCodCat() throws SQLException{
        String nomeCat = (String) categoriaComboBox.getSelectedItem();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM categorias WHERE nome_categoria = '"+nomeCat+"' ";
        ps = dbConnection.getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        if(rs.next()){
            try{
                int cod = rs.getInt("cod_categoria");
                return cod;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Erro - Query! Query = '"+query+"' ");
        }return 0;
    }
    public int getCodSubCat() throws SQLException{
        String nomeSubCat = (String) subcategoriaComboBox.getSelectedItem();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM sub_categorias WHERE nome_subcategoria = '"+nomeSubCat+"' ";
        ps = dbConnection.getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        if(rs.next()){
            try{
                int cod = rs.getInt("cod_subcategoria");
                return cod;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Erro - Query! Query = '"+query+"' ");
        }return 0;
    }
    public void getSubCategorias() throws SQLException{
        Connection con = dbConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM sub_categorias WHERE cod_categoria = '"+getCodCat()+"' ";
        ps = dbConnection.getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()){
            try{
                String nome = rs.getString("nome_subcategoria");
                subcategoriaComboBox.addItem(nome);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void executeSQLQuery(String query, String message){
        Connection con = dbConnection.getConnection();
        Statement st;
        try{
            st = con.createStatement();
            if(st.executeUpdate(query) == 1){
                JOptionPane.showMessageDialog(null, "Dados "+message+" com sucesso");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Dados não "+message);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        buttonExit = new javax.swing.JButton();
        labelMOpus = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        labelArea = new javax.swing.JLabel();
        fieldNomeProd = new javax.swing.JTextField();
        labelNome = new javax.swing.JLabel();
        buttonUpdate = new javax.swing.JButton();
        categoriaComboBox = new javax.swing.JComboBox<>();
        labelCat = new javax.swing.JLabel();
        subcategoriaComboBox = new javax.swing.JComboBox<>();
        labelSubCat = new javax.swing.JLabel();
        labelQuant = new javax.swing.JLabel();
        fieldQuant = new javax.swing.JTextField();
        fieldPrice = new javax.swing.JTextField();
        labelPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        backgroundPanel.setBackground(new java.awt.Color(106, 49, 127));

        buttonExit.setBackground(new java.awt.Color(255, 204, 102));
        buttonExit.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        buttonExit.setText("X");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        labelMOpus.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        labelMOpus.setForeground(new java.awt.Color(255, 255, 255));
        labelMOpus.setText("Magnum Opus");

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        labelArea.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelArea.setText("Adicionar Novo Produto");

        fieldNomeProd.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        fieldNomeProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNomeProdActionPerformed(evt);
            }
        });

        labelNome.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelNome.setText("Nome");

        buttonUpdate.setBackground(new java.awt.Color(255, 204, 102));
        buttonUpdate.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        buttonUpdate.setText("ADICIONAR");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        categoriaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaComboBoxActionPerformed(evt);
            }
        });

        labelCat.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelCat.setText("Categoria");

        subcategoriaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subcategoriaComboBoxActionPerformed(evt);
            }
        });

        labelSubCat.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelSubCat.setText("SubCategoria");

        labelQuant.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelQuant.setText("Quantidade");

        fieldQuant.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        fieldQuant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldQuantActionPerformed(evt);
            }
        });

        fieldPrice.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        fieldPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPriceActionPerformed(evt);
            }
        });

        labelPrice.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelPrice.setText("Preço");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelArea))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNome)
                            .addComponent(labelCat)
                            .addComponent(labelSubCat)
                            .addComponent(labelQuant)
                            .addComponent(labelPrice))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(categoriaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldNomeProd, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(subcategoriaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldQuant, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(fieldPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(buttonUpdate)))
                .addGap(55, 55, 55))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(buttonUpdate))
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(fieldNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subcategoriaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSubCat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuant, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldQuant, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(labelArea, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMOpus)
                .addGap(271, 271, 271)
                .addComponent(buttonExit)
                .addContainerGap())
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMOpus)
                    .addComponent(buttonExit))
                .addGap(24, 24, 24)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void fieldNomeProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNomeProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNomeProdActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        int codCat;
        int codSubCat;
        try {
            codCat = getCodCat();
            codSubCat = getCodSubCat();
            String query;
            query = "INSERT INTO produtos (nome_produto, cod_categoria, cod_subcategoria, quant_disp, preco) VALUES ('"+fieldNomeProd.getText()+"', '"+codCat+"', '"+codSubCat+"', '"+fieldQuant.getText()+"', '"+fieldPrice.getText()+"')";

            executeSQLQuery(query, "inseridos");
        } catch (SQLException ex) {
            Logger.getLogger(productsAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        products products = new products();
        DefaultTableModel model = (DefaultTableModel) products.tableProducts.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount -1; i >= 0; i--){
            model.removeRow(i);
        }
        products.show_product();
        this.dispose();
        
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void categoriaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaComboBoxActionPerformed
        try {
            subcategoriaComboBox.removeAllItems();
            getSubCategorias();
        } catch (SQLException ex) {
            Logger.getLogger(productsAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_categoriaComboBoxActionPerformed

    private void subcategoriaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subcategoriaComboBoxActionPerformed
        
    }//GEN-LAST:event_subcategoriaComboBoxActionPerformed

    private void fieldQuantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldQuantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldQuantActionPerformed

    private void fieldPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPriceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(productsAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(productsAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(productsAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(productsAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new productsAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonUpdate;
    public javax.swing.JComboBox<String> categoriaComboBox;
    public static javax.swing.JTextField fieldNomeProd;
    public static javax.swing.JTextField fieldPrice;
    public static javax.swing.JTextField fieldQuant;
    private javax.swing.JLabel labelArea;
    private javax.swing.JLabel labelCat;
    private javax.swing.JLabel labelMOpus;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPrice;
    private javax.swing.JLabel labelQuant;
    private javax.swing.JLabel labelSubCat;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox<String> subcategoriaComboBox;
    // End of variables declaration//GEN-END:variables

    
}
