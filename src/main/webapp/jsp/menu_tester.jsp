<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script>

    _(x)
    {
        document.getElementById(x);
    }

    function showDiw(x) {

    }


</script>
<head>
    <title>Title</title>
    <c:set var="counter" value="0" scope="page"/>

    <c:forEach items="${menu}" var="entry">
        <c:set var="counter" value="${counter+1}" scope="page"/>
        <input id="${counter}" type="button" onclick="showDiv(${counter})" value="${entry.key}"/>
    </c:forEach>

    <hr/>

    <c:set var="counter" value="0" scope="page"/>

    <c:forEach items="${menu}" var="entry">

        <c:set var="counter" value="${counter+1}" scope="page"/>

        <div id = "${counter}">

            <c:forEach items="${entry.value}" var="dish">





            </c:forEach>

        </div>

    </c:forEach>



</head>
<body>

</body>
</html>
