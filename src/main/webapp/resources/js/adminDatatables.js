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
            {"data": "id", "orderable": false},
            {"data": "firstName", "orderable": false},
            {"data": "lastName", "orderable": false},
            {"data": "login", "orderable": false},
            {"data": "email", "orderable": false},
            {
                "orderable": false,
                "render": renderBlockUnblockBtn
            },
            {
                "orderable": false,
                "render": renderUpdateUserBtn
            },
            {
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

