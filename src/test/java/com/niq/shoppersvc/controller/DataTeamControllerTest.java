package com.niq.shoppersvc.controller;

import com.niq.shoppersvc.service.DataTeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DataTeamControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DataTeamService dataTeamService;

    @InjectMocks
    private DataTeamController dataTeamController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dataTeamController).build();
    }

    @Test
    public void saveProductTest() throws Exception{
        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"productId\": \"yyy\",\n" +
                                "    \"category\": \"child\",\n" +
                                "    \"brand\": \"local\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }

    @Test
    public void savePersonalizedProductTest() throws Exception{
        mockMvc.perform(post("/shopper/personalized-product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"shopperId\": \"s-xxx\",\n" +
                        "    \"shelf\" : [\n" +
                        "        {\n" +
                        "            \"productId\" : \"xxx\",\n" +
                        "            \"relevancyScore\": 45.444555433\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"productId\" : \"xxx\",\n" +
                        "            \"relevancyScore\": 47.444555433\n" +
                        "        }\n" +
                        "    ]\n" +
                        "    \n" +
                        "}")
        ).andExpect(status().isOk());
    }
}
