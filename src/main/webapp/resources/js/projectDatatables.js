var ajaxUrl = 'ajax/profile/projects/';
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
            {"data": "id", "orderable": false, "visible": true, "className": "project-id"},
            {"data": "clientId", "orderable": false, "visible": true, "className": "project-client-id"},
            {"data": "name", "orderable": false, "visible": true, "className": "project-name"},
            {"data": "description", "orderable": false, "className": "project-description"},
            {"data": "payment", "orderable": false, "className": "project-payment"},
            {"data": "clientLastName", "orderable": false, "className": "project-client-last-name"},
            {"data": "status", "orderable": false,"className": "project-status editable"},
            {"data": "requiredSkills", "orderable": false, "className": "project-required-skills"},
            {
                "defaultContent": "",
                "orderable": false,
                "render": role === 'admin'  ? renderBlockUnblockProjectBtn :
                            (role === 'freelancer' ? renderApplyForProjectBtn : "")
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderUpdateProjectBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": role === 'admin'  ? renderDeleteProjectBtn : ""
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

    datatableApi.on('draw.dt', function () {
        showAppliedFreelancersAndComments();

        // onOrderTableReady();

    });

    datatableApi.on('click focusin', 'td.project-status', function () {

        var $this = $(this);
        var tr = $this.closest('tr');
        var row = datatableApi.row(tr);

        if (role === 'admin' || (role === 'client' && userId === row.data().clientId)) {

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

function showAppliedFreelancersAndComments() {
    datatableApi.rows().every(function (rowIdx, tableLoop, rowLoop) {
        var row = this;
        var tr = row.node();
        var appliedFreelancerTos = row.data().appliedFreelancerTos;
        var commentTos = row.data().commentTos;
        var projectId = row.data().id;
        // debugger;
        row.child(
            buildAppliedFreelancerList(appliedFreelancerTos, projectId, row.data().clientId, row)
            + buildCommentList(commentTos)
            + renderAddCommentField(projectId),
            'child-row'
        ).show();
    })
}

function renderAddCommentField(projectId){
    return  '<form id="commentForm' + projectId + '">' +
            '<p><b>Add comment:</b></p>' +
            '<p><textarea rows="2" cols="60" name="text"></textarea></p>' +
            '<button class="btn btn-primary" type="button" onclick="saveComment('+ projectId + ')">submit comment</button>' +
            '</form>';
}

function saveComment(projectId){
    $.ajax({
        type: "POST",
        url: ajaxUrl + projectId + '/add-comment',
        data: $('#commentForm' + projectId).serialize(),
        success: function () {
            updateTable();
            successNoty('common.saved');
        }
    });
}

function buildAppliedFreelancerList(appliedFreelancerTos, projectId, projectClientId, row) {
    /**
     * Building ChildRow - list of applied freelancers
     **/

    //Making templator working properly with JSP
    // debugger;
    if (appliedFreelancerTos.length != 0) {
        _.templateSettings = {
            evaluate: /{{([\s\S]+?)}}/g,    // {{ }}  :  <% %>
            interpolate: /{{=([\s\S]+?)}}/g // {{= }} :  <%= %>
        };
        // debugger;
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
    if (role === 'client' && userId === projectClientId) {
        return '<a class="btn btn-xs btn-success" onclick="approveAppliedFreelancer(' + projectId + ', ' + appliedFreelancerId + ');">Approve freelancer for project</a>';
    }
}

function approveAppliedFreelancer(projectId, freelancerId) {
    if (confirm('Are you sure you want approve this freelancer?')) {
        $.ajax({
            url: ajaxUrl + projectId +  '/approve-freelancer/' + freelancerId,
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

    if (role === 'admin')
        $('#projectClientId').val('');

    $('#projectName').val('');
    $('#projectDescription').val('');
    $('#projectPayment').val('');
    // $('#projectStatus').val(projectStatus);
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

    //Making templator working properly with JSP
    if (commentTos.length != 0) {
        _.templateSettings = {
            evaluate: /{{([\s\S]+?)}}/g,    // {{ }}  :  <% %>
            interpolate: /{{=([\s\S]+?)}}/g // {{= }} :  <%= %>
        };

        var tmpl = _.template($('#commentList').html());
        return tmpl({
            commentTos
        });
    }
    return "";

}

function renderBlockUnblockProjectBtn(data, type, row) {
    return row.blocked === false ?
        '<a class="btn btn-xs btn-danger" onclick="block(' + row.id + ');">block project</a>' :
        '<a class="btn btn-xs btn-success" onclick="unblock(' + row.id + ');">unblock project</a>' ;
}

function renderDeleteProjectBtn(data, type, row) {
    return '<a class="btn btn-xs btn-danger" onclick="deleteEntity(' + row.id + ');">delete project</a>';
}

function renderUpdateProjectBtn(data, type, row) {

    if (role === 'admin' || (role === 'client' && userId === row.clientId)) {
        return '<a class="btn btn-xs btn-primary" onclick="showUpdateProjectModal(' +
            row.id + ', ' +
            row.clientId + ', \'' +
            row.name + '\', \'' +
            row.description + '\', ' +
            row.payment + ', \'' +
            row.requiredSkills +
            '\');">update project</a>';
    }
    return "";
}

function showUpdateProjectModal(projectId, projectClientId, projectName, projectDescription, projectPayment, projectRequiredSkills) {

    if (role === 'admin')
        $('#projectClientId').val(projectClientId);

    $('#modalTitle').html('Update project');
// todo get info from row by projectId
    $('#projectId').val(projectId);
    $('#projectName').val(projectName);
    $('#projectDescription').val(projectDescription);
    $('#projectPayment').val(projectPayment);
    // $('#projectStatus').val(projectStatus);
    $('#projectRequiredSkills').val(projectRequiredSkills);
    $('#editRow').modal();
}
