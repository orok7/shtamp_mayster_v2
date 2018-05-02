<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/head.jsp"%>
<%@include file="templates/main_parts/header.jsp" %>
<%@include file="templates/main_parts/menu.jsp" %>

<div class="container fleft">

    <div class="col-sm-offset-1">
        <h3 style="margin-top: 11px">Корзина</h3>
    </div>

    <%@include file="templates/forms/f_cart.jsp" %>
    <td><a href="/order/newOrder" class="btn btn-success">Buy this</a></td>
    </tr>
    </tbody>
    </table>
    </div>

</div>

<%@include file="templates/main_parts/footer.jsp" %>

</body>
</html>