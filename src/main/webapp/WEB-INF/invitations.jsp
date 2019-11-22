<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

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
        Pending invitations: <br>
        <ul class="list-group">
            <c:forEach items="${invitations}" var="invitation">

                <li class="list-group-item">
                ${invitation.inviter.username} ${invitation.room.roomName}
                <form style ='float: right; padding: 5px;' method="post" action="/app/invitations/accept" >
                    <input hidden type="text" name="invitationId" value="${invitation.id}">
                    <input hidden type="text" name="userId" value="${invitation.invitee.id}">
                    <input hidden type="text" name="roomId" value="${invitation.room.id}">
                    <button type="submit" class="btn btn-primary">Acccept</button>
                </form>
                <form style ='float: right; padding: 5px;' method="post" action="/app/invitations/decline" >
                    <input hidden type="text" name="invitationId" value="${invitation.id}">
                    <button type="submit" class="btn btn-primary">Decline</button>
                </form> <br>
                </li>
            </c:forEach>
        </ul>
    </div>

</div>
</body>
</html>
