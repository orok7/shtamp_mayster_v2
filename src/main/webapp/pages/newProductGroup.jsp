<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/adminHead.jsp" %>

<div class="col-sm-offset-1 col-sm-4">
    <form action="/admin/saveProductGroup" class="form-horizontal" method="post">
        <h4>Назва групи продуктів</h4>
        <input type="hidden" name="idProductGroup" value="${productGroupId}">
        <input class="form-control" type="text" name="nameProductGroup" value="${productGroupName}">
        <br>
        <input class="btn btn-success btn-block" type="submit" value="Save">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>

</body>
</html>
