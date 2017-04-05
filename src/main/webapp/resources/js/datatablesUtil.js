function updateTable(added, isTabPressed, orderId) {
    $.get(ajaxUrl, updateTableByData);
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function showAddUserModal() {
    $('#id').val(0);
    $('#firstName').val('');
    $('#lastName').val('');
    $('#login').val('');
    $('#password').val('');
    $('#email').val('');

    $('#editRow').modal();
}

function renderBlockUnblockBtn(data, type, row) {
    return row.blocked === false ?
    '<a class="btn btn-xs btn-danger" onclick="block(' + row.id + ');">block</a>' :
    '<a class="btn btn-xs btn-success" onclick="unblock(' + row.id + ');">unblock</a>';
}

function renderUpdateUserBtn(data, type, row) {
    // debugger;
    return '<a class="btn btn-xs btn-primary" onclick="showUpdateUserModal(' +
        row.id + ', \'' +
        row.firstName + '\', \'' +
        row.lastName + '\', \'' +
        row.login + '\', \'' +
        row.email +
        '\');">update</a>';
}

function showUpdateUserModal(id, firstName, lastName, login, email) {
    $('#modalTitle').html('Update');
    $('#id').val(id);
    $('#firstName').val(firstName);
    $('#lastName').val(lastName);
    $('#login').val(login);
    $('#password').val('');
    $('#email').val(email);
    $('#editRow').modal();
}

function renderDeleteBtn(data, type, row) {
    return '<a class="btn btn-xs btn-danger" onclick="deleteEntity(' + row.id + ');">delete</a>';
}

function deleteEntity(id) {
    // debugger;
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function (data) {
            updateTable();
            successNoty('common.deleted');
        }
    })
}

function block(id) {
    $.ajax({
        url: ajaxUrl + id + '/block',
        type: 'POST',
        success: function (data) {
            updateTable();
            successNoty('common.saved');
        }
    })
}

function unblock(id) {
    $.ajax({
        url: ajaxUrl + id + '/unblock',
        type: 'POST',
        success: function (data) {
            updateTable();
            successNoty('common.saved');
        }
    })
}

function save(){
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: $('#detailsForm').serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('common.saved');
        }
    });
}

function successNoty(key) {
    closeNoty();
    noty({
        text: i18n[key],
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}



function onProjectTableReady() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function renderBlockUnblockCommentBtn(commentId, blocked) {
    if (role === 'admin') {
        return blocked === false ?
        '<a class="btn btn-xs btn-danger" onclick="blockComment(' + commentId + ');">block comment</a>' :
        '<a class="btn btn-xs btn-success" onclick="unblockComment(' + commentId + ');">unblock comment</a>' ;
    }
}

function blockComment(commentId) {
    $.ajax({
        url: ajaxUrl + 'block-comment/' + commentId,
        type: 'POST',
        success: function (data) {
            updateTable();
            successNoty('common.saved');
        }
    })

}

function unblockComment(commentId) {
    $.ajax({
        url: ajaxUrl + 'unblock-comment/' + commentId,
        type: 'POST',
        success: function (data) {
            updateTable();
            successNoty('common.saved');
        }
    })
}