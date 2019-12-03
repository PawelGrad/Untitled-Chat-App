<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns:th="https://www.thymeleaf.org">
<head th:include="layout :: head(title=~{::title},links=~{})">
    <title>Index</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body th:include="layout :: body" th:with="content=~{::content}">

<div style="position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);width: 50%; ">
    <div style="text-align: center;" class="card">
        <div class="card-body">
            <h1>Untitled Chat App</h1>

        </div>
    </div>
    <a href="/login"><button style="position: absolute; top: 110%; left: 40%" class="btn btn-primary">Log in</button></a>
    <a href="/register"><button style="position: absolute; top: 110%; left: 50%" class="btn btn-primary">Register</button></a>


</div>
</body>
</html>