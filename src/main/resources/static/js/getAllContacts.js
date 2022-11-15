const tableBody = document.querySelector('#table')

function getAllContacts() {
    var settings = {
        "url": "http://localhost:8090/contacts",
        "method": "GET",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
    };

    $.ajax(settings).done(function (response) {
        if (response !== undefined){
            for (let i = 0; i< response.length; i++){
                createContact(response[i])
            }
        }
    });
}

function createContact(data){
    const row = document.createElement('tr')
    row.id = data.id
    const id = document.createElement('td')
    id.innerHTML = data.id
    const name = document.createElement('td')
    name.classList.add('name')
    name.innerHTML = data.name
    const number = document.createElement('td')
    number.classList.add('number')
    number.innerHTML = data.number

    const actions = document.createElement('td')
    const actionList = document.createElement('ul')
    actionList.classList.add("action-list")
    const editButton = document.createElement('li')
    editButton.innerHTML = "<a href=\"#\" data-tip=\"edit\"><i class=\"fa fa-edit\"></i></a>"
    editButton.addEventListener('click', function (){
        showEditForm(data.id)
    }, true)
    const deleteButton = document.createElement('li')
    deleteButton.innerHTML = "<a href=\"#\" data-tip=\"delete\"><i class=\"fa fa-trash\"></i></a>"
    deleteButton.addEventListener('click', function (){
        deleteContactById(data.id)
    }, true)

    actionList.appendChild(editButton)
    actionList.appendChild(deleteButton)
    actions.appendChild(actionList)
    row.appendChild(id)
    row.appendChild(name)
    row.appendChild(number)
    row.appendChild(actions)
    tableBody.appendChild(row)
}
getAllContacts()



