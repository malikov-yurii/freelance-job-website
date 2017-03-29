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
        <h3>List of all projects</h3>
        <div class="row">
            <div class="col-md-3 col-xs-6">
                <a class="btn btn-sm btn-success" onclick="addProject('')">Add Project</a>
            </div>
        </div>
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


</body>

<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript" src="resources/js/projectDatatables.js"></script>

</html>