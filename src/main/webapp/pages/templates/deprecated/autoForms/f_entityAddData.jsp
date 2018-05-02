<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<form class="form-horizontal" action="/admin/saveSome${entityName}" method="get">

    <c:forEach items="${entityFields}" var="entityField">

        <div class="form-group">
            <label class="control-label col-sm-4">${entityField.fieldName}:</label>
            <div class="col-sm-7">

                <c:choose>

                    <c:when test="${entityField.inputType == 'multi-select'}">

                        <select class="form-control" name="${entityField.fieldName}" multiple="multiple">

                            <c:forEach items="${entityField.fieldObjectValue}" var="objectVal">

                                <option value="${objectVal.id}">${objectVal}</option>

                            </c:forEach>

                        </select>

                    </c:when>

                    <c:when test="${entityField.inputType == 'select'}">

                        <select class="form-control" name="${entityField.fieldName}">

                            <c:forEach items="${entityField.fieldObjectValue}" var="objectVal">

                                <option value="${objectVal.id}">${objectVal}</option>

                            </c:forEach>

                        </select>

                    </c:when>

                    <c:otherwise>

                        <input type="${entityField.inputType}" name="${entityField.fieldName}" class="form-control" placeholder="${entityField.fieldName}"/>

                    </c:otherwise>

                </c:choose>

            </div>
        </div>

    </c:forEach>


    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-7">
            <button type="submit" class="btn btn-success btn-block">Add</button>
        </div>
    </div>

</form>

<%--
<script>
    let fis = document.getElementsByTagName("input");
    //    console.log(fis);
    for (let x of fis){
        x.value = "";
    }
</script>--%>
