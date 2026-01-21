package Negocio;

/** @author Fernando */

import jakarta.persistence.*;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fornecedor", nullable = false, length = 100)
    private String nomeFornecedor;

    @Column(name = "cnpj", nullable = false, unique = true, length = 20)
    private String cnpj;

    @Column(name = "prazo_entrega", length = 50)
    private String prazoEntrega;

    // Construtor
    public Fornecedor() {
    }

    // Setters
    public void setId(Long id) { this.id = id; }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = (nomeFornecedor == null ? null : nomeFornecedor.trim());
    }

    public void setCnpj(String cnpj) {
        this.cnpj = (cnpj == null ? null : cnpj.replaceAll("[^0-9]", ""));
    }

    public void setPrazoEntrega(String prazoEntrega) {
        this.prazoEntrega = (prazoEntrega == null || prazoEntrega.isBlank())
                ? null
                : prazoEntrega.trim();
    }

    // ----- Getters -----
    public Long getId() {
        return id; }

    public String getNomeFornecedor() {
        return nomeFornecedor;  }

    public String getCnpj() {
        return cnpj;   }

    public String getPrazoEntrega() {
        return prazoEntrega;   }

    // --------- Validação ---------
    public void validar() {
        if (nomeFornecedor == null || nomeFornecedor.isBlank()) {
            throw new IllegalArgumentException("O nome do fornecedor é obrigatório.");
        }

        if (cnpj == null || cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido. Deve conter 14 dígitos.");
        }
    }

    @Override
    public String toString() {
        return nomeFornecedor;
    }
}
