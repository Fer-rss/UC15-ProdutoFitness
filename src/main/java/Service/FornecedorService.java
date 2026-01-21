package Service;

import Negocio.Fornecedor;
import persistencia.FornecedorDAO;
import java.util.List;

public class FornecedorService {

    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public void salvar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor não pode ser nulo.");
        }

        fornecedor.validar();

        // Regra de negócio: CNPJ único
        Fornecedor existente = fornecedorDAO.buscarPorCnpj(fornecedor.getCnpj());
        if (existente != null && !existente.getId().equals(fornecedor.getId())) {
            throw new IllegalStateException(
                "Já existe um fornecedor cadastrado com este CNPJ."
            );
        }

        fornecedorDAO.salvar(fornecedor);
    }

    public void excluir(Fornecedor fornecedor) {
        if (fornecedor == null || fornecedor.getId() == null) {
            throw new IllegalArgumentException("Fornecedor inválido.");
        }

        fornecedorDAO.excluir(fornecedor);
    }

    public Fornecedor buscar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return fornecedorDAO.buscarPorId(id);
    }
    
    public Fornecedor buscarPorNomeFornecedor(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return null;
        }

        List<Fornecedor> fornecedores =
            fornecedorDAO.buscarPorNomeFornecedor(nome.trim());

        return fornecedores.isEmpty() ? null : fornecedores.get(0);
    }

    public List<Fornecedor> listar() {
        return fornecedorDAO.listarTodos();
    }
    
    public void atualizar(Fornecedor fornecedor) {
        if (fornecedor == null || fornecedor.getId() == null) {
            throw new IllegalArgumentException("Fornecedor inválido.");
        }

        fornecedor.validar();

        // Regra de negócio: CNPJ único
        Fornecedor existente = fornecedorDAO.buscarPorCnpj(fornecedor.getCnpj());
        if (existente != null && !existente.getId().equals(fornecedor.getId())) {
            throw new IllegalStateException(
                "Já existe um fornecedor cadastrado com este CNPJ."
            );
        }

        fornecedorDAO.atualizar(fornecedor);
    }
}