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
            {"data": "name", "orderable": false},
            {"orderable": false, "render": renderUpdateSkillBtn},
            {"orderable": false, "render": renderDeleteBtn}
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
        '\')">' + i18n['common.update'] + '</a>';
}

function showUpdateSkillModal(id, name) {
    $('#modalTitle').html(i18n['common.update'] + entityName);
    $('#id').val(id);
    $('#name').val(name);
    $('#editRow').modal();
}

