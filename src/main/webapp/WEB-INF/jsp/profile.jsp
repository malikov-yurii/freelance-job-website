<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ishop" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <jsp:include page="fragments/headTag.jsp"/>
</head>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="profile-page">
        <h3>
            <c:if test="${register}">
                <fmt:message key="app.register"/>
                <c:if test="${profileUserTo.role == 'freelancer'}">

                    <fmt:message key="app.freelancer"/>
                </c:if>
                <c:if test="${profileUserTo.role == 'client'}">
                    <fmt:message key="app.client"/>
                </c:if>

                <fmt:message key="common.submitRegister" var="saveButton"/>

            </c:if>
            <c:if test="${not register}">
                ${profileUserTo.firstName} ${profileUserTo.lastName} profile
                <fmt:message key="common.update" var="saveButton"/>
            </c:if>
            <br><br>
        </h3>

        <div class="view-box">

            <form:form modelAttribute="profileUserTo" class="form-horizontal profile-form" method="post"
                       action="${register
                       ? (profileUserTo.role == 'client' ? 'register-client': 'register-freelancer')
                       : (profileUserTo.role == 'freelancer' ? 'profile-freelancer' : 'profile-user')}"
                       charset="utf-8" accept-charset="UTF-8">

                <c:if test="${not register}">
                    <form:input path="id" type="hidden" class="form-control"/>
                </c:if>

                    <div class="table-row">
                        <form:label path="firstName" class="control-label">First name</form:label>
                        <form:input path="firstName" type="text" class="form-control"/>
                    </div>

                    <div class="table-row">
                        <form:label path="lastName" class="control-label">Last name</form:label>
                        <form:input path="lastName" type="text" class="form-control"/>
                    </div>

                    <div class="table-row">
                        <form:label path="login" class="control-label">Login</form:label>
                        <form:input path="login" type="text" class="form-control"/>
                    </div>

                    <div class="table-row">
                        <form:label path="password" class="control-label">Password</form:label>
                        <form:input path="password" type="password" class="form-control"/>
                    </div>

                    <div class="table-row">
                        <form:label path="email" class="control-label">Email</form:label>
                        <form:input path="email" type="email" class="form-control"/>
                    </div>

                    <c:if test="${profileUserTo.role == 'freelancer'}">
                        <div class="table-row">
                            <form:label path="skills" class="control-label">Skills</form:label>
                            <form:input path="skills" type="text" class="form-control"/>
                        </div>
                    </c:if>

                    <div class="table-row">
                        <button type="submit" class="profile-submit">Submit</button>
                    </div>
            </form:form>

        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
