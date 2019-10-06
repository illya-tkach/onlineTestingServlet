<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Users List</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>
</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name"/></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/"><fmt:message key="menu.main" /><span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul class="navbar-nav ">
            <li class="nav-item mr-2">
                <span class="navbar-text"><fmt:message key="lang.change" /></span>:
                <select id="locales">
                    <option value=""></option>
                    <option value="ua"><fmt:message key="lang.ua" /></option>
                    <option value="en"><fmt:message key="lang.en" /></option>
                </select>
            </li>
            <li class="nav-item">
                <span class="navbar-text">
                Login as:
                </span>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <strong><security:authentication property="principal.username"/></strong>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/logout"/>">Выйти</a>
                </div>
            </li>
        </ul>

    </div>
</nav>
<div class="container d-flex justify-content-center">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead"><fmt:message key="recordPage.tableTitle" /></span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="recordPage.table.barber" /></th>
                <th><fmt:message key="recordPage.table.serviceType" /></th>
                <th><fmt:message key="recordPage.table.clientName" /></th>
                <th><fmt:message key="recordPage.table.clientMail" /></th>
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
                window.location.replace('/recordList?lang=' + selectedOption);
            }
        });
    });
</script>
</body>
</html>