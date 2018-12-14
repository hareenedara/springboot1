package com.refresh.samples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refresh.samples.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerUnitTest {
    @Autowired
    MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void controllerTest() throws Exception {
        Employee emp = new Employee();
        emp.setFirstName("Har");
        emp.setLastName("Eda");
        emp.setAge(100);
        emp.setDept("Sys");
        mockMvc.perform(post("/app/employee").content(objectMapper.writeValueAsBytes(emp)).header("Content-Type","application/json"))
                .andExpect(status().is2xxSuccessful());

    }

}
