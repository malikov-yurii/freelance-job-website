<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3 class="page-title"></h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info show-add-new-modal" onclick="showAddUserModal()"></a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="app.id"/></th>
                        <th><fmt:message key="app.firstName"/></th>
                        <th><fmt:message key="app.lastName"/></th>
                        <th><fmt:message key="app.userLogin"/></th>
                        <th><fmt:message key="app.email"/></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="firstName" class="control-label col-xs-3"><fmt:message key="app.firstName"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Ivan">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="lastName" class="control-label col-xs-3"><fmt:message key="app.lastName"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Ivanov">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="login" class="control-label col-xs-3"><fmt:message key="app.userLogin"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="login" name="login" placeholder="login">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><fmt:message key="app.password"/></label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><fmt:message key="app.email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="ivanov@gmail.com">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()"><fmt:message key="common.save"/></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>