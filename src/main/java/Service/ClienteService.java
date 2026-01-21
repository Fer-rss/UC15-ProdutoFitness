
package Service;

import Negocio.Cliente;
import persistencia.ClienteDAO;
import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void salvar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }

        cliente.validar();

        // Regra de negócio: CPF único
        Cliente existente = clienteDAO.buscarPorCpf(cliente.getCpf());
        if (existente != null && !existente.getId().equals(cliente.getId())) {
            throw new IllegalStateException(
                "Já existe um cliente cadastrado com este CPF."
            );
        }

        clienteDAO.salvar(cliente);
    }

    public void excluir(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("Cliente inválido.");
        }

        clienteDAO.excluir(cliente);
    }

    public void excluir(Long id) {
        Cliente p = clienteDAO.buscarPorId(id);
        if (p == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        clienteDAO.excluir(p);
    }
    
    public Cliente buscar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return clienteDAO.buscarPorId(id);
    }
    
    public List<Cliente> buscarPorNomeCliente(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return listar(); // retorna todos
        }

        return clienteDAO.buscarPorNomeCliente(nome.trim());
    }

    public void atualizar(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("Cliente inválido para atualização.");
        }

        cliente.validar();

        // Regra de negócio: CPF único
        Cliente existente = null;
        try {
            existente = clienteDAO.buscarPorCpf(cliente.getCpf());
        } catch (Exception e) {
            // Nenhum cliente com esse CPF — OK
        }

        if (existente != null && !existente.getId().equals(cliente.getId())) {
            throw new IllegalStateException(
                "Já existe outro cliente cadastrado com este CPF."
            );
        }

        clienteDAO.atualizar(cliente);
    }
    
    public List<Cliente> listar() {
        return clienteDAO.listarTodos();
    }
}

