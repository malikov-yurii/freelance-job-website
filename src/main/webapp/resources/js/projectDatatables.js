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
    // debugger;
    //Making templator working properly with JSP
    // debugger;
    // if (( role === 'admin' || role === 'client' && userId === projectClientId ) && appliedFreelancerTos.length != 0) {
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


// function renderDeleteBtn(row) {

    // return 'FakeBtn';
    // return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id + ');">' +
    //     '<span class="order-head-lg">Delete order</span><span class="order-head-sm">Order <i class="fa fa-times" aria-hidden="true"></i></span>' +
    //     '</a>';

// }

function renderApproveFreelancerBtn(projectId, appliedFreelancerId, projectClientId) {
    // debugger;
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
    // debugger;
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
    // debugger;

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





//   datatableApi.on('click focusin', 'td.order-status, td.order-payment-type', function () {
//     /**
//      * Autocomplete of 'order-status' and 'payment-type'
//      **/
//     var $this = $(this);
//     var tr = $this.closest('tr');
//     var row = datatableApi.row(tr);
//     var key = $this.data('key');
//     var initVal = $this.data('value');
//
//     $this.data('value', $this.text());
//     var currentVal = $this.data('value');
//
//     if ($this.hasClass('error')) $this.removeClass('error');
//
//     $this.autocomplete({
//       source: function (request, response) {
//         $.ajax({
//           url: ajaxUrl + 'autocomplete-' + key,
//           type: "POST",
//           dataType: "json",
//           success: function (data) {
//             response(data);
//           }
//         });
//       }
//       , select: function (event, ui) {
//         var rowDataId = datatableApi.row(tr).data().id;
//
//         $.ajax({
//           url: ajaxUrl + rowDataId + '/update-' + key,
//           type: "POST",
//           data: key + '=' + ui.item.value,
//           success: function () {
//             $(tr)
//               .removeClass(function (index, className) {
//                 return (className.match(/(^|\s)status-\S+/g) || []).join(' ');
//               })
//               .addClass('status-' + ui.item.value);
//           }
//         });
//
//         $this.blur();
//       }
//       , minLength: 0
//     });
//
//     $this.autocomplete("search");
//
//     // Making element to lose focus on ENTER keybutton AND preventing from typing anything in
//     $this.keydown(function (e) {
//       if (e.which == 13) {
//         e.preventDefault();
//         $this.blur();
//       } else if ((e.shiftKey && e.keyCode == 9) || e.which == 9) {
//         $this.blur();
//       } else {
//         return false;
//       }
//     });
//
//   });
//
//   datatableApi.on('focusin', '.order-product-table td,.order-status,.order-comment,.order-payment-type,.order-city,.order-post-office,.order-total-sum', function () {
//     /**
//      * Storing initial value of order-item-cell, 'status', 'payment-type', 'city', 'post-office', 'total-sum' when getting focus
//      **/
//
//     $(this).data('value', $(this).text());
//
//     // Making element to lose focus on ENTER keybutton
//     $(this).keydown(function (e) {
//       if (e.which == 13) {
//         e.preventDefault();
//         $(this).blur();
//         $(this).find('input').blur();
//       }
//     });
//   });
//
//   datatableApi.on('focusin', '.order-first-name,.order-last-name,.order-phone-number', function () {
//     /**
//      * Autocomplete of 'first-name', 'last-name', 'phone', 'city' and 'post-office'
//      **/
//
//     var $this = $(this);
//     $this.data('value', $(this).text());
//     var tr = $this.closest('tr');
//     var rowId = datatableApi.row(tr).data().id;
//
//     // Autocomplete for Order INFO
//     var key = $this.data('key');
//     $this.autocomplete({
//       source: function (request, response) {
//         $.ajax({
//           url: ajaxUrl + 'autocomplete-' + key,
//           type: "POST",
//           data: {
//             term: request.term
//           },
//           dataType: "json",
//           success: function (data) {
//             response(data);
//           }
//         });
//       }
//       , select: function (event, ui) {
//         $(tr).find('.order-customer-id').html(ui.item.customerId);
//         $(tr).find('.order-first-name').html(ui.item.firstName);
//         $(tr).find('.order-last-name').html(ui.item.lastName);
//         $(tr).find('.order-phone-number').html(ui.item.phoneNumber);
//         $(tr).find('.order-city').html(ui.item.city);
//         $(tr).find('.order-post-office').html(ui.item.postOffice);
//         $.ajax({
//           url: ajaxUrl + rowId + '/set-customer-for-order-by-customer-id',
//           type: "POST",
//           data: {
//             "customerId": ui.item.customerId
//           },
//           success: function (data) {
//             successNoty('common.saved');
//           }
//         });
//         return false; // Prevent the widget from inserting the value.
//       }
//       , focus: function (event, ui) {
//         return false; // Prevent the widget from inserting the value.
//       }
//     });
//
//     // Making element to lose focus on ENTER keybutton
//     $this.keydown(function (e) {
//       if (e.which == 13) {
//         e.preventDefault();
//         $this.blur();
//         $this.find('input').blur();
//       }
//     });
//   });
//
//   datatableApi.on('focusout', '.parent-row [class*="order-"], .order-product-table td', function () {
//     /**
//      * Making possible inline saving of order INFO and order ITEMS
//      **/
//
//     var $this = $(this);
//     var tr = $this.closest('tr');
//     var key = $this.data('key');
//     var initVal = $this.data('value');
//
//     if (key == 'quantity') {
//       $this.data('value', $this.find('input').val());
//     } else {
//       $this.data('value', $this.text());
//     }
//
//     var currentVal = $this.data('value');
//
//     if ($this.hasClass('error')) $this.removeClass('error');
//
//     if (currentVal == '') {
//       // At first check if cell is currently empty
//
//       if (initVal == '') return true; // Then check if cell already WAS empty. If so, then remove focus as evrthng is OK
//
//       simpleFailNoty();
//       $this.text(initVal).focus().addClass('error');
//
//     } else if (currentVal != initVal) {
//       // if value has changed then send it
//
//       if ($this.parents('.parent-row').length) {
//         // Request for saving order INFO
//
//         var rowDataId = datatableApi.row(tr).data().id;
//         $.ajax({
//           url: ajaxUrl + rowDataId + '/update-' + key,
//           type: 'POST',
//           data: key + '=' + currentVal,
//           success: function () {
//             successNoty('common.saved');
//           }
//         });
//
//       } else {
//         // Request for saving order ITEMS
//
//         var orderItemId = $this.closest('tr').data('order-item-id');
//         $.ajax({
//           url: ajaxUrl + orderItemId + '/update-' + key,
//           type: 'POST',
//           data: key + '=' + currentVal,
//           success: function (data) {
//             if (key === 'quantity' || key === 'price') {
//               $(tr).closest('tr.child-row').prev().find('.order-total-sum').html(data);
//             }
//             successNoty('common.saved');
//           }
//         });
//       }
//     }
//   });
//
//   datatableApi.on('click', '.orderrow-show', function () {
//     var tr = $(this).closest('tr');
//     var row = datatableApi.row(tr);
//
//     if (row.child.isShown()) {
//       row.child.hide();
//       tr.removeClass('opened');
//     } else {
//       row.child.show();
//       tr.addClass('opened');
//     }
//   });
//
//   datatableApi.on('draw.dt', function () {
//     showOrderItems();
//
//     onOrderTableReady();
//
//   });
//
//   datatableApi.on('focusin', 'tr:last-child .order-product-price', addOrderItemOnTabPressed);
//
//   $('.show-suppliers').on('click', showSuppliers);
// })
// ;
//
// function showOrderItems() {
//   datatableApi.rows().every(function (rowIdx, tableLoop, rowLoop) {
//     var row = this;
//     var tr = row.node();
//     var orderItemTos = row.data().orderItemTos;
//     var orderId = row.data().id;
//
//     row.child(buildOrderItemList(orderItemTos, orderId, row.data()), 'child-row').show();
//
//     // Autocomplete for Order ITEMS
//     var $firstTd = row.child().find('table td:first-child');
//     $firstTd.autocomplete({
//       source: function (request, response) {
//         $.ajax({
//           url: ajaxUrl + 'autocomplete-order-item-name',
//           type: "POST",
//           data: {
//             term: request.term
//           },
//           dataType: "json",
//           success: function (data) {
//             response(data);
//           }
//         });
//       }
//       , select: function (event, ui) {
//         var $this = $(this);
//         $this.html(ui.item.orderItemName);
//         $(this).nextAll('.order-product-price').html(ui.item.orderItemPrice);
//         var orderItemId = $this.closest('tr').data('order-item-id');
//
//         // debugger;
//         // Saving after autocompleted
//         $.ajax({
//           url: ajaxUrl + orderItemId + '/update-order-item-after-order-item-name-autocomplete',
//           type: 'POST',
//           data: 'price=' + ui.item.orderItemPrice + '&productId=' + ui.item.productId + '&productVariationId=' + ui.item.productVariationId + '&orderItemName=' + ui.item.orderItemName,
//           success: function (data) {
//             // debugger;
//             $(tr).find('td.order-total-sum').html(data);
//           }
//           // todo need PUT for rest??
//           // contentType: "application/json",
//           // type: "PUT",
//           // dataType: "json",
//           // data: JSON.stringify({'price' : ui.item.orderItemPrice, 'productId': ui.item.productId, 'productVariationId' : ui.item.productVariationId}),
//         });
//
//
//         return false; // Prevent the widget from inserting the value.
//       }
//       , focus: function (event, ui) {
//         $(this).html(ui.item.orderItemName);
//         return false; // Prevent the widget from inserting the value.
//       }
//     });
//   })
// }
//
// function renderDeleteOrderItemBtn(orderItemId) {
//
//   return '<a class="btn btn-xs btn-danger" onclick="deleteOrderItem(' + orderItemId + ');">x</a>';
//
// }
//
// function buildOrderItemList(orderItems, orderId, row) {
//   /**
//    * Building ChildRow - list of order ITEMS
//    **/
//
//   //Making templator working properly with JSP
//   _.templateSettings = {
//     evaluate: /{{([\s\S]+?)}}/g,    // {{ }}  :  <% %>
//     interpolate: /{{=([\s\S]+?)}}/g // {{= }} :  <%= %>
//   };
//
//   var tmpl = _.template($('#orderItemsList').html());
//   return tmpl({
//     orderItems,
//     orderId,
//     row
//   });
//
// }
//
// function deleteOrderItem(id) {
//   if (confirm('Вы уверены, что хотите удалить эту позицию?')) {
//     $.ajax({
//       url: ajaxUrl + 'order-item/' + id,
//       type: 'DELETE',
//       success: function () {
//         updateTable();
//         successNoty('common.deleted');
//       }
//     });
//   }
// }
//
// function onOrderTableReady() {
//   /**
//    * Initialising of DATATABLE completed
//    **/
//
//   makeEditable();
//
//   $('.parent-row .editable').attr('contenteditable', true);
//   if ($(window).width() < 768) {
//     $('.order-status, .order-payment-type').removeAttr('contenteditable');
//   }
//
//   $('.parent-row [class*="order-"]').each(function () {
//     var val = this.classList[0].slice(6);
//
//     $(this).data('key', val);
//   });
//
//   if (orderAdded) {
//     setTimeout(function () {
//       $('.parent-row:first td:first-child').focus();
//     }, 100)
//     orderAdded = false;
//   }
//
//   if (productAdded) {
//     setTimeout(function () {
//       $('.order-product-table[data-order-id=' + orderIdOnTab + '] tr:last-child td:first-child').focus();
//     }, 100)
//     productAdded = false;
//   }
// }
//
// function addProject() {
//   $.ajax({
//     url: 'ajax/profile/orders/',
//     type: 'POST',
//     success: function () {
//       updateTable('orderAdded');
//       successNoty('common.saved');
//     }
//   });
// }
//
// // function renderAddOrderItemBtn(data, type, row) {
// //     if (type == 'display' && $('#hasRoleAdmin').val()) {
// //         return '<a class="btn btn-xs btn-primary" onclick="addOrderItem(' + row.id + ');">' + i18n['orders.addOrderItem'] + '</a>';
// //     }
// // }
//
// function renderAddOrderItemBtn(rowId) {
//   //todo add "type" to uncomment
//   // if (type == 'display' && $('#hasRoleAdmin').val()) {
//   return '<a class="btn btn-xs btn-success" onclick="addOrderItem(' + rowId + ');">' +
//     '<span class="order-head-lg">Add order item</span><span class="order-head-sm">Product <i class="fa fa-plus" aria-hidden="true"></i></span>' +
//     '</a>';
//   // }
// }
//
// // function renderPersistOrUpdateCustomerBtn(data, type, row) {
// //     if (type == 'display' && $('#hasRoleAdmin').val()) {
// //         return '<a class="btn btn-xs btn-primary" onclick="persistOrUpdateCustomerFromOrder(' + row.id + ');">' + i18n['orders.addCustomer'] + '</a>';
// //     }
// // }
// // function renderPersistOrUpdateCustomerBtn(data, type, row) {
// // // debugger;
// //     if (type == 'display' && $('#hasRoleAdmin').val()) {
// //         return '<a class="btn btn-xs btn-primary" onclick="persistOrUpdateCustomerFromOrder(' + row.id + ',' + row.customerId + ');">+</a>';
// //     }
// //
// // }
// function renderPersistOrUpdateCustomerBtn(row) {
//   var btnText;
//   if (row.customerId === 0) {
//     btnText = '<span class="order-head-lg">Save customer to DB</span><span class="order-head-sm">Save customer</span>';
//   } else {
//     btnText = '<span class="order-head-lg">Update customer in DB</span><span class="order-head-sm">Upd. customer</span>'
//   }
//   ;
//
//   return '<a class="btn btn-xs btn-primary" onclick="persistOrUpdateCustomerFromOrder(' + row.id + ',' + row.customerId + ');">' + btnText + '</a>';
// }
//
// function addOrderItem(id, isTabPressed) {
//   $.ajax({
//     url: 'ajax/profile/orders/' + id + '/add-order-item',
//     type: 'POST',
//     success: function () {
//       updateTable('productAdded', isTabPressed, id);
//       successNoty('common.saved');
//     }
//   });
// }
//
// function persistOrUpdateCustomerFromOrder(orderId, customerId) {
//   debugger;
//   if (customerId == 0) {
//     $.ajax({
//       url: 'ajax/profile/orders/' + orderId + '/persist-customer-from-order',
//       type: 'POST',
//       success: function () {
//         updateTable();
//         successNoty('common.saved');
//       }
//     });
//   } else {
//     showUpdateCustomerModal(customerId)
//   }
// }
//
// function showUpdateCustomerModal(customerId) {
//   $('#editCustomer').modal();
//   $.ajax({
//     url: 'rest/profile/customers/' + customerId,
//     type: "GET",
//     dataType: "json",
//     success: function (data) {
//       // debugger;
//       $("#id").val(data.id);
//       $("#firstName").val(data.name);
//       $("#lastName").val(data.lastName);
//       $("#phoneNumber").val(data.phoneNumber);
//       $("#city").val(data.city);
//       $("#postOffice").val(data.postOffice);
//       $("#email").val(data.email);
//       $("#note").val(data.note);
//
//     },
//     error: function (error) {
//       alert.log("Error:" + error);
//     }
//   });
// }
//
// function onCreatedParentRow(row, data, rowIndex) {
//   $row = $(row);
//   $row.addClass('parent-row').addClass('status-' + data.status);
// }
//
// function showSuppliers() {
//   $('body').toggleClass('suppliers-shown');
// }
//
// function addOrderItemOnTabPressed(e) {
//   $(this).keydown(function (e) {
//     if (e.which == 9) {
//       var orderId = $(this).closest('.order-product-table').data('order-id');
//       addOrderItem(orderId, 'isTabPressed');
//     }
//   });
// }