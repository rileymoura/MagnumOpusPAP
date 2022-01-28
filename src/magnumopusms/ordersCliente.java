/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magnumopusms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author xdmah
 */
public class ordersCliente extends javax.swing.JFrame {

    /**
     * Creates new form clientesOrders
     */
    Connection con = null;
    public ordersCliente() {
        initComponents();
        show_orders();
        con = dbConnection.getConnection();
    }
    public void executeSQLQuery(String query, String message){
        Statement st;
        try{
            st = con.createStatement();
            if(st.executeUpdate(query) == 1){
                JOptionPane.showMessageDialog(null, "Dados "+message+" com sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "Dados não "+message);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public ArrayList<Order> orderList(){
        ArrayList<Order> ordersList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String query;
        query = "SELECT EP.*, P.* FROM encomendas_produtos EP, produtos P "
                + "WHERE EP.cod_encomenda = "+orders.fieldId.getText()+" "
                + "AND P.cod_produto = EP.cod_produto";
        try{
            ps = dbConnection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            Order order;
            while(rs.next()){
                order = new Order(rs.getInt("cod_encomenda"), "" , rs.getString("nome_produto") , rs.getFloat("preco_prods"), rs.getString("quant"), rs.getString("cod_encprod"));
                ordersList.add(order);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return ordersList;
    }
    public void show_orders(){
        ArrayList<Order> list = orderList();
        DefaultTableModel model = (DefaultTableModel)tableOrders.getModel();
        Object[] row = new Object[3];
        for(int i=0; i<list.size();i++){
            row[0]=list.get(i).getProdutos();
            row[1]=list.get(i).getValor();
            row[2]=list.get(i).getData();
           
            model.addRow(row);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        buttonExit = new javax.swing.JButton();
        labelMOpus = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        labelMOpus1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOrders = new javax.swing.JTable();

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

        labelMOpus1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelMOpus1.setText("Ver Produtos");

        tableOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome Produto", "Valor", "Quantidade"
            }
        ));
        jScrollPane1.setViewportView(tableOrders);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelMOpus1))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMOpus1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMOpus)
                .addGap(112, 112, 112)
                .addComponent(buttonExit)
                .addGap(23, 23, 23))
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonExit)
                    .addComponent(labelMOpus))
                .addGap(17, 17, 17)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonExitActionPerformed

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
            java.util.logging.Logger.getLogger(ordersCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ordersCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ordersCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ordersCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ordersCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton buttonExit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelMOpus;
    private javax.swing.JLabel labelMOpus1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable tableOrders;
    // End of variables declaration//GEN-END:variables
}
