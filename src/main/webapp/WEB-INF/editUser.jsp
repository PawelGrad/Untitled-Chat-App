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
    <form:form method="post" modelAttribute="user">



        <label>Password:</label>
        <form:input class="form-control" type="text" path="password" id="passwordId"/>
        <form:errors path="password"/>
        <br/><br/>

        <label>Email:</label>
        <form:input class="form-control" type="email" path="email" id="emailId"/>
        <form:errors path="email"/>
        <br/><br/>


        <input type="submit" class="btn btn-primary" value="Save">

    </form:form>
</div>
</body>
</html>