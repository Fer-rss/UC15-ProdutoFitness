package persistencia;

import Negocio.Fornecedor;
import java.util.List;

public class FornecedorDAO extends GenericDAO<Fornecedor> {
    public FornecedorDAO() {
        super(Fornecedor.class);
    }
    public Fornecedor buscarPorCnpj(String cnpj) {
        return getEM().createQuery(
                "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj", Fornecedor.class)
                .setParameter("cnpj", cnpj)
                .getSingleResult();
    }
    public List<Fornecedor> buscarPorNomeFornecedor(String nome) {
        return getEM().createQuery(
                    "SELECT f FROM Fornecedor f WHERE LOWER(f.nomeFornecedor) LIKE LOWER(:nome)",
                    Fornecedor.class
                )
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}


