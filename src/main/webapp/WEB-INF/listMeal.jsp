<%--
  Created by IntelliJ IDEA.
  User: P42
  Date: 20.06.2024
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
  <title>Meals list</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<div>
  <a href="meals?action=add">Add New Meal</a>
</div>
<h2>Таблица калорий</h2>
<table border="1" cellpadding="8" cellspacing="0">
  <tr>
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
    <th></th>
    <th></th>
  </tr>
  <c:forEach items="${meals}" var="meal">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>

    <tr style="color: ${meal.excess ? "red" : "green"}">
      <td>${TimeUtil.toHtml(meal.dateTime)}</td>
      <td><a href="meals?action=view&id=${meal.id}">${meal.description}</a></td>
      <td>${meal.calories}</td>
      <td><a href="meals?action=edit&id=${meal.id}">Edit</a></td>
      <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
    </tr>

  </c:forEach>
</table>

</body>
</html>