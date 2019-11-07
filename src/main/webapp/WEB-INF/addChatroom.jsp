<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div class="container-fluid">
    <form:form method="post" modelAttribute="chatroom">
        <label>Room name:</label>
        <form:input class="form-control" type="text" path="roomName" id="roomNameId"/>
        <form:errors path="roomName"/>
        <br/><br/>




        <input type="submit" class="btn btn-primary" value="Save">

    </form:form>
</div>
</body>
</html>