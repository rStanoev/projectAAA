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
public class PcPControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListHCPageShown() throws Exception {
        mockMvc.perform(get("/pc/listedPC")).
                andExpect(status().isOk()).
                andExpect(view().name("/ListedAllProducts/listedproductsPC"));
    }

    @Test
    void testListCPUPageShown() throws Exception {
        mockMvc.perform(get("/pc/listedPCs")).
                andExpect(status().isOk()).
                andExpect(view().name("/Pc/listPC"));
    }

    @Test
    void testListGPUPageShown() throws Exception {
        mockMvc.perform(get("/pc/listedLAPTOP")).
                andExpect(status().isOk()).
                andExpect(view().name("/Pc/listLAPTOP"));
    }



    @Test
    void testListHDDPageShown() throws Exception {
        mockMvc.perform(get("/pc/listedCONSOLE")).
                andExpect(status().isOk()).
                andExpect(view().name("/Pc/listCONSOLE"));
    }
}
