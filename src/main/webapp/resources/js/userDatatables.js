var datatableApi;

// function showPortfolio(freelancerId) {
//     todo just get??
    // $.ajax({
    //     url: 'freelancer-portfolio/' + freelancerId,
    //     type: "GET",
    // });
// }

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
            {"data": "id", "orderable": false, "visible": true, "className": ""},
            {"data": "firstName", "orderable": false, "visible": true, "className": ""},
            {"data": "lastName", "orderable": false, "visible": true, "className": ""},
            {"data": "login", "orderable": false, "className": ""},
            // {"data": "password", "orderable": false, "className": ""},
            {"data": "email", "orderable": false, "className": ""},
            {
                "data": "skills",
                "visible": entityName === 'freelancer',
                "orderable": false,
                "className": "",
                "defaultContent": "<i>Not set</i>"
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderPortfolioBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": role === 'admin' ? renderBlockUnblockBtn : ""
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": role === 'admin' ? renderUpdateUserBtn : ""
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": role === 'admin' ? renderDeleteBtn : ""
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

