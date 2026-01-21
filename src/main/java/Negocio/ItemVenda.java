package Negocio;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**@author Fernando */

@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private int quantidade;

    @Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;
    
    // Construtor
    public ItemVenda() {}

    public ItemVenda(Produto produto, int quantidade, BigDecimal precoUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
    
    // Setters e regras de validação
    public void setId(Long id) {
        this.id = id;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setVenda(Venda venda) {    
        this.venda = venda;
    }
   
    // Getters
    public Long getId() {
        return id; }

    public Produto getProduto() {
        return produto; }

    public int getQuantidade() {
        return quantidade; }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario; }

    public Venda getVenda() {
        return venda; }
    
    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade)); }

    // --------- Validação ---------
    public void validar() {
        if (produto == null) {
            throw new IllegalArgumentException("O produto é obrigatório.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }
    }
    
    public BigDecimal calcularSubtotal() {
        if (produto == null || produto.getPreco() == null) {
            throw new IllegalStateException("Produto inválido.");
        }
        if (quantidade <= 0) {
            throw new IllegalStateException("Quantidade inválida.");
        }

        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }
    
    @Override
    public String toString() {
        return produto + " - " + quantidade + " x R$" + precoUnitario;
    }
}