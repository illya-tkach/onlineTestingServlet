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
    <title>Results</title>

    <script src="${contextPath}/resources/jQuery-3.2.1/jquery-3.2.1.min.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <style type="text/css">
        body{
            background: url("${contextPath}/resources/images/wallpaper.jpg"); /* Добавляем фон */
            background-size: cover; /* Масштабируем фон */
        }
    </style>

    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

    <script defer src="${contextPath}/resources/fontawesome-free-5.6.1-web/js/all.min.js"></script>
</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name" /></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        </ul>
        <ul class="navbar-nav ">
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
<div class="container" >
    <div class="row justify-content-center">
        <div class="col-6">
            <p><fmt:message key="results.number" />: ${totalPoints} из ${questionList.size()}</p>
        </div>
    </div>
    <c:forEach items="${questionList}" var="question">
        <div class="row justify-content-center">
            <div class="col-6 justify-content-center">
                <div>
                    ${question.definition}
                </div>
                <c:forEach items="${question.answers}" var="answer">
                <div>
                        <input type="radio" id="contactChoice${answer.id}" value="${answer.id}" disabled <c:if test="${answer.answered==true}">checked="checked"</c:if>>
                        <label for="contactChoice${answer.id}"
                                <c:choose>
                                    <c:when test="${answer.isCorrect == true}">
                                        style="color:green;"
                                    </c:when>
                                    <c:when test="${(answer.isCorrect == false)  && (answer.answered == true)}">
                                        style="color:red;"
                                    </c:when>
                                    <c:otherwise>
                                    </c:otherwise>
                                </c:choose>>
                                ${answer.definition}
                        </label>
                </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
    <div class="row justify-content-center">
        <div class="col-3">
            <a href="/tests" class="btn btn-danger btn-lg active mb-5" role="button" aria-pressed="true">Перейти до меню тестів</a>
        </div>
    </div>
</>
</body>
</html>
