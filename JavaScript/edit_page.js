window.addEventListener("DOMContentLoaded", () => {
    window.addEventListener("load", main);
    document.getElementById("submit").addEventListener("click", sendData)

})


function sendData(){

    let data = {
        filename: document.getElementById("edit-page").value,
        contents: quill.root.innerHTML
    };

    fetch("/edit/content", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        return response.text()
    }).then(data => {
        if(data == "Success!") {
            window.location.href = document.getElementById("edit-page").value + ".html";
        }
    })
}

function submitData(){
    var editContainer = document.getElementsByClassName("ql-editor")[0];
    var content = document.getElementById("content");

    content.innerHTML = editContainer.innerHTML;


}

function main() {
    var pageNav = ["index",
        "advisory_committee",
        "affiliates",
        "chemicals",
        "equipment",
        "form",
        "goals",
        "library_resources",
        "msds",
        "officers",
        "partners",
        "radiation",
        "registry_analyses",
        "research_reports",
        "short_history_submarines",
        "submariner_registry",
        "submarines_our_boats",
        "subs_are_different",
        "terms",
        "the_registry",
        "va_forms",
        "vso_reference_guide_home",
        "test",
        "test2"];
    let nav = [];
    let dropDown = document.getElementById("edit-page");
    for(let i = 0; i < pageNav.length; i++){
        nav += "<option value='" + pageNav[i] + "'>" + pageNav[i] + "</option>";
    }
    dropDown.innerHTML += nav;


}