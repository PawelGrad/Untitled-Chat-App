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
Owner: <br>

<c:forEach items="${ownedRooms}" var="room">

    ${room.roomName}
    <form method="post" action="/app/rooms/roomInfo" >

        <input hidden type="text" name="room" value="${room.id}">
        <button type="submit" class="btn btn-primary">Details</button>
    </form>
    <form method="post" action="/app/rooms/delete" >

        <input hidden type="text" name="roomId" value="${room.id}">
        <button type="submit" class="btn btn-primary" name="removeButton" id="${room.roomName}">Delete</button>
    </form>
</c:forEach>
Member: <br>
<c:forEach items="${memberRooms}" var="room">

    ${room.roomName}
    <form method="post" action="/app/rooms/leave" >

        <input hidden type="text" name="roomId" value="${room.id}">
        <button type="submit" class="btn btn-primary">Leave</button>
    </form>

</c:forEach>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
<script src="../removeRoom.js"></script>
</body>
</html>
