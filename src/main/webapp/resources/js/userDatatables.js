var datatableApi;

function renderPortfolioBtn(data, type, row) {
    return '<a class="btn btn-xs btn-primary" href="freelancer-portfolio/' + row.id + '">portfolio<a/>';
}

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
                "data": "skills",
                "visible": entityName === 'freelancer',
                "orderable": false,
                "defaultContent": "<i>Not set</i>"
            },
            {"orderable": false, "render": renderPortfolioBtn},
            {"orderable": false, "render": authorizedUserRole === 'admin' ? renderBlockUnblockBtn : ""},
            {"orderable": false, "render": authorizedUserRole === 'admin' ? renderUpdateUserBtn : ""},
            {"orderable": false, "render": authorizedUserRole === 'admin' ? renderDeleteBtn : ""}
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

