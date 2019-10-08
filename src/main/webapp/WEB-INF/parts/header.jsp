<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Online testing</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>
</head>
<body>