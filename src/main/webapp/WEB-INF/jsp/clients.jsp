<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>

<body>
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <jsp:include page="fragments/clientsAndAdminsBody.jsp"/>
</body>

<jsp:include page="fragments/footer.jsp"/>

<script type="text/javascript">
    $(".page-title").text("Clients");
    $(".show-add-new-modal").text("add new client");
    var entityName = 'client';
    var ajaxUrl = 'ajax/profile/clients/';
</script>

<script type="text/javascript" src="resources/js/userDatatables.js"></script>

</html>