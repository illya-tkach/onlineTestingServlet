<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Rating List</title>

    <script src="${contextPath}/resources/jQuery-3.2.1/jquery-3.2.1.min.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>

    <style type="text/css">
        body{
            background: url("${contextPath}/resources/images/wallpaper.jpg"); /* Добавляем фон */
            background-size: cover; /* Масштабируем фон */
        }
    </style>
</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name" /></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        </ul>
        <ul class="navbar-nav ">
            <li class="nav-item">
                <span class="navbar-text"><fmt:message key="lang.change" /></span>:
                <select id="locales">
                    <option value=""></option>
                    <option value="ua"><fmt:message key="lang.ua" /></option>
                    <option value="en"><fmt:message key="lang.en" /></option>
                </select>
            </li>
            <li class="nav-item mr-2">
                <span style="color:red">${loginedUser.email}</span>
            </li>
            <c:if test="${loginedUser!= null}">
                <li class="nav-item mr-2">
                    <a class="nav-link" href="/logout"><fmt:message key="menu.logout" /></a>
                </li>
            </c:if>
        </ul>

    </div>
</nav>
<div class="container d-flex justify-content-center">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead"><fmt:message key="recordPage.tableTitle" /></span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="recordPage.table.student" /></th>
                <th><fmt:message key="recordPage.table.mail" /></th>
                <th><fmt:message key="recordPage.table.course" /></th>
                <th><fmt:message key="recordPage.table.rating" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${testRatings}" var="rating">
                <tr>
                    <td>${rating.user.firstName} ${rating.user.lastName}</td>
                    <td>${rating.user.email}</td>
                    <td>${rating.test.topicName}</td>
                    <td>${rating.rating}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/getRating?sessionLocale=' + selectedOption);
            }
        });
    });
</script>
</body>
</html>