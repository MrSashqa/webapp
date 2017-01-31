<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.lang ? param.lang:
	not empty language ? language: pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>


<html>
<head>
    <title><fmt:message key="page.registration.header"/></title>
</head>
<body>

<%@include file="include/locale.jsp" %>

<h2><fmt:message key="page.registration.header"/></h2>

<c:if test="${errors !=null}">
    <c:forEach var="error" items="${errors}">
        <fmt:message key="${error.value}"/><br/>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/registration/create" method="post">
    <fmt:message key="page.login.login"/><br/>
    <input type="text" name="login" value="${user.login}" required><br/>

    <fmt:message key="page.registration.fname"/><br/>
    <input type="text" name="firstName" value="${client.firstName}" required><br/>

    <fmt:message key="page.registration.lname"/><br/>
    <input type="text" name="lastName" value="${client.lastName}" required><br/>

    <fmt:message key="page.registration.phoneNumber"/><br/>
    <input type="text" name="phoneNumber" value="${client.phoneNumber}" required><br/>

    <fmt:message key="page.registration.email"/><br/>
    <input type="text" name="email" value="${client.email}" required><br/>

    <fmt:message key="page.login.password"/><br/>
    <input type="password" name="password" value="${user.password}" required><br/>

    <fmt:message key="page.registration.button" var="label"/><br/>
    <input type="submit" value="${label}"><br/>

</form>


</body>
</html>
