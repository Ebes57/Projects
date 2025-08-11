package edu.missouristate.controller;

import edu.missouristate.reports.sendToDB;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/update-database")
public class UpdateDatabaseController {
    @PostMapping("/sql")
    public List<String> mian(String sql) throws SQLException {
        return sendToDB.runQuery();
    }
}
