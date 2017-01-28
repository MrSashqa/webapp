<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>

<body>
<br/>
<a href="${pageContext.request.requestURI}?lang=en">en</a>
<a href="${pageContext.request.requestURI}?lang=ru">ru</a><br/>
!!!!!${pageContext.request.requestURI}<br/>


<c:if test="${errors!=null}">
    <c:forEach items="${errors}" var="error">
        <fmt:message key="${error.value}"/><br/>
    </c:forEach>
</c:if>


<form action='${pageContext.request.contextPath}/controller'
      method="post">
    <input type="hidden" name="command" value="login">
    <fmt:message key="page.login.login"/>
    <br/> <input type="text" name="login" value="${user.login}" required><br/>

    <fmt:message key="page.login.password"/>
    <br/> <input type="password" name="password"
                 value="${user.password}" required><br/>

    <fmt:message key="page.login.submit" var="button"/>
    <input type="submit" value="${button}"/>
</form>

</body>
</html>