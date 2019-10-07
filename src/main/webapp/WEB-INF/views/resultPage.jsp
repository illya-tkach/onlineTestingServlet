<%@ page import="java.io.OutputStream" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Products List</title>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>

    <style type="text/css">
        body{
            height: 200px; /* Высота блока */
            /*border: 2px solid #000; !* Параметры рамки *!*/
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
        <ul class="navbar-nav ">
            <li class="nav-item">
                <span class="navbar-text"><fmt:message key="lang.change" /></span>:
                <select id="locales">
                    <option value=""></option>
                    <option value="ua"><fmt:message key="lang.ua" /></option>
                    <option value="en"><fmt:message key="lang.en" /></option>
                </select>
            </li>
        </ul>

    </div>
</nav>
<div class="container" >
    <div class="row justify-content-center">
        <div class="col-6">
            <p>Количество правильных ответов: ${totalPoints} из ${questionList.size()}</p>
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
</>
<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/?lang=' + selectedOption);
            }
        });
    });
</script>

</body>
</html>
