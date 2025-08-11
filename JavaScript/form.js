window.addEventListener("DOMContentLoaded", ()=>{
    window.addEventListener("submit", sendData);
});

function validateForm(){
    let errorMessage = "";
    errorMessage = validateDates(errorMessage);
    validForm(errorMessage);
}

function validateDates(errorMessage){
    let bday = document.getElementById("bday").value;
    let fromDate = document.getElementById("fromdate").value;
    let toDate = document.getElementById("todate").value;

    let bdayList = bday.split("-");
    let fromDateList = fromDate.split("-");
    let toDateList = toDate.split("-");

    if(Number(bdayList[0]) >= Number(fromDateList[0])){
        errorMessage += "Birthdate is before before Start Date! Please check the Year!";
    }
    if(Number(fromDateList[0]) >= Number(toDateList[0])){
        errorMessage += "Start Date is before End Date! Please check the Year!";
    }else if(Number(fromDateList[0]) == Number(toDate[0])){
        if(Number(fromDateList[1]) >= fromDateList[1] || Number(fromDateList[2]) > Number(toDateList[2])){
            errorMessage += "Start Date and End Date are too close together!";
        }
    }
    return errorMessage;
}


function validForm(errorMessage){
    if(errorMessage != ""){
        window.alert(errorMessage);
    }else{
        getFormEntry();
        sendData();
    }
}
function getFormEntry(){
    //Get Primary key
    // Add to address first
    /* Fecth request goes here*/
    //
}
function sendData(){
    //Need questions for boat accidents
    let dataList = {

        subBy: document.getElementById("subm").value,
        status: document.getElementById("status").value,
        refBy: document.getElementById("refby").value,
        fname: document.getElementById("fname").value,
        mname: document.getElementById("mname").value,
        lname: document.getElementById("lname").value,
        bday: document.getElementById("bday").value,
        gender: document.getElementById("gender").value,
        adline1: document.getElementById("adline1").value,
        adline2: document.getElementById("adline2").value,
        adline3: document.getElementById("adline3").value,
        city: document.getElementById("city").value,
        state: document.getElementById("state").value,
        zip: document.getElementById("zip").value,
        phone1: document.getElementById("phone1").value + "-" + document.getElementById("phone1a").value+ "-" + document.getElementById("phone1b").value,
        phone2: document.getElementById("phone2").value + "-" + document.getElementById("phone2a").value+ "-" + document.getElementById("phone2b").value,
        subservstatus: document.getElementById("subservstat").value,
        shipdes: document.getElementById("shipdes").value,
        shipnum: document.getElementById("shipnum").value,
        shipname: document.getElementById("shipname").value,
        fromDate: document.getElementById("fromdate").value,
        endDate: document.getElementById("todate").value,
        usnRate: document.getElementById("usnrate").value,
        usnRateName: document.getElementById("usnratename").value,
        isscat: document.getElementById("isscat").value,
        issdiag: document.getElementById("issdiag").value,
        symstart: document.getElementById("symstart").value,
        yod: document.getElementById("yod").value,
        cfwva: document.getElementById("cfwva").value,
        statiss: document.getElementById("statiss").value,
        vadis: document.getElementById("vadis").value,
        disrat: document.getElementById("disrat").value,
        spouse: document.getElementById("spouse").value,
        exposure: document.getElementById("exposure").value,
        exposureDate: document.getElementById("expDate").value,
        sagCallBack: document.getElementById("sagcallback").value,
        ussVimem: document.getElementById("ussvimem").value,
        vfwMem: document.getElementById("vfwmem").value,
        davMem: document.getElementById("davmem").value,
        frMem: document.getElementById("fleetresnum").value,
        subServStatusID: document.getElementById("subservsstatID").value
    }
    console.log(dataList);

    fetch("/form/data",{
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataList)
    }).then(response => {
        return response.text();
    })
        .then(data => {
            console.log(data);
        })
}