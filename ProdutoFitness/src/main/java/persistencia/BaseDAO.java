package persistencia;

import java.util.List;

public interface BaseDAO<T> {

    void salvar(T entidade);

    T buscarPorId(Long id);

    List<T> listarTodos();
    
    void atualizar(T entidade);

    void excluir(T entidade);
}