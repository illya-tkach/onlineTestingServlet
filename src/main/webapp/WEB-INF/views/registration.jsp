<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>User Registration Form</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <%--    <link href="${contextPath}/resources/css/app.css" rel="stylesheet">--%>

</head>

<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/registration" method="post" class="needs-validation form-signin" novalidate>
        <h2 class="form-heading">Registration</h2>
        <div class="form-group col-12">
            <label for="exampleInputFirstName">First Name</label>
            <input type="text" name="firstName" class="form-control" id="exampleInputFirstName" placeholder="First Name" autocomplete="off">
            <p style="color: red;">${clientNameError}</p>
            <div class="invalid-feedback">
                Please type First Name.
            </div>
        </div>
        <div class="form-group col-12">
            <label for="exampleInputLastName">Last Name</label>
            <input type="text" name="lastName" class="form-control" id="exampleInputLastName" placeholder="Last Name" autocomplete="off">
            <p style="color: red;">${clientLastNameError}</p>
            <div class="invalid-feedback">
                Please type Last Name.
            </div>
        </div>
        <div class="form-group col-12">
            <label for="exampleInputEmail">Email</label>
            <input type="email" name="email" class="form-control" id="exampleInputEmail" placeholder="Email" autocomplete="off">
            <p style="color: red;">${emailError}</p>
            <div class="invalid-feedback">
                Please type Email.
            </div>
        </div>
        <div class="form-group col-12">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
            <p style="color: red;">${passwordError}</p>
            <div class="invalid-feedback">
                Please type a password.
            </div>
        </div>
        <p style="color: red;">${errorMessage}</p>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</div>
</body>
</html>