package com.example.demo;

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
class EmployeeControllerTest {
    @Autowired
    private EmployeeController EmployeeController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        EmployeeController.employees.clear();
    }

    @Test
    void should_create_employee_when_given_valid_body() throws Exception {
        String requestBody = """
                { "name": "Tom",
                "age": "18",
                "salary": "500.0",
                "gender": "Male"
                }
                """;

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void should_return_correct_employee_when_given_valid_employee_id() throws Exception {
        String requestBody = """
                { "name": "Marry",
                "age": "18",
                "salary": "500.0",
                "gender": "Female"
                }
                """;

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        mockMvc.perform(get("/employees/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Marry"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.salary").value(500.0))
                .andExpect(jsonPath("$.gender").value("Female"));

    }

    @Test
    void should_return_employee_list_when_given_valid_employee_gender() throws Exception {
        String requestBody = """
                { "name": "John",
                "age": "18",
                "salary": "500.0",
                "gender": "Male"
                }
                """;
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        mockMvc.perform(get("/employees?gender=Male")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].salary").value(500.0))
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }


}