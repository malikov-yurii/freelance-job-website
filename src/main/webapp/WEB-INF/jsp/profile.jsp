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
    <div class="container">
        <div class="shadow">
            <h3>
                <c:if test="${register}">
                    <fmt:message key="app.register"/>
                    <fmt:message key="common.add" var="saveButton"/>
                </c:if>
                <c:if test="${not register}">
                    ${profileUserTo.firstName} ${profileUserTo.lastName} profile
                    <fmt:message key="common.update" var="saveButton"/>
                </c:if>
            </h3>

            <div class="view-box">
                <form:form modelAttribute="profileUserTo" class="form-horizontal" method="post"
                           action="${register ? (profileUserTo.role == 'client' ? 'register-client': 'register-freelancer'): 'profile'}"
                           charset="utf-8" accept-charset="UTF-8">

                    <ishop:inputField label="First Name" name="firstName"/>
                    <ishop:inputField label="Last Name" name="lastName"/>
                    <ishop:inputField label="Login" name="login"/>
                    <ishop:inputField label="Password" name="password" inputType="password"/>
                    <ishop:inputField label="Email" name="email"/>
                    <c:if test="${profileUserTo.role == 'freelancer'}">
                        <ishop:inputField label="Skills" name="skills"/>
                    </c:if>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10">
                            <button type="submit" class="btn btn-primary">${saveButton}</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
