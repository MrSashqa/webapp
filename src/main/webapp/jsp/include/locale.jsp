
<c:set var="language" value="${not empty param.lang ? param.lang:
	not empty language ? language: pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<hr/>
<a href="${pageContext.request.requestURI}?lang=en">en</a>
<a href="${pageContext.request.requestURI}?lang=ru">ru</a><br/>
<hr/>
</body>
</html>
