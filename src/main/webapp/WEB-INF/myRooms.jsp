<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Rooms</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="menu.jsp" %>
<div style="position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);width: 50%; ">
<div>

    <c:if test="${ownedRooms.size()!='0'}">

My rooms: <br>
    <ul class="list-group">
<c:forEach items="${ownedRooms}" var="room">
    <li class="list-group-item">
    ${room.roomName}
        <a href="/app/rooms/roomInfo/${room.id}"><button style ='float: right; padding: 5px;' class="btn btn-primary" type="submit">Details</button></a>

        <form style="padding-top: 15px"method="post" action="/app/rooms/delete" >
        <input hidden type="text" name="roomId" value="${room.id}">
        <button style ='float: right; padding: 5px;' type="submit" class="btn btn-primary" name="removeButton" id="${room.roomName}">Delete</button>
    </form>
    </li>
</c:forEach>
    </ul>
    </c:if>
</div>
<div>

    <br><br><br>
<c:if test="${memberRooms.size()!='0'}">
Member: <br>
    <ul class="list-group">
<c:forEach items="${memberRooms}" var="room">

    <li class="list-group-item">
    ${room.roomName}
    <c:if test="${room.roomName ne 'Public'}">
    <form style ='float: right; padding: 5px;' method="post" action="/app/rooms/leave" >

        <input hidden type="text" name="roomId" value="${room.id}">
        <button type="submit" class="btn btn-primary">Leave</button>
    </form>
    </c:if>
    </li>
</c:forEach>
    </ul>
</c:if>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
<script src="../removeRoom.js"></script>

</body>
</html>
