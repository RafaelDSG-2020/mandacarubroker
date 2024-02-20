package com.mandacarubroker.domain.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockTest {

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock("1", "TEST", "Test Company", 100.0);
    }

    @Test
    void testChangePriceIncrease() {
        double newPrice = stock.changePrice(150.0);
        assertEquals(250.0, newPrice, "O preço deve aumentar corretamente.");
    }

    @Test
    void testChangePriceDecrease() {
        double newPrice = stock.changePrice(-50.0);
        assertEquals(50.0, newPrice, "O preço deve diminuir corretamente.");
    }

    @Test
    void testConstructorWithRequestStockDTO() {
        RequestStockDTO requestStockDto = new RequestStockDTO("NEW1", "New Company", 200.0);
        Stock newStock = new Stock(requestStockDto);
        assertEquals("NEW1", newStock.getSymbol(), "O símbolo deve ser definido corretamente pelo DTO.");
        assertEquals("New Company", newStock.getCompanyName(), "O nome da empresa deve ser definido corretamente pelo DTO.");
        assertEquals(200.0, newStock.getPrice(), "O preço deve ser definido corretamente pelo DTO.");
    }

    @Test
    void decreasePriceReducesPriceCorrectly() {
        // Chama decreasePrice com um valor para diminuir
        double newPrice = stock.decreasePrice(20.0);

        // Verifica se o preço foi reduzido corretamente
        assertEquals(80.0, newPrice, "O preço após a redução deve ser 80.0");
        assertEquals(80.0, stock.getPrice(), "O preço do estoque deve ser atualizado para 80.0 após a redução");
    }


}
