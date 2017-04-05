var ajaxUrl = 'ajax/profile/clients/';

var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "searching": false,
        // "pagingType": "full_numbers",
        "paging": false,
        "info": true,
        "columns": [
            {"data": "id", "orderable": false, "visible": true, "className": ""},
            {"data": "firstName", "orderable": false, "visible": true, "className": ""},
            {"data": "lastName", "orderable": false, "visible": true, "className": ""},
            {"data": "login", "orderable": false, "className": ""},
            {"data": "password", "orderable": false, "className": ""},
            {"data": "email", "orderable": false, "className": ""},
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderBlockUnblockClientBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderUpdateClientBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDeleteClientBtn
            }
        ],
        "initComplete": onProjectTableReady,
        "order": [
            [
                0,
                "desc"
            ]
        ]
        // "initComplete": onOrderTableReady,
    });
});

function addClient() {
    $('#modalTitle').html('Add new client');
    $('#id').val(0);
    $('#firstName').val('');
    $('#lastName').val('');
    $('#login').val('');
    $('#password').val('');
    $('#email').val('');

    $('#editRow').modal();

}

function renderBlockUnblockClientBtn(data, type, row) {
    return row.blocked === false ?
    '<a class="btn btn-xs btn-danger" onclick="block(' + row.id + ');">block client</a>' :
    '<a class="btn btn-xs btn-success" onclick="unblock(' + row.id + ');">unblock client</a>';
}


function renderUpdateClientBtn(data, type, row) {
    // debugger;
    return '<a class="btn btn-xs btn-primary" onclick="showUpdateClientModal(' +
        row.id + ', \'' +
        row.firstName + '\', \'' +
        row.lastName + '\', \'' +
        row.login + '\', \'' +
        row.password +
        '\');">update client</a>';
}

function showUpdateClientModal(id, firstName, lastName, login, password) {

    $('#modalTitle').html('Update client');
    $('#id').val(id);
    $('#firstName').val(firstName);
    $('#lastName').val(lastName);
    $('#login').val(login);
    $('#password').val(password);
    $('#editRow').modal();
}

function renderDeleteClientBtn(data, type, row) {
    return '<a class="btn btn-xs btn-danger" onclick="deleteEntity(' + row.id + ');">delete client</a>';
}
