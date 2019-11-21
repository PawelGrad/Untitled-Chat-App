<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pawcik
  Date: 11/21/2019
  Time: 5:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${rooms}" var="room">

    ${room.roomName}
    <form method="post" action="/app/rooms/roomInfo" >

        <input hidden type="text" name="room" value="${room.id}">
        <button type="submit" class="btn btn-primary">Details</button>
    </form>
    <form method="post" action="/app/rooms/delete" >

        <input hidden type="text" name="roomId" value="${room.id}">
        <button type="submit" class="btn btn-primary">Delete</button>
    </form>

</c:forEach>
</body>
</html>
