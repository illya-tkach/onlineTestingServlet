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
    <title>Test</title>

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
<div class="container justify-content-center">
    <div class="row justify-content-center">
        <div class="col-3">
            <ul class="list-group">
                <script>
                    var count = 1;
                    var questionsIdsArray = new Array();
                </script>
                <button type="button" name="selectedButton" onclick="clickQuestion(this);return false;" value="${questionList[0].id}" class="btn btn-primary disabled mb-1" ]><script>document.write(count++)</script></button>
                <script>questionsIdsArray.push(${questionList[0].id})</script>
                <c:forEach begin="1" items="${sessionScope.questionList}" var="questionList">
                    <button type="button" name=" " onclick="clickQuestion(this);return false;" value="${questionList.id}" class="btn btn-primary mb-1" ]><script>document.write(count++)</script></button>
                    <script>questionsIdsArray.push(${questionList.id})</script>
                </c:forEach>
            </ul>
        </div>
        <div class="col-6 justify-content-center">
            <div id="currentQuestion">
                <div>
                ${questionList[0].definition}
                </div>
                    <c:choose>
                        <c:when test= "${questionList[0].questionType.name == 'MULTIPLE'}">
                            <c:forEach items="${questionList[0].answers}" var="answer">
                                <div>
                                    <input type="checkbox" id="contactChoice${answer.id}" class="get_value" value="${answer.id}">
                                    <label for="contactChoice${answer.id}">${answer.definition}</label>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${questionList[0].answers}" var="answer">
                                <div>
                                    <input type="radio" name="radioAnswer" id="contactChoice${answer.id}" value="${answer.id}">
                                    <label for="contactChoice${answer.id}">${answer.definition}</label>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
            </div>
            <button type="button" name="submit" class="btn btn-info" id="submit">Відповісти</button>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-3">
            <a href="/results" class="btn btn-danger btn-lg active" role="button" aria-pressed="true">Закінчити тест</a>
        </div>
    </div>
</div>
</body>
</html>
<script>
    $(document).ready(function () {
            $('#submit').click(function () {
                repaintQuestion();
            });
    });

    function selectNextButton(radioAnswer, questionId) {
        var selectedQuestion = $( "button[name='selectedButton']" );
        var questionID = parseInt(selectedQuestion.val());
        var index = questionsIdsArray.indexOf(questionID);
        var nextIndex;
        if (index < questionsIdsArray.length-1){
            nextIndex = index + 1;
        } else {
            nextIndex = 0;
        }
        var nextQuestionId = questionsIdsArray[nextIndex];
        var nextSelectedQuestion = $( "button[value="+nextQuestionId+"]" );

        ajaxSend("/radioAnswered", radioAnswer, questionId, nextQuestionId);


        selectedQuestion.attr("name", " ");
        selectedQuestion.removeClass("disabled");

        nextSelectedQuestion.attr("name", "selectedButton");
        nextSelectedQuestion.addClass("disabled");
    }

    function repaintQuestion() {
        var idViaRadioChoice = $( "input[name='radioAnswer']" ).val();
        if(idViaRadioChoice != undefined){
            var radioAnswer = $( "input[name='radioAnswer']:checked" ).val();
            var questionID = $( "button[name='selectedButton']" ).val();

            if($('#submit').text() === "Відмінити відповідь"){
                $( "button[name='selectedButton']" ).removeClass('btn-success').addClass('btn-info');
                resetAnswers("/resetAnswers", questionID);
            } else {
                if ( $( "input[name='radioAnswer']:checked" ).val() == undefined){
                    alert("Выберите вариант ответа!")
                } else {
                    $("button[name='selectedButton']").removeClass('btn-info').addClass('btn-success');
                    selectNextButton(radioAnswer, questionID);
                }
            }
        } else {
            var answers = [];
            $('.get_value').each(function () {
                if ($(this).is(":checked")) {
                    answers.push($(this).val());
                }
            });
            ajaxSend("/checkboxAnswered", answers)
        }
    }

    function resetAnswers(url, questionID) {
        $.ajax({
            type: "POST",
            url: url,
            data: "questionID="+questionID,
            success: function (data) {
                addQuestionToView(data);
            }
        });
    }

    function ajaxSend(url, answerID, questionID, nextQuestionID) {
        $.ajax({
            type: "POST",
            url: url,
            data: "answerID="+answerID+"&questionID="+questionID+"&nextQuestionID="+nextQuestionID,
            success: function (data) {
                addQuestionToView(data);
            }
        });
    }

    function getQuestionAjax(url) {
        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json",
            success: function (data) {
                addQuestionToView(data);
            }
        });
    }
    function clickQuestion(obj) {
        var previousSelectedQuestion = $( "button[name='selectedButton']" );
        previousSelectedQuestion.attr("name", " ");
        previousSelectedQuestion.removeClass("disabled");

        $(obj).attr("name", "selectedButton");
        $(obj).addClass("disabled");

        getQuestionAjax("/getQuestion?question=" + $(obj).val())

    }

    function addQuestionToView(data) {
        var answeredQuestion = false;
        var select = $('#currentQuestion').empty();

        select.append(data.definition);
        if (data.questionType === 'ONE'){
            $.each(data.answers, function (i, item) {
                if (item.answered == true){
                    answeredQuestion = true;
                    return false;
                }
            });
            if (answeredQuestion){
                $('#submit').text("Відмінити відповідь");
            } else $('#submit').text("Відповісти");
            $.each(data.answers, function (i, item) {
                if (answeredQuestion){
                    if (item.answered) {
                        select.append('<div><input type="radio" name="radioAnswer" id="contactChoice' + item.id + '" value="' + item.id + '" checked="checked" disabled>' +
                            '<label for="contactChoice' + item.id + '">' + item.definition + '</label></div>');
                    } else {
                        select.append('<div><input type="radio" name="radioAnswer" id="contactChoice' + item.id + '" value="' + item.id + '" disabled>' +
                            '<label for="contactChoice' + item.id + '">' + item.definition + '</label></div>');
                    }
                } else {
                    select.append('<div><input type="radio" name="radioAnswer" id="contactChoice' + item.id + '" value="' + item.id + '">' +
                        '<label for="contactChoice' + item.id + '">' + item.definition + '</label></div>');
                }
            });
        } else {
            $.each(data.answers, function (i, item) {
                select.append('<input type="checkbox" id="contactChoice'+item.id+'" class="get_value" value="'+item.id+'">'+
                '<label for="contactChoice'+item.id+'">'+item.definition+'</label>');
            })
        }
    }
</script>
