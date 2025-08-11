package edu.missouristate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iii")
public class testController {
    @PostMapping("/your-endpoint")
    public ResponseEntity<String> receiveData(@RequestBody RequestData data) {
        String variable1 = data.getVariable1();
        String variable2 = data.getVariable2();

        // Process the received variables
        System.out.println("Received variable1: " + variable1);
        System.out.println("Received variable2: " + variable2);

        return ResponseEntity.ok("Data received successfully!");
    }

    public static class RequestData {
        private String variable1;
        private String variable2;

        public String getVariable1() {
            return variable1;
        }

        public void setVariable1(String variable1) {
            this.variable1 = variable1;
        }

        public String getVariable2() {
            return variable2;
        }

        public void setVariable2(String variable2) {
            this.variable2 = variable2;
        }
    }
}

