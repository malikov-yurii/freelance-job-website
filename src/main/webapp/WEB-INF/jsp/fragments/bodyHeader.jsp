<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="navbar navbar-inverse"  role="navigation">
    <div class="container">
        <i class="menu-toggle fa fa-bars" aria-hidden="true"></i>
        <a href="#" class="navbar-brand"><fmt:message key="app.title"/></a>
        <form:form class="navbar-form navbar-right" action="logout" method="post">
            <sec:authorize access="isAuthenticated()">
                <a class="btn btn-info" role="button" href="projects">Projects</a>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-info" role="button" href="clients">Clients</a>
                    <a class="btn btn-info" role="button" href="freelancers">Freelancers</a>
                    <a class="btn btn-info" role="button" href="admins">Admins</a>
                    <a class="btn btn-info" role="button" href="skills">Skills</a>
                    <a class="btn btn-info" role="button" href="profile">Profile</a>
                    <input type="hidden" id="hasRoleAdmin" value="1" >
                </sec:authorize>
                <a class="btn btn-info" role="button" href="profile">${baseUserTo.firstName} ${baseUserTo.lastName} profile</a>
                <input type="submit" class="btn btn-primary" value="<fmt:message key="app.logout"/>">
            </sec:authorize>
        </form:form>
    </div>
</div>

