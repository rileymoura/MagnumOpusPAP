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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static magnumopusms.mainform.idUser;
import static magnumopusms.mainform.userAtual;

/**
 *
 * @author a100519
 */
public class orders extends javax.swing.JFrame {

    /**
     * Creates new form orders
     */
    Connection con = null;
    public orders() {
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
        String query= "SELECT E.*, C.* FROM encomendas E, clientes C "
                +"WHERE E.cod_cliente = C.cod_cliente";
        try{
            ps = dbConnection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            Order order;
            while(rs.next()){
                order = new Order(rs.getInt("cod_encomenda"), rs.getString("nome_cliente"), "" , rs.getInt("preco_total"), rs.getString("data"), rs.getString("estado"));
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
        Object[] row = new Object[5];
        for(int i=0; i<list.size();i++){
            row[0]=list.get(i).getId();
            row[1]=list.get(i).getNome_cliente();
            row[2]=list.get(i).getValor();
            row[3]=list.get(i).getData();
            row[4]=list.get(i).getEstado();
           
            model.addRow(row);
        }
    }
    public void search (String str){
        DefaultTableModel model = (DefaultTableModel) tableOrders.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tableOrders.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + str));
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
        mainPanel = new javax.swing.JPanel();
        fieldId = new javax.swing.JTextField();
        buttonBack = new javax.swing.JButton();
        labelEncomendas = new javax.swing.JLabel();
        buttonAdd = new javax.swing.JButton();
        buttonProds = new javax.swing.JButton();
        labelSelectedId = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOrders = new javax.swing.JTable();
        labelEstado = new javax.swing.JLabel();
        buttonUpdate = new javax.swing.JButton();
        estadoComboBox = new javax.swing.JComboBox<>();
        fieldProcurar = new javax.swing.JTextField();
        labelMOpus2 = new javax.swing.JLabel();

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

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        fieldId.setEditable(false);
        fieldId.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        fieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldIdActionPerformed(evt);
            }
        });

        buttonBack.setBackground(new java.awt.Color(255, 204, 102));
        buttonBack.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        buttonBack.setText("VOLTAR");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        labelEncomendas.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelEncomendas.setText("Encomendas");

        buttonAdd.setBackground(new java.awt.Color(255, 204, 102));
        buttonAdd.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        buttonAdd.setText("ADICIONAR");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonProds.setBackground(new java.awt.Color(255, 204, 102));
        buttonProds.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        buttonProds.setText("VER PRODUTOS");
        buttonProds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProdsActionPerformed(evt);
            }
        });

        labelSelectedId.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelSelectedId.setText("ID:");

        tableOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome Cliente", "Valor", "Data", "Estado"
            }
        ));
        tableOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableOrdersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableOrders);

        labelEstado.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelEstado.setText("Estado:");

        buttonUpdate.setBackground(new java.awt.Color(255, 204, 102));
        buttonUpdate.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        buttonUpdate.setText("ATUALIZAR");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        estadoComboBox.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        estadoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Processamento", "Enviada", "Entregue", "Cancelada" }));

        fieldProcurar.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        fieldProcurar.setText("Pesquisar...");
        fieldProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldProcurarActionPerformed(evt);
            }
        });
        fieldProcurar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldProcurarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelEncomendas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fieldProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(buttonProds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(labelEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(estadoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(labelSelectedId)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fieldId))
                                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 18, Short.MAX_VALUE))))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSelectedId))
                        .addGap(13, 13, 13)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estadoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEstado))
                        .addGap(18, 18, 18)
                        .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonProds, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(fieldProcurar)
                        .addGap(490, 490, 490))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(labelEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBack)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        labelMOpus2.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        labelMOpus2.setForeground(new java.awt.Color(255, 255, 255));
        labelMOpus2.setText("Magnum Opus");

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMOpus2)
                .addGap(313, 313, 313)
                .addComponent(buttonExit)
                .addGap(23, 23, 23))
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(buttonExit))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelMOpus2)))
                .addGap(11, 11, 11)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
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
        System.exit(0);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM login WHERE id = ?";

        try{
            ps= dbConnection.getConnection().prepareStatement(query);

            ps.setInt(1, idUser);

            rs = ps.executeQuery();
            if(rs.next()){
                userAtual = (rs.getString("nome_func"));
                mainform mainform = new mainform();
                mainform.setVisible(true);
                mainform.pack();
                mainform.setLocationRelativeTo(null);
                mainform.welcomeLabel.setText("Bem-vindo, " +userAtual);
                this.dispose();
            }
        }catch(Exception e){}
    }//GEN-LAST:event_buttonBackActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        ordersAdd ordersAdd = new ordersAdd();
        ordersAdd.setVisible(true);
        ordersAdd.pack();
        ordersAdd.setLocationRelativeTo(null);
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonProdsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProdsActionPerformed
        ordersCliente clientesOrders = new ordersCliente();
        clientesOrders.setVisible(true);
        clientesOrders.pack();
        clientesOrders.setLocationRelativeTo(null);
    }//GEN-LAST:event_buttonProdsActionPerformed

    private void fieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldIdActionPerformed

    private void tableOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableOrdersMouseClicked
        int i = tableOrders.getSelectedRow();
        TableModel model = tableOrders.getModel();
        fieldId.setText(model.getValueAt(i,0).toString());
    }//GEN-LAST:event_tableOrdersMouseClicked

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        String id = fieldId.getText();
        String query = "UPDATE encomendas SET estado = '"+estadoComboBox.getSelectedItem()+"' WHERE cod_encomenda = '"+id+"'";

        executeSQLQuery(query, "atualizados");
        DefaultTableModel model = (DefaultTableModel) tableOrders.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount -1; i >= 0; i--){
            model.removeRow(i);
        }
        show_orders();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void fieldProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldProcurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldProcurarActionPerformed

    private void fieldProcurarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldProcurarKeyReleased
        String searchString = fieldProcurar.getText();
        search(searchString);
    }//GEN-LAST:event_fieldProcurarKeyReleased

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
            java.util.logging.Logger.getLogger(orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new orders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonProds;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox<String> estadoComboBox;
    public static javax.swing.JTextField fieldId;
    private javax.swing.JTextField fieldProcurar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEncomendas;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelMOpus2;
    private javax.swing.JLabel labelSelectedId;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable tableOrders;
    // End of variables declaration//GEN-END:variables
}
