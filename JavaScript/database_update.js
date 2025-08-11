window.addEventListener("DOMContentLoaded", () =>{
    window.addEventListener("load", getTableNames)
})

function getTableNames(){
    let sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'your_database_name';";
    let data = {
        sql: sql
    };

    fetch("/update-database/sql", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        return response.json()
    }).then(data => {
        console.log(data)
    })

}