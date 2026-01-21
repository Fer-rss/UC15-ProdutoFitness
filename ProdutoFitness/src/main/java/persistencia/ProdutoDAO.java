package persistencia;

import Negocio.Produto;
import java.util.List;

public class ProdutoDAO extends GenericDAO<Produto> {
    public ProdutoDAO() {
        super(Produto.class);
    }
    public List<Produto> buscarPorNomeProduto(String nome) {
        return getEM().createQuery(
                    "SELECT p FROM Produto p WHERE LOWER(p.nomeProduto) LIKE LOWER(:nome)",
                    Produto.class
                )
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}

