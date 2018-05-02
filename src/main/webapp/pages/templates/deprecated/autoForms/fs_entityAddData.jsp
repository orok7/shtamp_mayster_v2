<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form class="form-horizontal" action="/admin/saveSome${entityName}"
         method="get" modelAttribute="someEntity">
    <c:set var="efI" value="${0}"/>

    <c:forEach items="${someEntity.fields}" var="entityField">

        <div class="form-group">
            <label class="control-label col-sm-4" for="efInput${efI = efI + 1}">${entityField.fieldName}:</label>
            <div class="col-sm-7">

                <c:choose>

                    <c:when test="${entityField.inputType == 'select'}">

                        <sf:select class="form-control" path="fields.fieldStringValue">
                            <c:forEach items="${entityField.fieldObjectValue}" var="objectVal">
                                <sf:option value="${objectVal.id}">${objectVal}</sf:option>
                            </c:forEach>
                        </sf:select>

                    </c:when>

                    <c:when test="${entityField.inputType == 'multi-select'}">

                        <sf:select class="form-control" path="fields.fieldStringValue">
                            <c:forEach items="${entityField.fieldObjectValue}" var="objectVal">
                                <sf:option value="${objectVal.id}">${objectVal}</sf:option>
                            </c:forEach>
                        </sf:select>

                    </c:when>

                    <c:otherwise>
                        <sf:input type="${entityField.inputType}" class="form-control"
                                  path="fields.fieldStringValue" placeholder="${entityField.fieldName}"/>

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

</sf:form>

<script>
    let fis = document.getElementsByTagName("input");
//    console.log(fis);
    for (let x of fis){
        x.value = "";
    }
</script>