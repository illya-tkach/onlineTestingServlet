<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Log in with your account</title>


    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


</head>

<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-4">
            <form method="POST" action="${contextPath}/login" class="form-signin">
                <input type="hidden" name="redirectId" value="${param.redirectId}" />
                <h2 class="form-heading">Log in</h2>

                <div class="form-group ${error != null ? 'has-error' : ''}">
                    <span>${message}</span>
                    <input name="email" type="text" class="form-control" placeholder="Username"
                           autofocus="true"/>
                    <p style="color: red;">${emailError}</p>
                    <input name="password" type="password" class="form-control" placeholder="Password"/>
                    <p style="color: red;">${passwordError}</p>
                    <span>${error}</span>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                    <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>