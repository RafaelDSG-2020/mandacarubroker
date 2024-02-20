package com.mandacarubroker.controller;

import com.mandacarubroker.domain.stock.RequestStockDTO;
import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock("1", "AAP3", "Apple Inc.", 100.0);
    }

    @Test
    void getAllStocksTest() throws Exception {
        given(stockService.getAllStocks()).willReturn(Arrays.asList(stock));

        mockMvc.perform(get("/stocks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(stock.getId()));
    }

    @Test
    void getStockByIdTest() throws Exception {
        given(stockService.getStockById("1")).willReturn(Optional.of(stock));

        mockMvc.perform(get("/stocks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stock.getId()));
    }

    @Test
    void createStockTest() throws Exception {
        RequestStockDTO requestStockDto = new RequestStockDTO(stock.getSymbol(), stock.getCompanyName(), stock.getPrice());
        given(stockService.createStock(requestStockDto)).willReturn(stock);

        mockMvc.perform(post("/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"symbol\":\"AAP3\",\"companyName\":\"Apple Inc.\",\"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.symbol").value("AAP3"));
    }




    @Test
    void updateStockTest() throws Exception {
        given(stockService.updateStock("1", stock)).willReturn(Optional.of(stock));

        mockMvc.perform(put("/stocks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"symbol\":\"AAC3\",\"companyName\":\"Apple Inc.\",\"price\":150.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.symbol").value(stock.getSymbol()))
                .andExpect(jsonPath("$.price").value(stock.getPrice()));

    }

    @Test
    void deleteStockTest() throws Exception {
        mockMvc.perform(delete("/stocks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock with ID 1 was deleted successfully."));
    }
}
