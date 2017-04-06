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

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3 class="page-title">Freelancers</h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info show-add-new-modal" onclick="showAddUserModal()"></a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>login</th>
                        <th>email</th>
                        <th>skills</th>
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
                        <label for="firstName" class="control-label col-xs-3">First Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Ivan">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="lastName" class="control-label col-xs-3">Last Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Ivanov">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="login" class="control-label col-xs-3">Login</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="login" name="login" placeholder="login">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3">Password</label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3">Email</label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="ivanov@gmail.com">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="skills" class="control-label col-xs-3">Skills</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="skills" name="skills" placeholder="Java, HTML, CSS">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">save</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>


<jsp:include page="fragments/footer.jsp"/>

<script type="text/javascript">
    //    debugger;
    $(".show-add-new-modal").html('add new freelancer');
    var entityName = 'freelancer';
    var ajaxUrl = 'ajax/profile/freelancers/';
</script>

<%--<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>--%>

<script type="text/javascript" src="resources/js/userDatatables.js"></script>

</html>