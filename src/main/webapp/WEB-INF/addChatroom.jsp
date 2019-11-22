<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="../style.css" rel="stylesheet" type="text/css">
</head>
<style>

    * {
        box-sizing: border-box;
    }

    .column-one {
        float: left;
        width: 15%;
        height: 100%;
        padding: 10px;

    }
    .column-two {
        float: right;
        width: 85%;
        height: 100%;
        padding: 10px;

    }
    a {
        text-decoration:none;
        color: inherit;
        width: 10%;
        text-align: center;
        vertical-align: middle;
        background-color: #39ace7;
        font-family: Verdana;
    }

    .chatButton{

        color: inherit;
        width: 100%;
        text-align: center;
        vertical-align: middle;
        background-color: #39ace7;
        font-family: Verdana;
    }

    html, body {
        height: 100%;
    }

    .row {
        height: 97%;
    }
    .menu {
        height: 3%;
        background-color:#aaa;
    }

    .container {
        padding-top: 100px;
    }

    .chatControls {
        position:absolute;
        bottom:0;
    }
    .newRoom {
        color: inherit;
        width: 100%;
        text-align: center;
        vertical-align: middle;
        background-color: darkorange;
        font-family: Verdana;

    }
</style>
<body>
<%@include file="menu.jsp" %>


<div style="position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);width: 50%; ">
    <div>

        <form:form method="post" modelAttribute="chatroom">
            <label>Room name:</label>
            <form:input class="form-control" type="text" path="roomName" id="roomNameId"/>
            <form:errors path="roomName"/>
            <br/><br/>

            <input type="submit" class="btn btn-primary" value="Save">

        </form:form>
    </div>
</div>
</body>
</html>