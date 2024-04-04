package com.niq.shoppersvc.controller;

import com.niq.shoppersvc.service.EcommerceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EcommerceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EcommerceService ecommerceService;

    @InjectMocks
    private EcommerceController ecommerceController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ecommerceController).build();
    }

    @Test
    public void getShopperProductListTest() throws Exception{
        mockMvc.perform(get("/shopper/s-xxxx?category=baby"))
                .andExpect(status().isOk());
    }
}
