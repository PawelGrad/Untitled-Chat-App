<html xmlns:th="https://www.thymeleaf.org">
<head th:include="layout :: head(title=~{::title},links=~{})">
    <title>Login</title>
    <link href="../loginStyle.css" rel="stylesheet" type="text/css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


</head>
<body th:include="layout :: body" th:with="content=~{::content}">
<div class="wrapper fadeInDown">
    <div th:fragment="content" id="formContent">
        <form name="f" th:action="@{/login}" method="post" >


            <input type="text" id="username" class="fadeIn second" name="username" placeholder="username">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">

            <input style=" width: 41%;" type="submit" class="fadeIn fourth" value="Log In">


        </form>
        <a href="/register"><button  class="btn btn-primary">Register</button></a>
    </div>
</div>
</body>
</html>