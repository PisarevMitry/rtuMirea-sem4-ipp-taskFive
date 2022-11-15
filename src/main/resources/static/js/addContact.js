let createButton = document.querySelector('#create')
createButton.addEventListener('click', addContact, true)

function addContact(){
    let nameInput = document.querySelector('#name')
    let numberInput = document.querySelector('#number')
    var settings = {
        "url": "http://localhost:8090/addContact",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
            "name": nameInput.value,
            "number": numberInput.value
        }),
    };

    $.ajax(settings).done(function (response) {
        createContact(response);
    });
}