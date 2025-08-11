package edu.missouristate.controller;


import edu.missouristate.reports.reportbuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/rb")
public class reportController {
    @PostMapping("/getInfoFromDB")
    public List<String> getInfo(@RequestBody GetData data) throws Exception{
        String selection = data.getSelection();
        System.out.println(selection);
        return reportbuilder.runQuery(selection);
    }
    public static class GetData {
        private String selection;

        public String getSelection() {
            return selection;
        }

        public void setSelection(String selection) {
            this.selection = selection;
        }
    }
}
