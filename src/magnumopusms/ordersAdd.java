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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author a100519
 */
public class ordersAdd extends javax.swing.JFrame {

    /**
     * Creates new form ordersAdd
     */
    Connection con = null;
    public ordersAdd() {
        
        initComponents();
        show_client();
        show_product();
        con = dbConnection.getConnection();
    }
    public void executeSQLQuery(String query, String message){
        Connection con = dbConnection.getConnection();
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
    public void executeSQLQueryNoMessage(String query){
        Connection con = dbConnection.getConnection();
        Statement st;
        try{
            st = con.createStatement();
            if(st.executeUpdate(query) == 1){
                
            }else{
                JOptionPane.showMessageDialog(null, "Erro! (executeSQLQueryNoMessage)");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public ArrayList<Client> clientList(){
        ArrayList<Client> clientsList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String query="SELECT * FROM clientes";
        try{
            ps = dbConnection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            Client client;
            while(rs.next()){
                client = new Client(rs.getInt("cod_cliente"), rs.getString("nome_cliente"), rs.getString("morada"), rs.getString("cod_postal"), rs.getString("localidade"), rs.getString("cidade"), rs.getInt("num_tel"));
                clientsList.add(client);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return clientsList;
    }
    public void show_client(){
        ArrayList<Client> list = clientList();
        DefaultTableModel model = (DefaultTableModel)tableClients.getModel();
        Object[] row = new Object[2];
        for(int i=0; i<list.size();i++){
            row[0]=list.get(i).getId();
            row[1]=list.get(i).getNome_client();
           
            model.addRow(row);
        }
    }
    public ArrayList<Product> productsList(){
        ArrayList<Product> productsList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String query="SELECT * FROM produtos, categorias, sub_categorias"
                + " WHERE produtos.cod_categoria = categorias.cod_categoria"
                + " AND produtos.cod_subcategoria = sub_categorias.cod_subcategoria";
        try{
            ps = dbConnection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            Product product;
            while(rs.next()){                
            product = new Product(rs.getInt("cod_produto"), rs.getString("nome_produto"), rs.getString("nome_categoria"), rs.getString("nome_subcategoria"), rs.getInt("quant_disp"), rs.getFloat("preco"), rs.getFloat("preco_civa"), rs.getFloat("valor_iva"));                
            productsList.add(product);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return productsList;
    }
    public void show_product(){
        ArrayList<Product> list = productsList();
        DefaultTableModel model = (DefaultTableModel)tableProducts.getModel();
        Object[] row = new Object[8];
        for(int i=0; i<list.size();i++){
            row[0]=list.get(i).getId();
            row[1]=list.get(i).getNome_produto();
            row[2]=list.get(i).getNome_categoria();
            row[3]=list.get(i).getNome_subcategoria();
            row[4]=list.get(i).getQuant_disp();
            row[5]=list.get(i).getPreco();
            row[6]=list.get(i).getIVA();
            row[7]=list.get(i).getPrecoTotal();

           
            model.addRow(row);
        }}
     public void search_products (String str){
        DefaultTableModel model = (DefaultTableModel) tableProducts.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tableProducts.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter("(?i)" + str));
    }
     public void search_clients(String str){
        DefaultTableModel model = (DefaultTableModel) tableClients.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tableClients.setRowSorter(trs);
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
        buttonBack = new javax.swing.JButton();
        labelEncomendas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClients = new javax.swing.JTable();
        labelEncomendas1 = new javax.swing.JLabel();
        labelEncomendas2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProducts = new javax.swing.JTable();
        buttonAddProd = new javax.swing.JButton();
        buttonRemoveProd = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProductsS = new javax.swing.JTable();
        labelEncomendas3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        fieldIdClient = new javax.swing.JTextField();
        labelSelectedId = new javax.swing.JLabel();
        fieldIdOrder = new javax.swing.JTextField();
        labelSelectedId1 = new javax.swing.JLabel();
        buttonNewOrder = new javax.swing.JButton();
        fieldProcurar = new javax.swing.JTextField();
        fieldProcurar1 = new javax.swing.JTextField();
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

        buttonBack.setBackground(new java.awt.Color(255, 204, 102));
        buttonBack.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        buttonBack.setText("VOLTAR");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        labelEncomendas.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelEncomendas.setText("Selecionar Cliente");

        tableClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome Cliente"
            }
        ));
        tableClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableClients);

        labelEncomendas1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelEncomendas1.setText("Nova Encomenda");

        labelEncomendas2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelEncomendas2.setText("Produtos Selecionados");

        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Categoria", "SubCategoria", "Quantidade", "Preço S/IVA", "IVA", "Preço"
            }
        ));
        tableProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableProducts);

        buttonAddProd.setBackground(new java.awt.Color(255, 204, 102));
        buttonAddProd.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        buttonAddProd.setText(">>>");
        buttonAddProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddProdActionPerformed(evt);
            }
        });

        buttonRemoveProd.setBackground(new java.awt.Color(255, 204, 102));
        buttonRemoveProd.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        buttonRemoveProd.setText("<<<");

        tableProductsS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Categoria", "SubCategoria", "Quantidade", "Preço S/IVA", "IVA", "Preço"
            }
        ));
        tableProductsS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductsSMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProductsS);

        labelEncomendas3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelEncomendas3.setText("Selecionar Produtos");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Quantidade");

        fieldIdClient.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        fieldIdClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldIdClientActionPerformed(evt);
            }
        });

        labelSelectedId.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelSelectedId.setText("ID Cliente:");

        fieldIdOrder.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        fieldIdOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldIdOrderActionPerformed(evt);
            }
        });

        labelSelectedId1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        labelSelectedId1.setText("ID Encomenda:");

        buttonNewOrder.setBackground(new java.awt.Color(255, 204, 102));
        buttonNewOrder.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        buttonNewOrder.setText("NOVA ENCOMENDA");
        buttonNewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewOrderActionPerformed(evt);
            }
        });

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

        fieldProcurar1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        fieldProcurar1.setText("Pesquisar...");
        fieldProcurar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldProcurar1ActionPerformed(evt);
            }
        });
        fieldProcurar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldProcurar1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonBack)
                            .addComponent(labelEncomendas1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(labelEncomendas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonNewOrder))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addComponent(labelEncomendas3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fieldProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addComponent(labelEncomendas2)
                                        .addGap(346, 346, 346))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelSelectedId)
                                            .addComponent(labelSelectedId1))
                                        .addGap(18, 18, 18)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fieldIdOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fieldIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fieldProcurar1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonAddProd)
                                            .addComponent(buttonRemoveProd))
                                        .addGap(71, 71, 71)))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonNewOrder))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(buttonAddProd)
                                .addGap(28, 28, 28)
                                .addComponent(buttonRemoveProd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(labelEncomendas3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fieldProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSelectedId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldIdOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSelectedId1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEncomendas2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldProcurar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(labelEncomendas1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonBack)
                .addContainerGap())
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
                .addGap(483, 483, 483)
                .addComponent(buttonExit)
                .addContainerGap())
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonExit)
                    .addComponent(labelMOpus2))
                .addGap(10, 10, 10)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
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
        orders orders = new orders();
        orders.setVisible(true);
        orders.setLocationRelativeTo(null);
        orders.pack();
        this.dispose();
    }//GEN-LAST:event_buttonBackActionPerformed

    private void tableClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientsMouseClicked
        int i = tableClients.getSelectedRow();
        TableModel model = tableClients.getModel();
        fieldIdClient.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_tableClientsMouseClicked

    private void tableProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductsMouseClicked

    }//GEN-LAST:event_tableProductsMouseClicked

    private void tableProductsSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductsSMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableProductsSMouseClicked

    private void buttonAddProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddProdActionPerformed
        int i = tableProducts.getSelectedRow();
        TableModel model = tableProducts.getModel();
        int id;
    }//GEN-LAST:event_buttonAddProdActionPerformed

    private void fieldIdClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldIdClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldIdClientActionPerformed

    private void fieldIdOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldIdOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldIdOrderActionPerformed

    private void buttonNewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewOrderActionPerformed
        try {
            String query;
            String id = fieldIdClient.getText();
            query = "INSERT INTO encomendas (cod_cliente) VALUES ("+id+")";
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            
            ResultSet rs =  ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()){
                generatedKey = rs.getInt(1);
            }
            fieldIdOrder.setText(String.valueOf(generatedKey));
            JOptionPane.showMessageDialog(null, "Encomenda criada");
        } catch (SQLException ex) {
            Logger.getLogger(ordersAdd.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_buttonNewOrderActionPerformed

    private void fieldProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldProcurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldProcurarActionPerformed

    private void fieldProcurarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldProcurarKeyReleased
        String searchString = fieldProcurar.getText();
        search_clients(searchString);
    }//GEN-LAST:event_fieldProcurarKeyReleased

    private void fieldProcurar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldProcurar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldProcurar1ActionPerformed

    private void fieldProcurar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldProcurar1KeyReleased
        String searchString = fieldProcurar.getText();
        search_products(searchString);
    }//GEN-LAST:event_fieldProcurar1KeyReleased

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
            java.util.logging.Logger.getLogger(ordersAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ordersAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ordersAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ordersAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ordersAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton buttonAddProd;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonNewOrder;
    private javax.swing.JButton buttonRemoveProd;
    public static javax.swing.JTextField fieldIdClient;
    public static javax.swing.JTextField fieldIdOrder;
    private javax.swing.JTextField fieldProcurar;
    private javax.swing.JTextField fieldProcurar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel labelEncomendas;
    private javax.swing.JLabel labelEncomendas1;
    private javax.swing.JLabel labelEncomendas2;
    private javax.swing.JLabel labelEncomendas3;
    private javax.swing.JLabel labelMOpus2;
    private javax.swing.JLabel labelSelectedId;
    private javax.swing.JLabel labelSelectedId1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable tableClients;
    public javax.swing.JTable tableProducts;
    public javax.swing.JTable tableProductsS;
    // End of variables declaration//GEN-END:variables
}
