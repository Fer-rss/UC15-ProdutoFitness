package Gui;

import Negocio.Cliente;
import Negocio.ItemVenda;
import Negocio.Produto;
import Negocio.Venda;
import Service.ClienteService;
import Service.ProdutoService;
import Service.VendaService;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JList;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferna
 */
public class TelaCadastroVenda extends javax.swing.JFrame {
        private DefaultTableModel modeloTabela;
        private Venda vendaAtual;
        private final VendaService vendaService = new VendaService();
        private Cliente clienteSelecionado;
        private Produto produtoSelecionado;
        private final ClienteService clienteService = new ClienteService();
        private final ProdutoService produtoService = new ProdutoService();
        private JPopupMenu popupProduto = new JPopupMenu();
        private JList<Produto> listaProdutos = new JList<>();
        private JPopupMenu popupCliente = new JPopupMenu();
        private JList<Cliente> listaClientes = new JList<>();
              
    /**
     * Creates new form TelaCadastroVenda
     */
    public TelaCadastroVenda() {
        initComponents();
        modeloTabela = (DefaultTableModel) tblVenda.getModel();
        vendaAtual = new Venda();
        configurarAutoCompleteCliente();
        configurarAutoCompleteProduto();

        try {
            NumberFormat inteiro = NumberFormat.getIntegerInstance();
            inteiro.setGroupingUsed(false);

            NumberFormatter inteiroFormatter = new NumberFormatter(inteiro);
            inteiroFormatter.setValueClass(Integer.class);
            inteiroFormatter.setMinimum(0);
            inteiroFormatter.setAllowsInvalid(false);

            txtQuantidade.setFormatterFactory(new DefaultFormatterFactory(inteiroFormatter));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void atualizarTotalTela() {
        txtTotalVenda.setText("R$ " + vendaAtual.getTotal());
    }

    private void mostrarPopupClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            popupCliente.setVisible(false);
            return;
        }

        listaClientes.setListData(clientes.toArray(new Cliente[0]));
        popupCliente.removeAll();
        popupCliente.add(new JScrollPane(listaClientes));

        popupCliente.show(txtCliente, 0, txtCliente.getHeight());
    }

    private void configurarAutoCompleteCliente() {

        txtCliente.getDocument().addDocumentListener(new DocumentListener() {

            private void buscar() {
                String texto = txtCliente.getText();

                if (texto.length() < 2) {
                    popupCliente.setVisible(false);
                    return;
                }

                List<Cliente> clientes
                = clienteService.buscarPorNomeCliente(texto);

                mostrarPopupClientes(clientes);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        });

        listaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                clienteSelecionado = listaClientes.getSelectedValue();
                txtCliente.setText(clienteSelecionado.getNomeCliente());
                popupCliente.setVisible(false);
            }
        });
    }

    private void configurarAutoCompleteProduto() {

        txtProduto.getDocument().addDocumentListener(new DocumentListener() {

            private void buscar() {
                String texto = txtProduto.getText();

                if (texto.length() < 2) {
                    popupProduto.setVisible(false);
                    return;
                }

                List<Produto> produtos
                        = produtoService.buscarPorNomeProduto(texto);

                if (produtos.isEmpty()) {
                    popupProduto.setVisible(false);
                    return;
                }

                listaProdutos.setListData(produtos.toArray(new Produto[0]));
                popupProduto.removeAll();
                popupProduto.add(new JScrollPane(listaProdutos));
                popupProduto.show(txtProduto, 0, txtProduto.getHeight());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        });

        listaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                produtoSelecionado = listaProdutos.getSelectedValue();
                txtProduto.setText(produtoSelecionado.getNomeProduto());
                txtPreco.setText(produtoSelecionado.getPreco().toString());
                popupProduto.setVisible(false);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVenda = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btAdicionar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JFormattedTextField();
        txtQuantidade = new javax.swing.JFormattedTextField();
        txtProduto = new javax.swing.JTextField();
        btFinalizar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTotalVenda = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tblVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade", "Preço Unitário", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tblVenda);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Tela de Venda");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel2.setText("Cliente:");

        btAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        btExcluir.setBackground(new java.awt.Color(153, 0, 0));
        btExcluir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel3.setText("Produto:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel4.setText("Quantidade:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel5.setText("Preço:");

        btFinalizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btFinalizar.setText("Finalizar");
        btFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinalizarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel6.setText("Total da Venda");

        txtTotalVenda.setEditable(false);
        txtTotalVenda.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(221, 221, 221))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btFinalizar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPreco)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(txtProduto))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAdicionar)
                            .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalVenda))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btFinalizar))
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btExcluir)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btAdicionar)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        try {
            if (produtoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um produto válido.");
                return;
            }
        int quantidade = Integer.parseInt(txtQuantidade.getText());

        ItemVenda item = new ItemVenda(produtoSelecionado, quantidade, produtoSelecionado.getPreco());
        vendaAtual.adicionarItem(item);


        modeloTabela.addRow(new Object[]{
            produtoSelecionado.getNomeProduto(),
            quantidade,
            produtoSelecionado.getPreco(),
            item.getSubtotal()
        });

        atualizarTotalTela();
        limparCamposProduto();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        int linha = tblVenda.getSelectedRow();

        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um item.");
            return;
        }

        ItemVenda item = vendaAtual.getItens().get(linha);
        vendaAtual.removerItem(item);
        modeloTabela.removeRow(linha);

        atualizarTotalTela();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinalizarActionPerformed
        try {
        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente válido.");
            return;
            }

            vendaAtual.setCliente(clienteSelecionado);
            vendaService.salvar(vendaAtual);

        JOptionPane.showMessageDialog(this,
            "Venda salva com sucesso! Total: R$ " + vendaAtual.getTotal()
        );

        limparTela();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_btFinalizarActionPerformed

    private void limparTela() {
        vendaAtual = new Venda();
        modeloTabela.setRowCount(0);
        txtTotalVenda.setText("R$ 0,00");
        txtCliente.setText("");
        txtPreco.setText("");
    }
    
    private void limparCamposProduto(){
        txtProduto.setText("");
        txtQuantidade.setText("");
        txtPreco.setText("");
    }
       
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
            java.util.logging.Logger.getLogger(TelaCadastroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btFinalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVenda;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JFormattedTextField txtPreco;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JFormattedTextField txtQuantidade;
    private javax.swing.JTextField txtTotalVenda;
    // End of variables declaration//GEN-END:variables
}
