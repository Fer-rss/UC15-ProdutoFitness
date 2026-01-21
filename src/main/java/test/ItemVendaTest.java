package test;

import Negocio.ItemVenda;
import Negocio.Produto;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemVendaTest {

    @Test
    public void deveCalcularSubtotalCorretamente() {
        Produto produto = new Produto();
        produto.setPreco(new BigDecimal("10.00"));

        ItemVenda item = new ItemVenda(produto, 3, produto.getPreco());

        BigDecimal subtotal = item.calcularSubtotal();

        assertEquals(new BigDecimal("30.00"), subtotal);
    }

    @Test
    public void naoDevePermitirQuantidadeZero() {
        Produto produto = new Produto();
        produto.setPreco(new BigDecimal("10.00"));

        ItemVenda item = new ItemVenda(produto, 0, produto.getPreco());

        assertThrows(IllegalStateException.class, item::calcularSubtotal);
    }
}
