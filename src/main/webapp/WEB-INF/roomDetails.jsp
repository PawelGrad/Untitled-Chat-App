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
<c:forEach items="${users}" var="user">

    <form method="post" action="/app/rooms/removeUser" >
            ${user.username}
        <input hidden type="text" name="user" value="${user.id}">
                <input hidden type="number" name="roomId" value="${roomId}">
        <button type="submit" class="btn btn-primary">Remove</button>
    </form>

</c:forEach>
 Invite Link: ${inviteLink}

</body>
</html>
