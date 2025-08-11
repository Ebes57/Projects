package edu.missouristate.controller;

import edu.missouristate.reports.reportbuilder;
import edu.missouristate.reports.sendToDB;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/form")
public class formController {
    @PostMapping("/getInfoFromDB")
    public List<String> getInfo(@RequestBody SentData data) throws Exception{
        String sql = data.getSql();
        System.out.println(sql);
        return reportbuilder.runQuery(sql);
    }
    @PostMapping("/data")
    public String main(@RequestBody SentData data) throws SQLException {
        //Creates variables for user data
        String address1 = data.getAdline1();
        String address2 = data.getAdline2();
        String address3 = data.getAdline3();
        String city = data.getCity();
        String state = data.getState();
        String zip = data.getZip();
        String spouse = data.getSpouse();
        String fname = data.getFname();
        String lname = data.getLname();
        String subBy = data.getSubBy();
        String status = data.getStatus();
        String refBy = data.getRefBy();
        String mname = data.getMname();
        String bday = data.getBday();
        String gender = data.getGender();
        String phone1 = data.getPhone1();
        String phone2 = data.getPhone2();
        String subservstatus = data.getSubservstatus();
        String shipdes = data.getShipdes();
        String shipnum = data.getShipnum();
        String shipname = data.getShipname();
        String fromDate = data.getFromDate();
        String endDate = data.getEndDate();
        String usnRate = data.getUsnRate();
        String isscat = data.getIsscat();
        String issdiag = data.getIssdiag();
        String symstart = data.getSymstart();
        String yod = data.getYod();
        String cfwva = data.getCfwva();
        String statiss = data.getStatiss();
        String vadis = data.getVadis();
        String disrat = data.getDisrat();
        String usnRateName = data.getUsnRateName();
        String exposure = data.getExposure();
        String exposureDate = data.getExposureDate();
        String sagCallBack = data.getSagCallBack();
        String ussVimem = data.getUssVimem();
        String vfwMem = data.getVfwMem();
        String davMem = data.getDavMem();
        String fleetReserve = data.getFleetReserve();
        String subServStatusID = data.getSubServStatusID();

        List<String> query = new ArrayList<>();

        if(address2 == null){
            address2 = "";
        }if(address3 == null){
            address3 = "";
        }if(phone2 == null){
            phone2 = "";
        }if(disrat == null || disrat == ""){
            disrat = "0";
        }

        String queryPersonalData = "INSERT INTO `sag_db`.`personal_data`(`first_name`,`middle_name`,`last_name`,`birthdate`,`birth_gender`,`Spouse`) VALUES(";
        List<String> personalDataLsit = new ArrayList<>();
        personalDataLsit.add(fname);
        personalDataLsit.add(mname);
        personalDataLsit.add(lname);
        personalDataLsit.add(bday);
        personalDataLsit.add(gender);
        personalDataLsit.add(spouse);
        sendToDB.sendData(sendToDB.formatPersonalInformation(personalDataLsit, queryPersonalData) + ";");
        //Add to db first then get pk
        Integer primaryKey = 300011;//sendToDB.getPrimaryKey(personalDataLsit);
        System.out.println("Primary Key: " + primaryKey.toString());

        String queryAddress = "INSERT INTO `sag_db`.`address`(`sag_registry_number`,`address_1`,`address_2`,`address_3`,`city`,`state`,`zip_code`,`spouse`) VALUES (";
        List<String> adressList = new ArrayList<>();
        adressList.add(address1);
        adressList.add(address2);
        adressList.add(address3);
        adressList.add(city);
        adressList.add(state);
        adressList.add(zip);
        adressList.add(spouse);


        //Missing entries
        String queryBoatAccidents = "INSERT INTO `sag_db`.`boat_accidents_incidents`(`sag_registry_number`,`boat_hull_designation`,`category`,`exposed_to`,`approx_date_of_event`)VALUES(";
        List<String> boatAccidentList = new ArrayList<>();
        boatAccidentList.add(shipdes + "-" + shipnum);
        boatAccidentList.add(isscat);
        boatAccidentList.add(exposure);
        boatAccidentList.add(exposureDate);


        //Not Working
        //Issue with trying to submit years as strings
        String queryHealthIssuesHipaa  ="INSERT INTO `sag_db`.`health_issues_hipaa`(`sag_registry_number`,`issue_category`,`issue_diagnosis`,`symptom_start_date`,`year_of_diagnosis`,`va_claim_filed`,`va_claim_status`,`va_disability`,`va_disability_rating`)VALUES(";
        List<String> healthIssuesHipaaList = new ArrayList<>();
        healthIssuesHipaaList.add(isscat);
        healthIssuesHipaaList.add(issdiag);
        healthIssuesHipaaList.add(symstart);
        healthIssuesHipaaList.add(yod);
        healthIssuesHipaaList.add(cfwva);
        healthIssuesHipaaList.add(statiss);
        healthIssuesHipaaList.add(vadis);
        healthIssuesHipaaList.add(disrat);


        System.out.println(symstart);
        for(int i = 0; i < healthIssuesHipaaList.size(); i++){
            System.out.println(healthIssuesHipaaList.get(i));
        }

        //Need to add start date and end date on form for member boats
        String queryMemberBoats = "INSERT INTO `sag_db`.`member_boats`(`sag_registry_number`,`assigned_boat_hull_designation`,`start_date`,`end_data`)VALUES(";
        List<String> memberBoatsList = new ArrayList<>();
        memberBoatsList.add(shipdes + "-" + shipnum);
        memberBoatsList.add(fromDate);
        memberBoatsList.add(endDate);

        String queryMemberDod = "INSERT INTO `sag_db`.`member_dod`(`sag_registry_number`,`branch`,`designation`,`description`)VALUES(";
        List<String> memberDodList = new ArrayList<>();
        memberDodList.add(subservstatus);
        memberDodList.add(shipdes);
        memberDodList.add("DESC");


        String queryMiscInfo = "INSERT INTO `sag_db`.`misc_info`(`sag_registry_number`,`info_submitted_by`,`veteran_life_status`,`sag_advocate_verification_call`,`sub_service_status_id`,`referral_source`,`ussvi_member`,`vfw_member`,`dav_member`,`fleet_reserve_member`)VALUES(";
        List<String> miscInfoList = new ArrayList<>();
        miscInfoList.add(subBy);
        miscInfoList.add(status);
        miscInfoList.add(sagCallBack);
        miscInfoList.add(subServStatusID);
        miscInfoList.add(refBy);
        miscInfoList.add(ussVimem);
        miscInfoList.add(vfwMem);
        miscInfoList.add(davMem);
        miscInfoList.add(fleetReserve);


        String queryPhoneNumber = "INSERT INTO `sag_db`.`phone_number`(`sag_registry_number`,`primary_phone`,`secondary_phone`)VALUES (";
        List<String> phoneNumberList = new ArrayList<>();
        phoneNumberList.add(phone1);
        phoneNumberList.add(phone2);
        sendToDB.formatData(phoneNumberList, queryPhoneNumber, primaryKey);

        String queryUsnRates = "INSERT INTO `sag_db`.`usn_rates`(`sag_registry_number`,`rate_abbreviation`,`rate_name`)VALUES(";
        List<String> usnRateList = new ArrayList<>();
        System.out.println("USN Rate: " + usnRate);
        System.out.println("RATE NAME: " + usnRateName);
        usnRateList.add(usnRate);
        usnRateList.add(usnRateName);
        System.out.println("USN RATE SIZE: " + usnRateList.size());



        query.add(sendToDB.formatData(adressList, queryAddress, primaryKey));
        query.add(sendToDB.formatData(boatAccidentList, queryBoatAccidents, primaryKey));
        query.add(sendToDB.formatData(healthIssuesHipaaList,queryHealthIssuesHipaa, primaryKey));
        query.add(sendToDB.formatData(memberDodList, queryMemberDod, primaryKey));
        query.add(sendToDB.formatData(memberBoatsList, queryMemberBoats, primaryKey));
        query.add(sendToDB.formatData(miscInfoList, queryMiscInfo,primaryKey));
        query.add(sendToDB.formatData(phoneNumberList, queryPhoneNumber, primaryKey));
        query.add(sendToDB.formatData(usnRateList, queryUsnRates, primaryKey));


        String queryTest = "";
        System.out.println("QUERY READY TO BE SENT: ");
        for(int i = 0; i < query.size(); i++){
            //System.out.println(i);
            //System.out.println(query.get(i));
            sendToDB.sendData(query.get(i));

            //sendToDB.sendData(query.get(i));
        }
        System.out.println(queryTest);

        return "0";

    }

    public static class SentData {
        private String adline1;
        private String adline2;
        private String adline3;
        private String city;
        private String state;
        private String zip;
        private String spouse;
        private String fname;
        private String lname;
        private String subBy;
        private String status;
        private String refBy;
        private String mname;
        private String bday;
        private String gender;
        private String phone1;
        private String phone2;
        private String subservstatus;
        private String shipdes;
        private String shipnum;
        private String shipname;
        private String fromDate;
        private String endDate;
        private String usnRate;
        private String isscat;
        private String issdiag;
        private String symstart;
        private String yod;
        private String cfwva;
        private String statiss;
        private String vadis;
        private String disrat;
        private String usnratename;
        private String exposure;
        private String exposureDate;
        private String sagCallBack;
        private String ussVimem;
        private String vfwMem;
        private String davMem;
        private String fleetReserve;
        private String usnRateName;
        private String subServStatusID;
        private String sql;

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public String getSubServStatusID() {
            return subServStatusID;
        }

        public void setSubServStatusID(String subServStatusID) {
            this.subServStatusID = subServStatusID;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getSpouse() {
            return spouse;
        }

        public void setSpouse(String spouse) {
            this.spouse = spouse;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getSubBy() {
            return subBy;
        }

        public void setSubBy(String subBy) {
            this.subBy = subBy;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRefBy() {
            return refBy;
        }

        public void setRefBy(String refBy) {
            this.refBy = refBy;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getBday() {
            return bday;
        }

        public void setBday(String bday) {
            this.bday = bday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAdline1() {
            return adline1;
        }

        public void setAdline1(String adline1) {
            this.adline1 = adline1;
        }

        public String getAdline2() {
            return adline2;
        }

        public void setAdline2(String adline2) {
            this.adline2 = adline2;
        }

        public String getAdline3() {
            return adline3;
        }

        public void setAdline3(String adline3) {
            this.adline3 = adline3;
        }

        public String getPhone1() {
            return phone1;
        }

        public void setPhone1(String phone1) {
            this.phone1 = phone1;
        }

        public String getPhone2() {
            return phone2;
        }

        public void setPhone2(String phone2) {
            this.phone2 = phone2;
        }

        public String getSubservstatus() {
            return subservstatus;
        }

        public void setSubservstatus(String subservstatus) {
            this.subservstatus = subservstatus;
        }

        public String getShipdes() {
            return shipdes;
        }

        public void setShipdes(String shipdes) {
            this.shipdes = shipdes;
        }

        public String getShipnum() {
            return shipnum;
        }

        public void setShipnum(String shipnum) {
            this.shipnum = shipnum;
        }

        public String getShipname() {
            return shipname;
        }

        public void setShipname(String shipname) {
            this.shipname = shipname;
        }

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getUsnRate() {
            return usnRate;
        }

        public void setUsnRate(String usnRate) {
            this.usnRate = usnRate;
        }

        public String getIsscat() {
            return isscat;
        }

        public void setIsscat(String isscat) {
            this.isscat = isscat;
        }

        public String getIssdiag() {
            return issdiag;
        }

        public void setIssdiag(String issdiag) {
            this.issdiag = issdiag;
        }

        public String getSymstart() {
            return symstart;
        }

        public void setSymstart(String symstart) {
            this.symstart = symstart;
        }

        public String getYod() {
            return yod;
        }

        public void setYod(String yod) {
            this.yod = yod;
        }

        public String getCfwva() {
            return cfwva;
        }

        public void setCfwva(String cfwva) {
            this.cfwva = cfwva;
        }

        public String getStatiss() {
            return statiss;
        }

        public void setStatiss(String statiss) {
            this.statiss = statiss;
        }

        public String getVadis() {
            return vadis;
        }

        public void setVadis(String vadis) {
            this.vadis = vadis;
        }

        public String getDisrat() {
            return disrat;
        }

        public void setDisrat(String disrat) {
            this.disrat = disrat;
        }

        public String getUsnratename() {
            return usnratename;
        }

        public void setUsnratename(String usnratename) {
            this.usnratename = usnratename;
        }

        public String getExposure() {
            return exposure;
        }

        public void setExposure(String exposure) {
            this.exposure = exposure;
        }

        public String getExposureDate() {
            return exposureDate;
        }

        public void setExposureDate(String exposureDate) {
            this.exposureDate = exposureDate;
        }

        public String getSagCallBack() {
            return sagCallBack;
        }

        public void setSagCallBack(String sagCallBack) {
            this.sagCallBack = sagCallBack;
        }

        public String getFleetReserve() {
            return fleetReserve;
        }

        public void setFleetReserve(String fleetReserve) {
            this.fleetReserve = fleetReserve;
        }

        public String getVfwMem() {
            return vfwMem;
        }

        public void setVfwMem(String vfwMem) {
            this.vfwMem = vfwMem;
        }

        public String getDavMem() {
            return davMem;
        }

        public void setDavMem(String davMem) {
            this.davMem = davMem;
        }

        public String getUssVimem() {
            return ussVimem;
        }

        public void setUssVimem(String ussVimem) {
            this.ussVimem = ussVimem;
        }

        public String getUsnRateName() {
            return usnRateName;
        }

        public void setUsnRateName(String usnRateName) {
            this.usnRateName = usnRateName;
        }
    }
}
