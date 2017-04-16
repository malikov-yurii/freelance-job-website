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
<script>
    var freelancerId = ${freelancer.id};
</script>

<div class="jumbotron">
    <div class="container">

        <sec:authorize access="hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')">
            <div class="row">
                <div class="col-md-3 col-xs-6">
                    <a class="btn btn-sm btn-success" onclick="addProject('')">add project</a>
                </div>
            </div>
        </sec:authorize>
        <br>

        <table class="project-table display" id="datatable">
            <thead>
            <tr class="order-table-head">
                <th>P Name</th>
                <th>Description</th>
                <th>Cl last name</th>
                <th>Skills</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

</body>

<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript" src="resources/js/portfolioDatatables.js"></script>

</html>