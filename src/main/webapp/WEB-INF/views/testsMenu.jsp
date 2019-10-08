<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Test Menu</title>

    <script src="${contextPath}/resources/jQuery-3.2.1/jquery-3.2.1.min.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

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
<div class="container justify-content-center" >
    <div class="row justify-content-center">
        <p class="text-center text-uppercase"><fmt:message key="menu.tests.choose" /></p>
    </div>
    <div class="row justify-content-center">
        <ul class="list-group">
        <c:forEach items="${testList}" var="test">

            <a href="<c:url value='/getRandQuestions?test=${test.id}' />" class="list-group-item list-group-item-action mb-2">${test.topicName}</a>

        </c:forEach>
        </ul>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/tests?sessionLocale=' + selectedOption);
            }
        });
    });
</script>

</body>
</html>
