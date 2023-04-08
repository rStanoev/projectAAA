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
public class HardwarePControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListHCPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedHC")).
                andExpect(status().isOk()).
                andExpect(view().name("/ListedAllProducts/listedproductsHC"));
    }

    @Test
    void testListCPUPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedCPU")).
                andExpect(status().isOk()).
                andExpect(view().name("Hardware/listCPU"));
    }

    @Test
    void testListGPUPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedGPU")).
                andExpect(status().isOk()).
                andExpect(view().name("Hardware/listGPU"));
    }

    @Test
    void testListMOTHERBOARDPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedMOTHERBOARD")).
                andExpect(status().isOk()).
                andExpect(view().name("Hardware/listMOTHERBOARD"));
    }

    @Test
    void testListHDDPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedHDD")).
                andExpect(status().isOk()).
                andExpect(view().name("Hardware/listHDD"));
    }

    @Test
    void testListSSDPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedSSD")).
                andExpect(status().isOk()).
                andExpect(view().name("Hardware/listSSD"));
    }

    @Test
    void testListPOWERSUPPLYPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedPOWERSUPPLY")).
                andExpect(status().isOk()).
                andExpect(view().name("Hardware/listPOWERSUPPLY"));
    }

    @Test
    void testListCASEPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedCASE")).
                andExpect(status().isOk()).
                andExpect(view().name("Hardware/listCASE"));
    }

    @Test
    void testListETCPageShown() throws Exception {
        mockMvc.perform(get("/hardware/listedETC")).
                andExpect(status().isOk()).
                andExpect(view().name("Hardware/listETC"));
    }




}
