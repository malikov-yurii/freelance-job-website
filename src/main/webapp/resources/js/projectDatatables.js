var ajaxUrl = 'ajax/profile/projects/';
var datatableApi;

// Making templator working properly with JSP
_.templateSettings = {
    evaluate: /{{([\s\S]+?)}}/g,
    interpolate: /{{=([\s\S]+?)}}/g
};

$(function () {

    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "searching": true,
        "pagingType": "full_numbers",
        "pageLength": 10,
        "paging": true,
        "info": true,
        "columns": [
            {"data": "id", "orderable": false, "className": "project-id"},
            {"data": "clientId", "orderable": false, "className": "project-client-id"},
            {"data": "name", "orderable": false, "className": "project-name"},
            {"data": "description", "orderable": false, "className": "project-description"},
            {"data": "payment", "orderable": false, "className": "project-payment"},
            {"data": "clientLastName", "orderable": false, "className": "project-client-last-name"},
            {"data": "status", "orderable": false, "className": "project-status editable"},
            {"data": "requiredSkills", "orderable": false, "className": "project-required-skills"},
            {
                "orderable": false,
                "render": authorizedUserRole === 'admin' ? renderBlockUnblockProjectBtn :
                    (authorizedUserRole === 'freelancer' ? renderApplyForProjectBtn : "")
            },
            {
                "orderable": false,
                "render": renderUpdateProjectBtn
            },
            {
                "orderable": false,
                "render": authorizedUserRole === 'admin' ? renderDeleteProjectBtn : ""
            }
        ],
        "createdRow": onCreatedParentRow,
        "initComplete": onProjectTableReady,
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "autoWidth": false
    });

    datatableApi.on('draw.dt', function () {
        showAppliedFreelancersAndComments();
    });

    datatableApi.on('click focusin', 'td.project-status', function () {

        var $this = $(this);
        var tr = $this.closest('tr');
        var row = datatableApi.row(tr);

        if (authorizedUserRole === 'admin' || (authorizedUserRole === 'client' && authorizedUserId === row.data().clientId)) {

            /**
             * Autocomplete of 'project-status'
             **/
            $this.autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: ajaxUrl + 'autocomplete-project-status',
                        type: "POST",
                        dataType: "json",
                        success: function (data) {
                            response(data);
                        }
                    });
                }
                , select: function (event, ui) {
                    var rowDataId = row.data().id;
                    $this.closest('.project-status').html(ui.item.label);

                    $.ajax({
                        url: ajaxUrl + rowDataId + '/update-project-status',
                        type: "POST",
                        data: 'projectStatus=' + ui.item.value,
                    });

                    $this.blur();
                    return false;
                }
                , minLength: 0
            });

            $this.autocomplete("search");
        }
    });
});

function renderAddCommentField(projectId) {
    return '<form class="comments-form" id="commentForm' + projectId + '">' +
        '<p><b>Add comment:</b></p>' +
        '<p><textarea id="newCommentTextArea' + projectId + '" rows="2" cols="60" name="text"></textarea></p>' +
        '<button class="btn btn-primary" type="button" onclick="saveNewComment(' + projectId + ')">submit comment</button>' +
        '</form>';
}

function saveNewComment(projectId) {
    $.ajax({
        type: "POST",
        url: 'ajax/profile/comments',
        data: {"commentText": $("#newCommentTextArea" + projectId).val(), "projectId": projectId},
        success: function () {
            updateTable();
            successNoty('common.saved');
        }
    });
}

function renderUpdateCommentBtn(commentId, commentText) {
    return authorizedUserRole === 'admin' ? '<a class="btn btn-xs btn-update-comment" title="Update comment" onclick="showUpdateCommentModal(' + commentId + ', \'' + commentText + '\')"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>' : "";
}

function showUpdateCommentModal(commentId, commentText) {
    $('#commentModalTitle').html('Update comment');
    $('#id').val(commentId);
    $('#commentProjectId').val(0);
    $('#commentText').val(commentText);
    $('#commentEditRow').modal();
}

function updateComment() {
    $.ajax({
        type: "POST",
        url: 'ajax/profile/comments',
        data: $('#commentDetailsForm').serialize(),
        success: function () {
            $('#commentEditRow').modal('hide');
            updateTable();
            successNoty('common.saved');
        }
    });
}

function renderDeleteCommentBtn(commentId) {
    if (authorizedUserRole === 'admin')
        return '<a class="btn btn-xs btn-delete-comment" onclick="deleteComment(' + commentId + ')" title="Delete comment"><i class="fa fa-trash" aria-hidden="true"></i></a>';
}

function deleteComment(id) {
    if (confirm('Are you sure you want to delete comment?')) {
        $.ajax({
            url: 'ajax/profile/comments/' + id,
            type: 'DELETE',
            success: function (data) {
                updateTable();
                successNoty('common.deleted');
            }
        })
    }
}

function renderBlockUnblockCommentBtn(commentId, blocked) {
    if (authorizedUserRole === 'admin') {
        return blocked === false ?
        '<a class="btn btn-xs btn-block-comment" onclick="blockComment(' + commentId + ');" title="Block comment"><i class="fa fa-ban" aria-hidden="true"></i></a>' :
        '<a class="btn btn-xs btn-unblock-comment" onclick="unblockComment(' + commentId + ');" title="Unblock comment"><i class="fa fa-unlock-alt" aria-hidden="true"></i></a>';
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

function showAppliedFreelancersAndComments() {
    datatableApi.rows().every(function (rowIdx, tableLoop, rowLoop) {
        var row = this;
        var tr = row.node();
        var appliedFreelancerTos = row.data().appliedFreelancerTos;
        var commentTos = row.data().commentTos;
        var projectId = row.data().id;
        row.child(
            '<div class="child-content">' +
            buildAppliedFreelancerList(appliedFreelancerTos, projectId, row.data().clientId, row)
            + buildCommentList(commentTos)
            + renderAddCommentField(projectId) +
            '</div>',
            'child-row'
        ).show();
    })
}

function buildAppliedFreelancerList(appliedFreelancerTos, projectId, projectClientId, row) {
    /**
     * Building ChildRow - list of applied freelancers
     **/
    if (appliedFreelancerTos.length != 0) {
        var tmpl = _.template($('#appliedFreelancerList').html());
        return tmpl({
            appliedFreelancerTos,
            projectId,
            projectClientId,
            row
        });
    }
    return "";
}

function renderApproveFreelancerBtn(projectId, appliedFreelancerId, projectClientId) {
    if (authorizedUserRole === 'client' && authorizedUserId === projectClientId) {
        return '<a class="btn btn-xs btn-success" onclick="approveAppliedFreelancer(' + projectId + ', ' + appliedFreelancerId + ');">Approve freelancer for project</a>';
    }
}

function approveAppliedFreelancer(projectId, freelancerId) {
    if (confirm('Are you sure you want approve this freelancer?')) {
        $.ajax({
            url: ajaxUrl + projectId + '/approve-freelancer/' + freelancerId,
            type: 'POST',
            success: function () {
                updateTable();
                successNoty('common.deleted');
            }
        });
    }
}

function addProject() {
    $('#modalTitle').html('Add new project');
    $('#projectId').val(0);

    if (authorizedUserRole === 'admin')
        $('#projectClientId').val('');

    $('#projectName').val('');
    $('#projectDescription').val('');
    $('#projectPayment').val('');
    $('#projectRequiredSkills').val('');

    $('#editRow').modal();

}

function renderApplyForProjectBtn(data, type, row) {
    switch (row.applicationStatus) {
        case 'NOT_LOOKING_FOR_A_FREELANCER' :
            return '<b>Application closed</b>';
            break;
        case 'ALLOWED_HAS_SKILLS' :
            return '<a class="btn btn-xs btn-success" onclick="applyForProject(event, ' + row.id + ');">Apply for project</a>';
            break;
        case 'ALREADY_APPLIED' :
            return '<a class="btn btn-xs btn-danger" onclick="discardApplicationForProject(event, ' + row.id + ');">Discard application</a>';
            break;
        case 'NOT_ALLOWED_LACK_OF_SKILLS' :
            return '<b>Lack of skills</b>';
            break;
    }
}

function applyForProject(e, id) {
    $.ajax({
        url: ajaxUrl + id + '/apply-for-project',
        type: 'POST',
        success: function (data) {
            $(e.target).parent().html('<a class="btn btn-xs btn-danger" onclick="discardApplicationForProject(event, ' + id + ');">Discard application</a>');
        }
    })
}

function discardApplicationForProject(e, id) {
    $.ajax({
        url: ajaxUrl + id + '/discard-application-for-project',
        type: 'POST',
        success: function (data) {
            $(e.target).parent().html('<a class="btn btn-xs btn-success" onclick="applyForProject(event, ' + id + ');">Apply for project</a>');
        }
    });
}

function buildCommentList(commentTos) {
    /**
     * Building part of ChildRow - list of comments
     **/

    // Making templator working properly with JSP
    if (commentTos.length != 0) {

        var tmpl = _.template($('#commentList').html());
        return tmpl({
            commentTos
        });
    }
    return "";
}

function renderBlockUnblockProjectBtn(data, type, row) {
    return row.blocked === false ?
    '<a class="btn btn-xs btn-danger btn-project" onclick="block(' + row.id + ');" title="Block Project"><i class="fa fa-ban" aria-hidden="true"></i></a>' :
    '<a class="btn btn-xs btn-success btn-project" onclick="unblock(' + row.id + ');" title="Unblock Project"><i class="fa fa-check" aria-hidden="true"></i></a>';
}

function renderDeleteProjectBtn(data, type, row) {
    return '<a class="btn btn-xs btn-danger btn-project" onclick="deleteEntity(' + row.id + ');" title="Delete Project"><i class="fa fa-trash-o" aria-hidden="true"></i></a>';
}

function renderUpdateProjectBtn(data, type, row) {
    if (authorizedUserRole === 'admin' || (authorizedUserRole === 'client' && authorizedUserId === row.clientId)) {
        return '<a class="btn btn-xs btn-primary btn-project" onclick="showUpdateProjectModal(' +
            row.id + ', ' +
            row.clientId + ', \'' +
            row.name + '\', \'' +
            row.description + '\', ' +
            row.payment + ', \'' +
            row.requiredSkills +
            '\');"' +
            ' title="Update Project"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>';
    }
    return "";
}

function showUpdateProjectModal(projectId, projectClientId, projectName, projectDescription, projectPayment, projectRequiredSkills) {

    if (authorizedUserRole === 'admin')
        $('#projectClientId').val(projectClientId);

    $('#modalTitle').html('Update project');
    $('#projectId').val(projectId);
    $('#projectName').val(projectName);
    $('#projectDescription').val(projectDescription);
    $('#projectPayment').val(projectPayment);
    $('#projectRequiredSkills').val(projectRequiredSkills);
    $('#editRow').modal();
}

function onCreatedParentRow(row, data, rowIndex) {
    $row = $(row);
    $row.addClass('parent-row');
}