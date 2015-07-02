<%@ page import="model.Prod" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <style>
    <%@include file='view/bootstrap.css' %>
  </style>
  <title>Прайс-лист</title>
</head>
<body>

<!-- прайс view -->
<div id='container'>
  <h1>Прайс-лист</h1>
  <form id='filter' method='get' action='/controller'>

    <div class='filter_inner'>
      Категория:<br>
      <input class='filter_input' type='text' name="category" maxlength='64'>
    </div>

    <div class='filter_inner'>
      Наименование:<br>
      <input class='filter_input' type='text' name="name" maxlength='64'>
    </div>

    <div class='filter_inner'>
      Цена от:<br>
      <input class='filter_input' type='text' name="priceFrom" maxlength='16'>
    </div>

    <div class='filter_inner'>
      Цена до:<br>
      <input class='filter_input' type='text' name="priceTo" maxlength='16'>
    </div>

    <div class='filter_inner'>
      <input type='submit' value='Найти' class='filter_button'>
    </div>
    <div id="inputSome">${havenot}</div>
    <br>
    <div id='result'>
      <table id='result_table'>
        <tr>
          <th>Категория</th>
          <th>Наименование</th>
          <th>Цена</th>
        </tr>
        <c:forEach var="prod" items="${list}">
        <tr>
          <td><c:out value="${prod.cat.name}"/></td>
          <td><c:out value="${prod.name}"/></td>
          <td><c:out value="${prod.price}"/></td>
        </tr>
        </c:forEach>
      </table>
    </div>

  </form>
</div>
</body>
</html>