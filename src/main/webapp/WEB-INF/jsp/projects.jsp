<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">

        <sec:authorize access="hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')">
            <div class="row">
                <div class="col-md-3 col-xs-6">
                    <a class="btn btn-success" onclick="addProject('')"><fmt:message key="app.addProject"/></a>
                </div>
            </div>
        </sec:authorize>
        <br>

        <table class="project-table display" id="datatable">
            <thead>
            <tr class="order-table-head">
                <th><fmt:message key="app.id"/></th>
                <th><fmt:message key="app.clientId"/></th>
                <th><fmt:message key="app.projectName"/></th>
                <th><fmt:message key="app.description"/></th>
                <th><fmt:message key="app.payment"/></th>
                <th><fmt:message key="app.clientLastName"/></th>
                <th><fmt:message key="app.status"/></th>
                <th><fmt:message key="app.requiredSkills"/></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
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
                    <input type="hidden" class="form-control" id="projectId" name="id">

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="form-group">
                            <label for="projectClientId" class="control-label col-xs-3"><fmt:message key="app.clientId"/></label>

                            <div class="col-xs-9">
                                <input type="number" class="form-control" id="projectClientId" name="clientId"
                                       placeholder="client id of new project">
                            </div>
                        </div>
                    </sec:authorize>

                    <div class="form-group">
                        <label for="projectName" class="control-label col-xs-3"><fmt:message key="app.projectName"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="projectName" name="name"
                                   placeholder="New project name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="projectDescription" class="control-label col-xs-3"><fmt:message key="app.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="projectDescription" name="description"
                                   placeholder="description">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="projectPayment" class="control-label col-xs-3"><fmt:message key="app.payment"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="projectPayment" name="payment"
                                   placeholder="0.00">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="projectRequiredSkills" class="control-label col-xs-3"><fmt:message key="app.requiredSkills"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="projectRequiredSkills" name="requiredSkills"
                                   placeholder="Java, HTML, Javascript">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()"><fmt:message key="common.submit"/></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="commentEditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="commentModalTitle"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="commentDetailsForm">

                    <input type="hidden" class="form-control" id="commentProjectId" name="projectId">

                    <input type="hidden" class="form-control" id="id" name="id">


                    <div class="form-group">
                        <label for="commentText" class="control-label col-xs-3"><fmt:message key="app.commentText"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="commentText" name="commentText">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="updateComment()"><fmt:message key="common.submit"/></button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>

<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript" src="resources/js/projectDatatables.js"></script>

<script type="text/template" id="appliedFreelancerList">
    <table class="applied-freelancer-table" data-project-id="{{= projectId }}">
        <thead>
        <tr>
            <th><span class="order-head-lg"><fmt:message key="common.appliedFreelancer"/></span></th>
            <th><span class="order-head-lg"><fmt:message key="common.freelancerSkills"/></span></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        {{for (var i = 0; i < appliedFreelancerTos.length; i++) { }}
        <tr class="order-product-row" data-applied-freelancer-id="{{= appliedFreelancerTos[i].id }}">
            <td class="applied-freelancer-full-name" data-key="full-name">
                {{= appliedFreelancerTos[i].fullName }}
            </td>
            <td class="order-product-price" data-key="skills">
                {{= appliedFreelancerTos[i].skills }}
            </td>
            <td>
                {{= renderApproveFreelancerBtn(projectId, appliedFreelancerTos[i].id, projectClientId) }}
            </td>
        </tr>
        {{ } }}
        </tbody>
    </table>
</script>

<script type="text/template" id="commentList">
    <table class="comment-table">
        <thead>
        <tr>
            <th><fmt:message key="app.comments"/></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        {{for (var i = 0; i < commentTos.length; i++) { }}
            <tr data-comment-id="{{= commentTos[i].id }}">
                <td class="comment-text" data-key="comment-text">
                    {{= commentTos[i].dateTimePlaced + ' ' + commentTos[i].userFullName + ': ' + commentTos[i].commentText }}
                </td>
                <td>{{= renderBlockUnblockCommentBtn(commentTos[i].id, commentTos[i].blocked) }}</td>
                <td>{{= renderUpdateCommentBtn(commentTos[i].id, commentTos[i].commentText) }}</td>
                <td>{{= renderDeleteCommentBtn(commentTos[i].id) }}</td>
            </tr>
        {{ } }}
        </tbody>
    </table>
</script>

</html>