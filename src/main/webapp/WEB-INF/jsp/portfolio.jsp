<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        Portfolio of ${freelancer.fullName}
        <br>
        ${freelancer.fullName}'s skills: ${freelancer.skills}
        <br><br>

        <c:if test="${not empty portfolioList}">
            <table>
                <th>Project name</th>
                <th>Project description</th>
                <th>Client</th>
                <th>Skills for project</th>
                <c:forEach var="project" items="${portfolioList}">
                    <tr>
                        <td>${project.name}</td>
                        <td>${project.description}</td>
                        <td>${project.clientLastName}</td>
                        <td>${project.requiredSkills}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${empty portfolioList}">
            Sorry, but ${freelancer.fullName} doesn't have any finished projects yet.
        </c:if>
    </div>
</div>

</body>

<jsp:include page="fragments/footer.jsp"/>

</html>