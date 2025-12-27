package Service;

import Negocio.Venda;
import persistencia.VendaDAO;

public class VendaService {

    private final VendaDAO vendaDAO = new VendaDAO();

    public void salvar(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("Venda não pode ser nula.");
        }

        venda.validar();

        vendaDAO.salvar(venda);
    }

    public Venda buscar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return vendaDAO.buscarPorId(id);
    }

    public void excluir(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("Venda inválida.");
        }
        vendaDAO.excluir(venda);
    }

    public java.util.List<Venda> listar() {
        return vendaDAO.listarTodos();
    }
}

