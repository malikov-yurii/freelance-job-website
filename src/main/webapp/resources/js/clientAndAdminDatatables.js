var datatableApi;

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
            // {"data": "password", "orderable": false, "className": ""},
            {"data": "email", "orderable": false, "className": ""},
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
        ]
        // "initComplete": onOrderTableReady,
    });
});

