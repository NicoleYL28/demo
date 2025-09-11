package com.example.demo.controller;

import com.example.demo.Company;
import com.example.demo.Employee;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

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
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.clear();
        companyRepository.clear();
    }

    @Test
    void should_return_company_with_employees() throws Exception {
        Company company = new Company();
        company.setName("oocl");
        companyRepository.insert(company);

        Employee employee = new Employee();
        employee.setSalary(800000);
        employee.setGender("Male");
        employee.setCompanyId(company.getId());
        employeeRepository.insert(employee);
        company.getEmployees().add(employee);

        mockMvc.perform(get("/companies/{id}", company.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(company.getId()))
                .andExpect(jsonPath("$.employees.length()").value(1));
    }

    @Test
    void should_create_company_when_given_valid_body() throws Exception {
        String requestBody = """
                { "name": "ABC" }
                """;

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void should_return_all_companies_list_when_get_all_companies() throws Exception {
        Company company1 = new Company();
        company1.setName("oocl");
        companyRepository.insert(company1);
        Company company2 = new Company();
        company2.setName("cola");
        companyRepository.insert(company2);

        mockMvc.perform(get("/companies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void should_return_correct_company_when_given_valid_companies_id() throws Exception {
        Company company = new Company();
        company.setName("oocl");
        companyRepository.insert(company);

        mockMvc.perform(get("/companies/{id}", company.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(company.getId()))
                .andExpect(jsonPath("$.name").value("oocl"));


    }

    @Test
    void should_update_company_when_given_valid_company_id_and_body() throws Exception {

        Company company = new Company();
        company.setName("oocl");
        companyRepository.insert(company);

        String updateRequestBody =  """
                { "id": %d,
                "name": "cola" }
                """.formatted(company.getId());
        mockMvc.perform(put("/companies/{id}", company.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(jsonPath("$.id").value(company.getId()))
                .andExpect(jsonPath("$.name").value("cola"));
    }

    @Test
    void should_delete_company_when_given_valid_company_id() throws Exception {
        Company company = new Company();
        company.setName("oocl");
        companyRepository.insert(company);

        mockMvc.perform(delete("/companies/{id}", company.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_return_company_list_when_given_valid_page_and_size() throws Exception {
        for (int i = 1; i <= 10; i++) {
            Company company = new Company();
            company.setName("oocl");
            companyRepository.insert(company);
        }
        mockMvc.perform(get("/companies?page=1&size=3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3));;
    }

}