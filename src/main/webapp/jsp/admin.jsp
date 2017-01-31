<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h1>Hello Admin </h1>
<hr/>
<br/>
<a href="${pageContext.request.contextPath}/admin/main">orders</a>|
<a href="${pageContext.request.contextPath}/admin/main/menu">dishes</a>
<hr/>
${Role}<br/>
<hr/>
<hr/>

!!!!!${pageContext.request.requestURI}<br/>
<c:choose>

    <c:when test="${not empty orders}">
        <c:forEach items="${orders}" var="order">
            order id :${order.id} <br/>
            client :${order.client}<br/>
            content:${order.orderContent}<br/>
            <hr/>
            <hr/>

        </c:forEach>

    </c:when>

</c:choose>

</body>
</html>
