package edu.missouristate.controller;

import edu.missouristate.reports.reportbuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class controller {
    @GetMapping("/index")
    public String getIndex(Model model) throws Exception {
        return "index";
    }
}
