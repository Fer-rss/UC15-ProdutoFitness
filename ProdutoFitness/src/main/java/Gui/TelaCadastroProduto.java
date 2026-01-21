
package Gui;

import Negocio.Fornecedor;
import java.math.BigDecimal;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import Negocio.Produto;
import Service.ProdutoService;
import Service.FornecedorService;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 *
 * @author ferna
 */
public class TelaCadastroProduto extends javax.swing.JFrame {
        private DefaultTableModel modeloTabela;
        private ProdutoService produtoService = new ProdutoService();
        private Produto produtoSelecionado;
        private Fornecedor fornecedorSelecionado;
        private FornecedorService fornecedorService = new FornecedorService();
        
    /**
     * Creates new form TelaCadastroProduto
     */
    public TelaCadastroProduto() {
        initComponents();
        configurarTabela();
        atualizarTabela();
        setupAutoCompleteFornecedor();
        
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setAllowsInvalid(false);
        txtPreco.setFormatterFactory(new DefaultFormatterFactory(numberFormatter));

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

        @Override
            public void setVisible(boolean b) {
                if (b) {
                    try {
                        atualizarTabela();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(
                            this,
                            "Erro ao carregar produtos:\n" + e.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
                super.setVisible(b);
            }
            
        private void setupAutoCompleteFornecedor() {
            // Carrega todos os fornecedores do banco
            java.util.List<Fornecedor> fornecedores = fornecedorService.listar();

            JTextField txt = txtFornecedor;

            JPopupMenu popup = new JPopupMenu();

            txt.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                private void updateList() {
                    popup.removeAll();
                    String texto = txt.getText().trim().toLowerCase();
                    if (texto.isEmpty()) {
                        popup.setVisible(false);
                        return;
                    }

                    boolean temItem = false;

                    for (Fornecedor f : fornecedores) {
                        if (f.getNomeFornecedor().toLowerCase().contains(texto)) {
                            JMenuItem item = new JMenuItem(f.getNomeFornecedor());
                            item.addActionListener(e -> {
                                txt.setText(f.getNomeFornecedor());
                                fornecedorSelecionado = f; // seleciona corretamente
                                popup.setVisible(false);
                            });
                            popup.add(item);
                            temItem = true;
                        }
                    }

                    if (temItem) {
                        popup.show(txt, 0, txt.getHeight());
                    } else {
                        popup.setVisible(false);
                    }
                }

                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    updateList();
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    updateList();
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    updateList();
                }
            });

            txt.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    // opcional: valida se o texto é um fornecedor válido
                    String nome = txt.getText().trim();
                    fornecedorSelecionado = fornecedores.stream()
                            .filter(f -> f.getNomeFornecedor().equals(nome))
                            .findFirst()
                            .orElse(null);
                }
            });
        }
        
        private void configurarTabela() {
            modeloTabela = (DefaultTableModel) tblProduto.getModel();
            modeloTabela.setRowCount(0);
        }
        
        private void atualizarTabela() {
            modeloTabela.setRowCount(0);

            for (Produto p : produtoService.listar()) {
                modeloTabela.addRow(new Object[]{
                    p, // OBJETO
                    p.getFornecedor().getNomeFornecedor(),
                    p.getPreco(),
                    p.getQtddEstoque(),
                    p.getCategoria(),
                    p.getObservacao()
                });
            }
        }
                
        private void limparCampos() {
            txtNome.setText("");
            txtFornecedor.setText("");
            txtPreco.setValue(null);
            txtQuantidade.setValue(null);
            txtCategoria.setText("");
            txtObservacao.setText("");
        }
    
        private Produto lerProdutoDaTela() {
            if (fornecedorSelecionado == null) {
            throw new IllegalArgumentException("Selecione um fornecedor válido.");
            }

                Produto p = new Produto();
                p.setNomeProduto(txtNome.getText().trim());
                p.setFornecedor(fornecedorSelecionado);
                p.setPreco(new BigDecimal(((Number) txtPreco.getValue()).doubleValue()));
                p.setQtddEstoque(((Number) txtQuantidade.getValue()).intValue());
                p.setCategoria(txtCategoria.getText().trim());
                p.setObservacao(txtObservacao.getText().trim());

                return p;
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
        tblProduto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtFornecedor = new javax.swing.JTextField();
        txtPreco = new javax.swing.JFormattedTextField();
        txtCategoria = new javax.swing.JTextField();
        txtObservacao = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblProduto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Fornecedor", "Preço", "Quantidade", "Categoria", "Observação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Cadastro de Produto");

        btAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        btAlterar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel3.setText("Fornecedor:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel4.setText("Preço:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel5.setText("Quantidade:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel6.setText("Categoria:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel7.setText("Observação:");

        txtFornecedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFornecedorFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNome)
                    .addComponent(txtFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(txtPreco)
                    .addComponent(txtCategoria)
                    .addComponent(txtObservacao)
                    .addComponent(txtQuantidade))
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btAdicionar)
                        .addGap(18, 18, 18)
                        .addComponent(btAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btExcluir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            Produto p = lerProdutoDaTela();
            produtoService.salvar(p);

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            
            // Atualizar a tabela
            atualizarTabela();

            // Limpar os campos
            limparCampos(); 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        if (produtoSelecionado == null) {
        JOptionPane.showMessageDialog(this, "Selecione um produto na tabela.");
        return;
        }

        try {
            Produto produto = lerProdutoDaTela();
            produto.setId(produtoSelecionado.getId());
            produtoService.atualizar(produto);

            JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!");
            atualizarTabela();
            limparCampos();
            produtoSelecionado = null;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }        
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        if (produtoSelecionado == null) {
        JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
        return;
        }

        int resp = JOptionPane.showConfirmDialog(
            this,
            "Deseja excluir o produto?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if (resp == JOptionPane.YES_OPTION) {
            produtoService.excluir(produtoSelecionado.getId());
            atualizarTabela();
            limparCampos();
            produtoSelecionado = null;
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        int linha = tblProduto.getSelectedRow();

        if (linha >= 0) {
            produtoSelecionado = (Produto) tblProduto.getValueAt(linha, 0);
            fornecedorSelecionado = produtoSelecionado.getFornecedor();
            
            txtNome.setText(produtoSelecionado.getNomeProduto());
            txtFornecedor.setText(produtoSelecionado.getFornecedor().getNomeFornecedor());
            txtPreco.setValue(produtoSelecionado.getPreco());
            txtQuantidade.setValue(produtoSelecionado.getQtddEstoque());
            txtCategoria.setText(produtoSelecionado.getCategoria());
            txtObservacao.setText(produtoSelecionado.getObservacao());
        }
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void txtFornecedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFornecedorFocusLost
       String nome = txtFornecedor.getText().trim();

        if (nome.isEmpty()) {
            fornecedorSelecionado = null;
            return;
        }

        try {
            fornecedorSelecionado =
                fornecedorService.buscarPorNomeFornecedor(nome);

            if (fornecedorSelecionado == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Fornecedor não encontrado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_txtFornecedorFocusLost

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
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtFornecedor;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtObservacao;
    private javax.swing.JFormattedTextField txtPreco;
    private javax.swing.JFormattedTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
