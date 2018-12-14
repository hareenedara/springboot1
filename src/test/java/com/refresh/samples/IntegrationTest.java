package com.refresh.samples;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.refresh.samples.model.EmpValidError;
import com.refresh.samples.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void postEmployee(){
        final ObjectMapper objectMapper = new ObjectMapper();
        String FirstName = "Hareen";
        String LastName = "Edara";

        Employee emp = new Employee();
        emp.setFirstName(FirstName);
        emp.setLastName(LastName);
        emp.setAge(100);
        ResponseEntity<Object> response = testRestTemplate.postForEntity("/app/employee",emp,Object.class);
        if(response.getStatusCode().is2xxSuccessful()){
            final Employee emptemp = objectMapper.convertValue(response.getBody(),Employee.class);
           assertEquals(FirstName, emptemp.getFirstName());
           assertEquals(LastName, emptemp.getLastName());
        }else if(response.getStatusCode().is4xxClientError()){
            //final EmpValidError errortemp = objectMapper.convertValue(response.getBody(),EmpValidError.class);
            final Gson gson = new Gson();
            final EmpValidError errortemp2 = gson.fromJson(gson.toJson(response.getBody()),EmpValidError.class);
            if (errortemp2.getErrorMessages().size()>0){
                System.out.println("Invalid input to controller...");
            }
        }
        System.out.println("Test PostEmployee done");

    }
}
