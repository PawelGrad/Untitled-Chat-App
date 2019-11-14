<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>

<style>
    * {
        box-sizing: border-box;
    }

    .column-one {
        float: left;
        width: 10%;
        height: 100%;
        padding: 10px;

    }
    .column-two {
        float: right;
        width: 90%;
        height: 100%;
        padding: 10px;

    }


    .row, html, body {
        height: 100%;
    }

    .container {
        padding-top: 100px;
    }
</style>


<body>
<span hidden id="room">${room}</span>
<span hidden id="name">${user}</span>


<div class="row" >
    <div class="column-one" style="background-color:#aaa;">
        <c:forEach items="${myRooms}" var="myRoom">


            <form method="post" action="/app/chat" >
                <input hidden type="text" name="myRoom" value="${myRoom.roomName}">
                <button type="submit" class="btn btn-primary">${myRoom.roomName}</button>
            </form>

        </c:forEach>
    </div>



    <div class="column-two" style="background-color:#bbb;">

        <div  id="chatPage" class="container" >
            <div class="card">
                <div class="card-body">
                    <h1>Untitled Chat App</h1>
                </div>
            </div>
            <div class="chat-header">
                <h2>[<span id="room-id-display"></span>]</h2>
            </div>
            <div class="waiting">
                We are waiting to enter the room.
            </div>
            <div class="card" id="chatWindow" style='overflow-y:scroll; height:400px;'>
                <div class="card-body">
                    <ul id="messageArea"/>
                </div>
            </div>


            </ul>
            <form id="messagebox" name="messagebox">
                <div class="form-group">
                    <label for="message">Enter Message:</label>
                    <input type="text" class="form-control" id="message" aria-describedby="name" placeholder="Enter message to chat ...." autocomplete="off">
                </div>
                <button type="submit" class="btn btn-primary">Send</button>
            </form>
        </div>

    </div>
</div>



<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
<script src="../mychat.js"></script>
</body>
</html>