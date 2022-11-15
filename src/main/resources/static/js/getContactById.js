let searchBar = document.querySelector('#search')
let searchButton = document.querySelector('#searchButton')
searchButton.addEventListener('click',function (){
    getContact(searchBar.value)
},true)
function getContact(id) {
    var settings = {
        "url": "http://localhost:8090/findContact/"+ id,
        "method": "GET",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        while (tableBody.children.length > 1) {
            tableBody.removeChild(tableBody.lastChild)
        }
        console.log(response)
        if (response!==undefined)
        {
            createContact(response)
        }
        else
        {
            createEmptyRow()
        }
    });
}
function createEmptyRow() {
    const row = document.createElement('tr')
    row.classList.add(`text-center`)
    const text = document.createElement('td')
    text.colSpan = 4
    text.innerHTML = "Контакта с таким id не существует"
    row.appendChild(text)
    tableBody.appendChild(row)
}