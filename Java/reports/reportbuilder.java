package edu.missouristate.reports;


import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class reportbuilder {
    private String url = "jdbc:mysql://localhost:3306/sag_db";
    private String sql1 = "SELECT primary_key_column FROM your_table_name ORDER BY primary_key_column DESC=LIMIT 1;";
    private String user = "root";
    private String password = "12345678";
    private String query = "select distinct state from sag_db.address;";
    private String Q2 = "select count(*) from sag_db.address where state = ${state};";;


    public static Integer main(String[] args) throws Exception {

        reportbuilder rb = new reportbuilder();

        Connection con = DriverManager.getConnection(rb.url, rb.user, rb.password);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rb.Q2);
        Integer c = 0;
        if (rs.next()) {
            Integer count = rs.getInt(1);
            System.out.println(count);
            c = count;
        }

        rs.close();
        stmt.close();
        return c;

    }
    public static List<String> getState() throws Exception {
        reportbuilder rb = new reportbuilder();
        Connection con = DriverManager.getConnection(rb.url, rb.user, rb.password);
        List<String> s = new ArrayList<String>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(rb.query);
        //String state = rs.getString("state");
        while(rs.next()) {
            s.add(rs.getString("state"));
        }
        rs.close();
        stmt.close();
        //return state;
        return getStateCount(s);
    }
    public static List<String> getStateCount(List<String> args) throws Exception {
        reportbuilder rb = new reportbuilder();
        String Q2 = "select count(*) from sag_db.address where state = ${state};";
        Connection con = DriverManager.getConnection(rb.url, rb.user, rb.password);
        List<String> s = new ArrayList<String>();
        Statement stmt = con.createStatement();
        System.out.println(args);
        for(int x = 0; x < args.size(); x++) {
            String temps = args.get(x);
            Q2 = "select count(*) from sag_db.address where state = " + "'" + temps  + "'" + ";";
            ResultSet rs = stmt.executeQuery(Q2);
            Integer c = 0;
            if (rs.next()) {
                Integer count = rs.getInt(1);
                System.out.println(count);
                c = count;
            }
            rs.close();
            s.add(temps);
            s.add(c.toString());


        }
        System.out.println(s);
        stmt.close();
        return s;
    }
    public static List<String> runQuery(String query) throws Exception {
        reportbuilder rb = new reportbuilder();
        String queryStart = "SELECT issue_diagnosis, count(issue_diagnosis) FROM `sag_db`.`health_issues_hipaa` where issue_category = '";
        String queryEnd = "' group by issue_diagnosis;";
        String finalQuery = queryStart + query + queryEnd;

        System.out.println(finalQuery);

        Connection con = DriverManager.getConnection(rb.url, rb.user, rb.password);
        List<String> s = new ArrayList<String>();
        Statement stmt = con.createStatement();


        ResultSet rs = stmt.executeQuery(finalQuery);

        try{
            while(rs.next()) {
                s.add(rs.getString("issue_diagnosis"));
                s.add(rs.getString("count(issue_diagnosis)".toString()));
            }
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println(s);
        stmt.close();
        return s;
    }


}
