package com.example.minibank;

import com.example.minibank.controller.ClientController;
import com.example.minibank.dto.ClientDto;
import com.example.minibank.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @Test
    void signupSuccessfully() throws Exception {
        ClientDto dto = new ClientDto("sina", "password1");

        doNothing().when(clientService).signUp(dto);

        mockMvc.perform(post("http://localhost:8080/client/signup")
                        .contentType(APPLICATION_JSON)
                        .header("X-API-KEY", "super-secret-bank-key")

                        .content("""
                            {
                                "username": "sina",
                                "password": "password1"
                            }
                        """))
                .andExpect(status().isOk());
    }
}
