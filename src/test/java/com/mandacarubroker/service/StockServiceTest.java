package com.mandacarubroker.service;

import com.mandacarubroker.domain.stock.RequestStockDTO;
import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.domain.stock.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;



    @Test
    void getAllStocksTest() {
        Stock stock1 = new Stock(); // Preencha com dados de teste relevantes
        stock1.setSymbol("AAPL");
        stock1.setCompanyName("Apple Inc.");
        stock1.setPrice(150.0);
        Stock stock2 = new Stock();
        stock2.setSymbol("MSFT");
        stock2.setCompanyName("Microsoft Corporation");
        stock2.setPrice(250.0);
        when(stockRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        List<Stock> stocks = stockService.getAllStocks();

        assertNotNull(stocks);
        assertEquals(2, stocks.size());
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void getStockByIdTest_WithValidId() {
        RequestStockDTO data = new RequestStockDTO("BAS3","Banco-Do-Brasil",250.0); // Configurar com dados válidos de teste
        String validId = "validId";
        Stock stock = new Stock(data); // Configurar o mock stock com dados de teste
        when(stockRepository.findById(validId)).thenReturn(Optional.of(stock));

        Optional<Stock> result = stockService.getStockById(validId);

        assertTrue(result.isPresent());
        assertEquals(stock, result.get());
        verify(stockRepository, times(1)).findById(validId);
    }

    @Test
    void getStockByIdTest_WithInvalidId() {
        String invalidId = "invalidId";
        when(stockRepository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<Stock> result = stockService.getStockById(invalidId);

        assertFalse(result.isPresent());
        verify(stockRepository, times(1)).findById(invalidId);
    }

    @Test
    void createStockTest_WithValidData() {
        RequestStockDTO data = new RequestStockDTO("BAS3","Banco-Do-Brasil",250.0); // Configurar com dados válidos de teste
        Stock expectedStock = new Stock(data);
        when(stockRepository.save(any(Stock.class))).thenReturn(expectedStock);

        Stock result = stockService.createStock(data);

        assertNotNull(result);
        // Verificações adicionais conforme necessário
        verify(stockRepository, times(1)).save(any(Stock.class));
    }

// Pode-se adicionar mais testes para cobrir cenários de dados inválidos

    @Test
    void updateStockTest_WithValidIdAndData() {
        String validId = "validId";
        Stock existingStock = new Stock(); // Configurar com dados existentes
        existingStock.setSymbol("AAPL");
        existingStock.setCompanyName("Apple Inc.");
        existingStock.setPrice(150.0);
        Stock updatedStock = new Stock(); // Configurar com os novos dados
        updatedStock.setSymbol("MSFT");
        updatedStock.setCompanyName("Microsoft Corporation");
        updatedStock.setPrice(250.0);
        when(stockRepository.findById(validId)).thenReturn(Optional.of(existingStock));
        when(stockRepository.save(any(Stock.class))).thenReturn(updatedStock);

        Optional<Stock> result = stockService.updateStock(validId, updatedStock);

        assertTrue(result.isPresent());
        assertEquals(updatedStock, result.get());
        verify(stockRepository, times(1)).findById(validId);
        verify(stockRepository, times(1)).save(any(Stock.class));
    }
    @Test
    void deleteStockTest() {
        String id = "testId";

        stockService.deleteStock(id);

        verify(stockRepository, times(1)).deleteById(id);
    }

    @Test
    void validateAndCreateStock_Success() {
        RequestStockDTO data = new RequestStockDTO("BAS3","Banco-Do-Brasil",250.0); // Configure seu DTO com dados de teste válidos

        stockService.validateAndCreateStock(data);

        verify(stockRepository, times(1)).save(any(Stock.class));
    }


}
