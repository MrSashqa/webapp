<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <style>
        .menu_div {
            display: none;
        }

        div {
            align-content: center;
            background: mediumspringgreen;
        }

        #dish_form {
            display: none;
            background: slategray;
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
        function _displayForm(x) {
            if (_(x).style.display == 'block') {
                _(x).style.display = 'none'
            } else {
                _(x).style.display = 'block'
            }
        }

        function _display(x) {
            _(current).style.display = 'none';
            _(x).style.display = 'block';
            current = x;
        }


    </script>

    <title>Dishes</title>

</head>

<c:choose>

    <c:when test="${not empty menu}">

        <c:set var="counter" value="0" scope="page"/>

        <c:forEach items="${menu}" var="entry">

            <c:set var="counter" value="${counter+1}" scope="page"/>

            <button onclick="_display('${counter}')">${entry.key}</button>

        </c:forEach>

    </c:when>

    <c:otherwise>

        <h2>You have nothing in your menu. Add some dishes!</h2>

        <button>Add Dish</button>

    </c:otherwise>
</c:choose>

<hr/>

<c:choose>

    <c:when test="${not empty menu}">

        <c:set var="counter" value="0" scope="page"/>

        <c:forEach items="${menu}" var="entry">

            <c:set var="counter" value="${counter+1}" scope="page"/>

            <div id="${counter}" class="menu_div">

                <c:forEach items="${entry.value}" var="dish">

                    <a href="${pageContext.request.contextPath}/controller?command=delete_dish&id=${dish.id}">
                        Delete</a>${dish}


                    <br/>

                </c:forEach>

            </div>

        </c:forEach>

    </c:when>

    <c:otherwise>

        <h2>You have nothing in your menu. Add some dishes!</h2>

    </c:otherwise>
</c:choose>

<hr/>

<button onclick="_displayForm('dish_form')">Add New Dish</button>

<hr/>

<c:if test="${not empty errors}">


    <c:forEach items="${errors}" var="error">

        ${error}<br/>

    </c:forEach>

</c:if>

<form id="dish_form" action="${pageContext.request.contextPath}/controller" method="post">

    <input type="hidden" name="command" value="create_dish">

    Dish Type:<br/><select name="dishType">

    <option value=""></option>

    <c:forEach items="${dishTypes}" var="type">

        <option value="${type}">${type}</option>

    </c:forEach>

</select><br/>

    DishName :<br/> <input type="text" name="name" value="${dish.name}" required/><br/>

    Price :<br/><input type="text" name="price" value="${dish.price}" required/><br/>

    <input type="submit" value="create!">

</form>

<body>

</body>
</html>
