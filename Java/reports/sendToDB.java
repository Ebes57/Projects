package edu.missouristate.reports;

import edu.missouristate.controller.reportController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class sendToDB {
    //Login for DB
    private String url = "jdbc:mysql://localhost:3306/sag_db";
    private String user = "root";
    private String password = "12345678";


    public static String formatData(List<String> userData, String query, Integer primaryKey){
        query += primaryKey + ", ";
        System.out.println(query);
        System.out.println(userData);

        if(userData.size() == 0){
            return query;
        }else if(userData.size() == 1){
            query += "'" + checkInvalidCharacters(userData.get(0)) + "');";
            return query;
        } else if (userData.size() == 2) {
            query += "'" + checkInvalidCharacters(userData.get(0)) + "', '" + checkInvalidCharacters(userData.get(1)) + "');";
            return query;
        }
        for(int i = 0; i < userData.size() - 1; i++){
            query += "'" + checkInvalidCharacters(userData.get(i)) + "',";
        }
        query += "'" + checkInvalidCharacters(userData.get(userData.size() - 1));
        query += "');";

        //System.out.println(query);


        return query;
    }

    public static String checkInvalidCharacters(String str1){
        if(str1 == null){
            return "";
        }else if(str1.length() == 0){
            return "";
        }
        str1 = str1.replace("'", " ");

        return str1;
    }

    public static String formatPersonalInformation(List<String> userData, String query){

        if(userData.size() == 0){
            return query;
        }
        for(int i = 0; i < userData.size() - 1; i++){
            query += "'" + userData.get(i) + "',";
        }
        query += "'" + userData.get(userData.size() - 1);
        query += "')";

        System.out.println(query);


        return query;
    }

    public static Integer getPrimaryKey(List<String> userData) throws SQLException {
        sendToDB s = new sendToDB();

        Integer primaryKey = null;

        String sqlGetData = "SELECT `personal_data`.`sag_registry_number` FROM `sag_db`.`personal_data` where ";
        sqlGetData = sqlGetData + "first_name = '" + userData.get(0) + "' and ";
        sqlGetData =  sqlGetData + "middle_name = '" + userData.get(1) + "' and ";
        sqlGetData =  sqlGetData + "last_name = '" + userData.get(2) + "' and ";
        sqlGetData =  sqlGetData + "birthdate = '" + userData.get(3) + "' and ";
        sqlGetData =  sqlGetData + "birth_gender = '" + userData.get(4) + "' and ";
        sqlGetData =  sqlGetData + "Spouse = '" + userData.get(5) + "';";

        System.out.println(sqlGetData);

        //System.out.println(sqlGetData);

        Connection con = DriverManager.getConnection(s.url, s.user, s.password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlGetData);

        while(rs.next()){
            primaryKey = Integer.valueOf(rs.getString("sag_registry_number"));

            System.out.println("Primary Key: " + primaryKey.toString());
            break;
        }
        rs.close();
        stmt.close();
        return primaryKey;
    }

    public static String sendData(String query) throws SQLException{
        try{
            sendToDB s = new sendToDB();
            Connection con = DriverManager.getConnection(s.url, s.user, s.password);
            Statement stmt = con.createStatement();
            System.out.println(query);
            int rs = stmt.executeUpdate(query);
            System.out.println(rs);
            stmt.close();

            return "Success! Sent to Address DB";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> runQuery() throws SQLException{
        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'sag_db';";
        //String query = "Select address_1 from sag_db.address where address_1 = '2425 NE Carousel Ct';";

        sendToDB s = new sendToDB();
        Connection con = DriverManager.getConnection(s.url, s.user, s.password);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<String> tableNames = new ArrayList<>();

        while(rs.next()){
            tableNames.add(rs.getString("table_name"));
        }
        stmt.close();

        return tableNames;
    }

    public static String validEntry(List<String> validList){
        return "Success";
    }
}
