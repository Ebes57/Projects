window.addEventListener("DOMContentLoaded", () => {
    window.addEventListener("load", fetchAndExtractDiv)
})

function getData(content) {
    var pageName = document.getElementById("pageName").value;

    console.log(content);
    var data = {
        fileName: pageName,
        content: content
    };
    createNewPage(data)
    //navBar(pageName)
}

function createNewPage(data) {
    fetch("/add/page", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        return response.text()
    }).then(data => {
        console.log(data)
    })
}

function navBar() {
    let mainHeader = document.getElementById("main-header").value;

    if (mainHeader == "yes") {

    }else if(mainHeader == "no"){
        getNavBar()
    }
    //else subheading
    //fetch request to write to nav template
}


// Function to fetch and extract the target div
async function fetchAndExtractDiv() {
    try {
        // Fetch the content of the other HTML file
        const response = await fetch('navigation_bar.html');
        const text = await response.text();

        // Parse the fetched HTML content
        const parser = new DOMParser();
        const doc = parser.parseFromString(text, 'text/html');

        // Extract the target div from the parsed HTML
        const targetDiv = doc.getElementById('main-headers').textContent;

        // Display the extracted content in the current document
        console.log(targetDiv);
        return formatList(targetDiv);
    } catch (error) {
        console.error('Error fetching and extracting div:', error);
    }
}

function formatList(list1){
    let sub = document.getElementById("sub-header-options");
    let temp;
    let html = '        <label for="sub-headers">Which header should it sit under?</label><br>\n' +
        '        <select id="sub-headers" name="sub-headers">\n';
    let holder = [];
    list1 = list1.split(/\n/);
    list1 = list1.filter(item => item)
    for(let i = 0; i < list1.length; i++){
        temp = formatString(list1[i])
        if(temp != null){
            holder += temp;
        }
    }
    console.log(holder.split(","));
    holder = holder.split(",");
    holder = holder.filter(item => item)
    for(let i = 0; i < holder.length; i++){
        html += '<option value="'+ holder[i] +'">' + holder[i] + '</option>\n';
    }
    html += '        </select><br>';
    console.log(html);

    sub.innerHTML = html;
}

function formatString(str1){
    str1 = str1.replace(/\//g, ' ');
    //console.log(str1);
    str1 = str1.replace(/[^\w\s]/gi, '');
    //console.log(str1);
    str1 = str1.replace(/^\s+/, '');
    //console.log(str1);
    str1 = str1.replace(/\s+/g, ' ');
    //console.log(str1);
    str1 = str1.replace(/ /g, '_')
    str1 = str1.toLocaleLowerCase();
    //console.log(str1);
    str1 += ",";
    return str1;

}
async function getNavBar(){
    let pageName = document.getElementById("pageName").value;
    // Fetch the content of the other HTML file
    const response = await fetch('navigation_bar.html');
    const text = await response.text();

    // Parse the fetched HTML content
    const parser = new DOMParser();
    const doc = parser.parseFromString(text, 'text/html');

    // Extract the target div from the parsed HTML
    let mainHeaders = doc.getElementById('main-headers').textContent;

    let navBar = document.getElementById("sub-headers").value;
    let holder = [];
    let temp = "";
    let html = "<div class=\"nav\">\n" +
        "        <ul class=\"nav-list\">\n";

    mainHeaders = mainHeaders.split(/\n/);
    mainHeaders = mainHeaders.filter(item => item)
    //console.log(mainHeaders);
    let x = 0;
    mainHeaders = mainHeaders.filter(item => item)
    for(let i = 0; i < mainHeaders.length; i++){
        temp = formatString(mainHeaders[i])
        if(temp != null){
            holder += temp;
        }
    }
    holder = holder.split(",");
    holder = holder.filter(item => item)
    console.log(holder);

    let subHeaders;

    for(let x = 0; x < holder.length; x++){
        while(holder[x] == undefined || holder[x] == ""){
            x++;
            if(x >= mainHeaders.length){
                break;
            }
        }
        console.log("sub-headers-" + holder[x]);
        subHeaders = doc.getElementById("sub-headers-" + holder[x]).textContent;
        subHeaders = subHeaders.split(/\n/);
        subHeaders = subHeaders.filter(item => item)
        //console.log("sub-headers-about");
        //console.log("main-headers-" + holder[x]);
        //console.log(subHeaders);
        html += '<li class=\\"dropdown\\">\n' +
            '<a href="#">' +
            mainHeaders[x] +
            '</a>\n' +
            '<ul class="dropdown-content">\n'
        console.log(pageName);
        if(navBar == holder[x]){
            console.log(pageName)
            console.log(formatString(pageName))
            html += '<li><a href="' + formatString(pageName).replace(",", "") + '.html">' + pageName + '</a></li>\n';
        }
        for(let i = 0; i < subHeaders.length; i++){
            while(subHeaders[i] == undefined){
                i++;
                if(i > subHeaders.length){
                    break;
                }
            }
            temp = formatString(subHeaders[i]);

            //console.log(temp);
            html += '<li><a href="' + temp.replace(",", "") + '.html">' + subHeaders[i] + '</a></li>\n';
        }
        html += '</ul>\n';

    }
    console.log(html);
    let data = {
        html: html
    };
    fetch("/add/nav", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}