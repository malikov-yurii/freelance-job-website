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

        <sec:authorize access="hasRole('ROLE_CLIENT')">
            <div class="row">
                <div class="col-md-3 col-xs-6">
                    <a class="btn btn-sm btn-success" onclick="addProject('')">Add Project</a>
                </div>
            </div>
        </sec:authorize>
        <br>

        <table class="project-table display" id="datatable">
            <thead>
            <tr class="order-table-head">
                <%--<tr>--%>
                <th>ID</th>
                <th>Cl ID</th>
                <th>P Name</th>
                <th>Description</th>
                <th>Payment</th>
                <th>Cl last name</th>
                <th>Status</th>
                <th>Skills</th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" id="addProject">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Add project</h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">

                    <%--<div class="form-group">--%>
                        <%--<label for="id" class="control-label col-xs-3">ID</label>--%>

                        <%--<div class="col-xs-9">--%>
                            <%--<input type="text" class="form-control" disabled="disabled" id="id" name="id">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label for="projectName" class="control-label col-xs-3">Project name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="projectName" name="name"
                                   placeholder="New project name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="projectDescription" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="projectDescription" name="description"
                                   placeholder="description">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="projectPayment" class="control-label col-xs-3">Payment</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="projectPayment" name="payment"
                                   placeholder="0.00">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="projectStatus" class="control-label col-xs-3">Status</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="projectStatus" name="status"
                                   placeholder="LOOKING_FOR_FREELANCER">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="projectRequiredSkills" class="control-label col-xs-3">Required skills</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="projectRequiredSkills" name="requiredSkills"
                                   placeholder="Java, HTML, Javascript">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveProject()">Persist new project</button>
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

</html>