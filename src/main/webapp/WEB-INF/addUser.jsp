<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<div style="position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);width: 50%;
    width: 20%;">
    <div>
        ${mismatch}
        <div style="color: red;"><c:if test="${exists == 'User already exists'}">
            User already exists.
        </c:if></div>
        <form:form method="post" modelAttribute="user">
            <label>Username:</label>
            <form:input class="form-control" type="text" path="username" id="usernameId"/>
            <form:errors path="username"/>
            <br/><br/>

            <label>Password:</label>
            <form:input class="form-control" type="password" path="password" id="passwordId"/>
            <form:errors path="password"/>
            <br/><br/>

            <input type="submit" class="btn btn-primary" value="Save">
        </form:form>
    </div>
</div>
</body>
</html>