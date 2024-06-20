<%--
  Created by IntelliJ IDEA.
  User: P42
  Date: 20.06.2024
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
  <title>Еда ${meal.description}</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<div class="form-wrapper">
  <h2>${meal.description}</h2>
  <div>dateTime:
    ${TimeUtil.toHtml(meal.dateTime)}
  </div>

  <div>Calories:
    ${meal.calories}
  </div>
</div>
</body>
</html>