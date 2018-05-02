<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/adminHead.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Username</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Role</th>
            <th>Discount</th>
            <th>isCompany</th>
            <th>Email</th>
            <th>Phone number</th>
            <th>Change</th>
            <%--<th>Remove</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:set var="indexPG" value="1" scope="page" />
        <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${indexPG}</td>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.role}</td>
                    <td>${user.discount}</td>
                    <td>${user.isCompany}</td>
                    <td>${user.email}</td>
                    <td>${user.phoneNumber}</td>
                    <td><a href="/admin/modifyUser${user.id}">Modify</a></td>
                    <%--<td><a href="/admin/removeUser${user.id}">Delete</a></td>--%>
                </tr>
                <c:set var="indexPG" value="${indexPG + 1}" scope="page"/>
            </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>
