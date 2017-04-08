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
                <a class="btn btn-sm btn-info show-add-new-modal" onclick="showAddSkillModal()"></a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Skill name</th>
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
                        <label for="name" class="control-label col-xs-3">Skill name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Java 8">
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
    $(".show-add-new-modal").html('add new skill');
    var entityName = 'skill';
    var ajaxUrl = 'ajax/profile/skills/';
</script>

<script type="text/javascript" src="resources/js/skillDatatables.js"></script>

</html>