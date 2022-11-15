function editContact(id) {
    let currentElement = document.querySelector(`#${CSS.escape(id)}`)
    var settings = {
        "url": "http://localhost:8090/contacts/"+ id,
        "method": "PUT",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
            "id": id,
            "name": document.querySelector('#nameInput').value,
            "number": document.querySelector('#numberInput').value
        }),
    };

    $.ajax(settings).done(function (response) {
        currentElement.querySelector('.name').innerHTML = response.name
        currentElement.querySelector(".number").innerHTML = response.number
        currentElement.style.display = 'table-row'
        currentElement.parentNode.removeChild(currentElement.nextSibling)
    });
}
function showEditForm(id) {
    const contactToEdit = document.querySelector(`#${CSS.escape(id)}`)
    contactToEdit.style.display = 'none'
    const editForm = document.createElement('tr')
//         < tr >
//         < td > id < /td>
//     <td><input className="form-control" type="text" id="name" placeholder="Введите имя"
//                required></td>
//     <td><input className="form-control" type="text" id="number" placeholder="Введите номер"
//                required></td>
//     <td>
//         <ul className="action-list">
//             <li id="create"><a href="#" data-tip="add"><i className="fa fa-plus"></i></a></li>
//         </ul>
//     </td>
// </tr>
    editForm.innerHTML = "<td>" + id + "</td>\n" +
        "                            <td><input class=\"form-control\" type=\"text\" id=\"nameInput\" placeholder=\"Введите имя\"\n" +
        "                                       value='" + contactToEdit.querySelector('.name').textContent + "' required></td>\n" +
        "                            <td><input class=\"form-control\" type=\"text\" id=\"numberInput\" placeholder=\"Введите номер\"\n" +
        "                                       value='" + contactToEdit.querySelector('.number').textContent + "' required></td>\n" +
        "                            <td>\n" +
        "                                <ul class=\"action-list\">\n" +
        "                                    <li><a href=\"#\" data-tip=\"close\"><i class=\"fa fa-close cancel\"></i></a></li>\n" +
        "<li ><a href=\"#\" data-tip=\"update\"><i class=\"fa fa-check update\"></i></a></li>" +
        "                                </ul>\n" +
        "                            </td>"
    const cancelButton = editForm.querySelector('.cancel')
    cancelButton.addEventListener('click', cancelEdit, true)
    const editButton = editForm.querySelector('.update')
    editButton.addEventListener('click', function () {
        editContact(id)
    }, true)
    contactToEdit.parentNode.insertBefore(editForm, contactToEdit.nextSibling)
}

function cancelEdit(e) {
    const button = e.currentTarget
    let editForm = button.parentNode.parentNode.parentNode.parentNode.parentNode
    let currentElement = editForm.previousSibling
    currentElement.style.display = 'table-row'
    editForm.parentNode.removeChild(editForm)
}