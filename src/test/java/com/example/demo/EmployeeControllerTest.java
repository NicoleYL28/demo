package com.example.demo;

import com.example.demo.controller.EmployeeController;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService.clear();
        employeeService.create(new Employee("Tom", 20, 500.0, "Male"));
        employeeService.create(new Employee("Marry", 20, 500.0, "Female"));
    }


    @Test
    void should_create_employee_when_given_valid_body() throws Exception {
        String requestBody = """
                { "name": "John",
                "age": "20",
                "salary": "500.0",
                "gender": "Male"
                }
                """;

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void should_return_correct_employee_when_given_valid_employee_id() throws Exception {

        mockMvc.perform(get("/employees/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tom"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.salary").value(500.0))
                .andExpect(jsonPath("$.gender").value("Male"));

    }

    @Test
    void should_return_employee_list_when_given_valid_employee_gender() throws Exception {
        mockMvc.perform(get("/employees?gender=Male")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].salary").value(500.0))
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }

    @Test
    void should_return_all_employee_list_when_get_all_employees() throws Exception {

        mockMvc.perform(get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void should_update_employee_when_given_valid_employee_id_and_body() throws Exception {
        String updateRequestBody = """
                { "name": "Tom",
                "age": "25",
                "salary": "800.0",
                "gender": "Male"
                }
                """;
        mockMvc.perform(put("/employees/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tom"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.salary").value(800.0))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    void should_delete_employee_when_given_valid_employee_id() throws Exception {

        mockMvc.perform(delete("/employees/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_return_employee_list_when_given_valid_page_and_size() throws Exception {
        for (int i = 1; i <= 8; i++) {
            String requestBody = """
                    { "name": "Tom",
                    "age": "18",
                    "salary": "500.0",
                    "gender": "Male"
                    }
                    """;
            mockMvc.perform(post("/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody));
        }
        mockMvc.perform(get("/employees?page=2&size=3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].id").value(4))
                .andExpect(jsonPath("$[1].id").value(5))
                .andExpect(jsonPath("$[2].id").value(6));
    }

    @Test
    void should_return_error_when_create_given_employee_name_excess_30_and_salary_below_20000() throws Exception {
        String requestBody = """
                { "name": "Bob",
                "age": "37",
                "salary": "15000.0",
                "gender": "Male"
                }
                """;

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isBadRequest());
    }




}