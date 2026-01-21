package Negocio;

import jakarta.persistence.*;

/**@author Fernando */
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cliente", nullable = false, length = 100)
    private String nomeCliente;

    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;

    @Column(name = "endereco", length = 200)
    private String endereco;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    // Construtor
    public Cliente() {}

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = (nomeCliente == null ? null : nomeCliente.trim());
    }

    public void setCpf(String cpf) {
        this.cpf = (cpf == null ? null : cpf.replaceAll("[^0-9]", ""));
    }

    public void setEndereco(String endereco) {
        this.endereco = (endereco == null ? null : endereco.trim());
    }

    public void setEmail(String email) {
        this.email = (email == null ? null : email.trim());
    }

    public void setTelefone(String telefone) {
        this.telefone = (telefone == null ? null : telefone.replaceAll("[^0-9]", ""));
    }
        
    // Getters
    public Long getId() { 
        return id; }
    
    public String getNomeCliente() { 
        return nomeCliente; }
    
    public String getCpf() { 
        return cpf; }
    
    public String getEndereco() { 
        return endereco; }
    
    public String getEmail() { 
        return email; }
    
    public String getTelefone() { 
        return telefone; }

    // --------- Validação ---------
    public void validar() {
        if (nomeCliente == null || nomeCliente.isBlank()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos.");
        }
        if (email != null && !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }
    
    @Override
    public String toString() {
        return nomeCliente;
    }
}
