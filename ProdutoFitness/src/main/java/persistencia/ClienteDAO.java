package persistencia;

import Negocio.Cliente;
import java.util.List;

public class ClienteDAO extends GenericDAO<Cliente> {
    
    public ClienteDAO() {
        super(Cliente.class);
    }
    
    public Cliente buscarPorCpf(String cpf) {
        try {
            return getEM().createQuery(
                    "SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
    
    public List<Cliente> buscarPorNomeCliente(String nome) {
        return getEM().createQuery(
                    "SELECT c FROM Cliente c WHERE LOWER(c.nomeCliente) LIKE LOWER(:nome)",
                    Cliente.class
                )
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}


