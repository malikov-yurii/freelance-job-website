var ajaxUrl = 'ajax/profile/admins/';

var datatableApi;

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "searching": false,
        "pagingType": "full_numbers",
        "paging": true,
        "info": true,
        "columns": [
            {"data": "id", "orderable": false, "visible": true},
            {"data": "firstName", "orderable": false, "visible": true},
            {"data": "lastName", "orderable": false, "visible": true},
            {"data": "login", "orderable": false},
            {"data": "email", "orderable": false},
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderBlockUnblockBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderUpdateUserBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDeleteBtn
            }
        ],
        "initComplete": onProjectTableReady,
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "autoWidth": false
    });
});

