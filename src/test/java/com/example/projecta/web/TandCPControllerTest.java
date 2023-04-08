package com.example.projecta.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class TandCPControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListHCPageShown() throws Exception {
        mockMvc.perform(get("/tANDc/listedTC")).
                andExpect(status().isOk()).
                andExpect(view().name("/ListedAllProducts/listedproductsTC"));
    }

    @Test
    void testListCPUPageShown() throws Exception {
        mockMvc.perform(get("/tANDc/listedTABLE")).
                andExpect(status().isOk()).
                andExpect(view().name("/TableAndChair/listTABLE"));
    }

    @Test
    void testListGPUPageShown() throws Exception {
        mockMvc.perform(get("/tANDc/listedCHAIR")).
                andExpect(status().isOk()).
                andExpect(view().name("/TableAndChair/listCHAIR"));
    }
}
