package Negocio;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**@author Fernando */

@Entity
@Table(name = "venda")
public class Venda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda = LocalDateTime.now();

    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "venda",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();

    // Construtor
    public Venda() {}

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }
    
    // MÉTODOS DE DOMÍNIO
    public void adicionarItem(ItemVenda item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        item.validar();    
        item.setVenda(this);
        itens.add(item);
        recalcularTotal();
    }

    public void removerItem(ItemVenda item) {
        itens.remove(item);
        recalcularTotal();
    }

    public void recalcularTotal() {
        total = itens.stream()
                .map(ItemVenda::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Validação 
    public void validar() {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente é obrigatório.");
        }
        if (itens.isEmpty()) {
            throw new IllegalStateException("A venda deve conter ao menos um item.");
        }
        if (dataVenda == null) {
            throw new IllegalStateException("A data da venda não pode ser nula.");
        }
    }
    
    @Override
    public String toString() {
        return "Venda ID: " + id +
                ", Cliente: " + cliente.getNomeCliente() +
                ", Total: R$" + total;
    }
}
