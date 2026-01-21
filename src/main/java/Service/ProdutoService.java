package Service;

import Negocio.Produto;
import persistencia.ProdutoDAO;
import java.util.List;

public class ProdutoService {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public void salvar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        produto.validar();

        produtoDAO.salvar(produto);
    }

    public void atualizar(Produto produto) {
        if (produto == null || produto.getId() == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }

        produto.validar();
        produtoDAO.salvar(produto);
    }
    
    public void excluir(Produto produto) {
        if (produto == null || produto.getId() == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }

        produtoDAO.excluir(produto);
    }

    public void excluir(Long id) {
        Produto p = produtoDAO.buscarPorId(id);
        if (p == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        produtoDAO.excluir(p);
    }
    
    public Produto buscar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return produtoDAO.buscarPorId(id);
    }
    
    public List<Produto> buscarPorNomeProduto(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return listar(); 
        }

        return produtoDAO.buscarPorNomeProduto(nome.trim());
    }
    
    public List<Produto> listar() {
        return produtoDAO.listarTodos();
    }
}