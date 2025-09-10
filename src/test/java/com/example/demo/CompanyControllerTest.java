package com.example.demo;

import com.example.demo.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        companyService.clear();
    }

    @Test
    void should_create_company_when_given_valid_body() throws Exception {
        String requestBody = """
                { "name": "ABC" }
                """;

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void should_return_all_companies_list_when_get_all_companies() throws Exception {
        String requestBody1 =  """
                { "name": "ABC" }
                """;
        String requestBody2 =  """
                { "name": "DEF" }
                """;
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody1));
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody2));
        mockMvc.perform(get("/companies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void should_return_correct_company_when_given_valid_employee_id() throws Exception {
        String requestBody =  """
                { "name": "ABC" }
                """;

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        mockMvc.perform(get("/companies/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("ABC"));


    }

    @Test
    void should_update_company_when_given_valid_employee_id_and_body() throws Exception {
        String requestBody =  """
                { "name": "ABC" }
                """;
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        String updateRequestBody =  """
                { "name": "DEF" }
                """;
        mockMvc.perform(put("/companies/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("DEF"));

    }

    @Test
    void should_delete_company_when_given_valid_employee_id() throws Exception {
        String requestBody =  """
                { "name": "ABC" }
                """;
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        mockMvc.perform(delete("/companies/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_return_company_list_when_given_valid_page_and_size() throws Exception {
        for (int i = 1; i <= 5; i++) {
            String requestBody =  """
                { "name": "ABC" }
                """;
            mockMvc.perform(post("/companies")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody));
        }
        mockMvc.perform(get("/companies?page=2&size=3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(4))
                .andExpect(jsonPath("$[1].id").value(5));
    }

}