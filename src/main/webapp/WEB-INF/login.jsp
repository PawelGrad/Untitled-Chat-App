<html xmlns:th="https://www.thymeleaf.org">
<head th:include="layout :: head(title=~{::title},links=~{})">
    <title>Please Login</title>
</head>
<body th:include="layout :: body" th:with="content=~{::content}">
<div th:fragment="content">
    <form name="f" th:action="@{/login}" method="post">
        <fieldset>
            <legend>Please Login</legend>
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
        </fieldset>
    </form>
    <form name="r" action="/register" method="get">
        <button type="submit" class="btn">Register</button>
    </form>

</div>
</body>
</html>