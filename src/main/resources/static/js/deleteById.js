function deleteContactById(id) {
    var settings = {
        "url": "http://localhost:8090/contacts/"+ id,
        "method": "DELETE",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        const elementToDelete = document.querySelector(`#${CSS.escape(id)}`)
        elementToDelete.parentNode.removeChild(elementToDelete)
    });
}