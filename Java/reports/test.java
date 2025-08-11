package edu.missouristate.reports;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class test {
    private String url = "jdbc:mysql://localhost:3306/sag_db";
    private String user = "root";
    private String password = "1234567890";
    private String query = "select distinct state from sag_db.address;";

    public static List<String> main(String[] args) throws SQLException {
        test rb = new test();
        List<String> state = new ArrayList<String>();
        Connection con = DriverManager.getConnection(rb.url, rb.user, rb.password);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rb.query);
        state.add(rs.getString("state"));
        System.out.println(state);
        rs.close();
        stmt.close();
        return state;
    }
}
