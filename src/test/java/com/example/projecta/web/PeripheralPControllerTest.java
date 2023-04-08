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
public class PeripheralPControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListHCPageShown() throws Exception {
        mockMvc.perform(get("/peripheral/listedPE")).
                andExpect(status().isOk()).
                andExpect(view().name("/ListedAllProducts/listedproductsPE"));
    }

    @Test
    void testListCPUPageShown() throws Exception {
        mockMvc.perform(get("/peripheral/listedKEYBOARD")).
                andExpect(status().isOk()).
                andExpect(view().name("/Peripheral/listKEYBOARD"));
    }

    @Test
    void testListGPUPageShown() throws Exception {
        mockMvc.perform(get("/peripheral/listedMOUSE")).
                andExpect(status().isOk()).
                andExpect(view().name("/Peripheral/listMOUSE"));
    }



    @Test
    void testListHDDPageShown() throws Exception {
        mockMvc.perform(get("/peripheral/listedHEADSET")).
                andExpect(status().isOk()).
                andExpect(view().name("/Peripheral/listHEADSET"));
    }

    @Test
    void testListSSDPageShown() throws Exception {
        mockMvc.perform(get("/peripheral/listedMONITOR")).
                andExpect(status().isOk()).
                andExpect(view().name("/Peripheral/listMONITOR"));
    }

    @Test
    void testListPOWERSUPPLYPageShown() throws Exception {
        mockMvc.perform(get("/peripheral/listedSPEAKER")).
                andExpect(status().isOk()).
                andExpect(view().name("/Peripheral/listSPEAKER"));
    }

    @Test
    void testListCASEPageShown() throws Exception {
        mockMvc.perform(get("/peripheral/listedCONTROLLER")).
                andExpect(status().isOk()).
                andExpect(view().name("/Peripheral/listCONTROLLER"));
    }

    @Test
    void testListMOTHERBOARDPageShown() throws Exception {
        mockMvc.perform(get("/peripheral/listedETC")).
                andExpect(status().isOk()).
                andExpect(view().name("/Peripheral/listETC"));
    }
}
