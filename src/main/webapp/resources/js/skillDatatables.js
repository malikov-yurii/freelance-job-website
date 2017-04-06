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
            {"data": "name", "orderable": false, "visible": true, "className": ""},
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderUpdateSkillBtn
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

function showAddSkillModal() {
    $('#modalTitle').html('Add new ' + entityName);
    $('#id').val(0);
    $('#name').val('');
    $('#editRow').modal();
}

function renderUpdateSkillBtn(data, type, row) {
    return '<a class="btn btn-xs btn-primary" onclick="showUpdateSkillModal(' +
        row.id + ', \'' +
        row.name +
        '\')">update</a>';
}

function showUpdateSkillModal(id, name) {
    $('#modalTitle').html('Update ' + entityName);
    $('#id').val(id);
    $('#name').val(name);
    $('#editRow').modal();
    }

