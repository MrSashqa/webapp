<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<style>
    .menu_div {
        display: none;
    }
</style>
<script>
    var current = 1;
    window.onload = function () {
        _display(1);
    }


    function _(x) {
        return document.getElementById(x);
    }

    function _display(x) {
        _(current).style.display = 'none';
        _(x).style.display = 'block';
        current = x;
    }

    function addToOrder(x, y, z) {

        var inputId = 'input_id_' + x;

        if (_(inputId) == null) {

            var newDiv = document.createElement('order_line_' + x);

            newDiv.innerHTML = "<br>" + y + " <input id=" + inputId + " type='number' min='1'  value='1' name='quantity'>" +

                "<input type='button' onclick='removeFromOrder(this)' value='remove'><input type='hidden' name='dishId' value='" + x + "'>";

            _('order_lines').appendChild(newDiv);
        } else {
            var i = parseInt(_(inputId).value) + 1;
            _('input_id_' + x).value = i;
        }
    }

    function removeFromOrder(x) {
        _('order_lines').removeChild(x.parentNode);
    }

    function submitForm() {
        if (_("order_lines").hasChildNodes()) {
            _("order_form").method = "post";
            _("order_form").submit();
        } else {
            alert("Your order is empty =(");
        }

    }
</script>

<head>
    <title>Menu</title>
</head>

<body>
<%@include file="include/locale.jsp"%>
<hr/>
user{${user.login},id=${user.id}}
<hr/>
<c:choose>

    <c:when test="${not empty menu}">

        <c:set var="counter" value="0" scope="page"/>

        <c:forEach items="${menu}" var="entry">

            <c:set var="counter" value="${counter+1}" scope="page"/>

            <button onclick="_display('${counter}')">${entry.key.toString()}</button>

        </c:forEach>

        <hr/>
        <hr/>

        <c:set var="counter" value="0" scope="page"/>

        <c:forEach items="${menu}" var="entry">

            <c:set var="counter" value="${counter+1}" scope="page"/>

            <div id="${counter}" class="menu_div">

                <c:forEach items="${entry.value}" var="dish">

                    <input id="button_${dish.id}" type="button"
                           onclick="addToOrder('${dish.id}','${dish.name}','${dish.price}')"

                           value="${dish.name} : ${dish.price}">

                    <br/>

                </c:forEach>

            </div>

        </c:forEach>


    </c:when>

    <c:otherwise>

        <h2>Currently we have nothing to please you...Visit us later!</h2>

    </c:otherwise>
</c:choose>

<hr/>
<hr/>


<form id="order_form" action="${pageContext.request.contextPath}/client/order" onclick="return false">
    <hr/>
    FORM
    <hr/>
    <div id="order_lines"></div>
    <input type="submit" value="order" onclick="submitForm()">
</form>


</body>
</html>
