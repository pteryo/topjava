<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>All Meals</h2>
<br>

<table cellspacing="10">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>  </th>
    </tr>

    <c:forEach var="meal" items="${mealToList}">


        <tr style="color: ${meal.excess ? "red" : "green"}">
            <td>${meal.id}</td>
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>

            <td>
                <a href="update-meals.jsp?mealId=${meal.id}">Редактировать</a>
            </td>
        </tr>

    </c:forEach>

</table>


</body>
</html>