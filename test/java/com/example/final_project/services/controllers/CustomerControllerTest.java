package com.example.final_project.controllers;
import com.example.final_project.dto.CustomerDTO;
import com.example.final_project.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerDTO customerDTO;

    @BeforeEach
    void setup() {
        List<Integer> accounts = new ArrayList<>();
        accounts.add(1);
        customerDTO = new CustomerDTO(1L,"Test Customer", accounts);
        customerDTO.setId(1L);
        customerDTO.setFullName("Test Customer");
        customerDTO.setAccounts(accounts);

    }

    @Test
    public void testGetCustomerById() {
        try {
            when(customerService.getCustomerById(1L)).thenReturn(customerDTO);

            mockMvc.perform(get("/customer/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.fullName").value("Test Customer"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testRegisterCustomer() {
        try {


            when(customerService.registerCustomer(any())).thenReturn(customerDTO);

            mockMvc.perform(post("/customer")
                            .contentType(MediaType.TEXT_PLAIN)
                            .content(objectMapper.writeValueAsString(customerDTO.getFullName())))
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("$.id").value(1))
                            .andExpect(jsonPath("$.fullName").value("Test Customer"))
                            .andExpect(jsonPath("$.accounts").value(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteCustomer() {
        try {
            double balance = 100.00;
            when(customerService.deleteCustomer(1L)).thenReturn(balance);

            mockMvc.perform(delete("/customer/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(String.valueOf(balance)));
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

}


