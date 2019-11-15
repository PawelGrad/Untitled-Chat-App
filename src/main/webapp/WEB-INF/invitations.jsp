<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${invitations}" var="invitation">


    ${invitation.inviter.username} ${invitation.room.roomName}
    <form method="post" action="/app/invitations" >
        <input hidden type="text" name="userId" value="${invitation.invitee.id}">
        <input hidden type="text" name="roomId" value="${invitation.room.id}">
        <button type="submit" class="btn btn-primary">Acccept</button>
    </form> <br>

</c:forEach>
</body>
</html>
