
var stompClient = null;
var room = $('#room').text() ;
var name = $('#name').text();


function connect() {

    var socket = new SockJS('/sock');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);

}

function onConnected() {

  enterRoom();
  var waiting = document.querySelector('.waiting');
  waiting.classList.add('d-none');

}

function onError(error) {
    var waiting = document.querySelector('.waiting');
  waiting.textContent = 'HONK! service unavailable';
}

function enterRoom() {

  Cookies.set('roomId', room);
  var roomIdDisplay = document.querySelector('#room-id-display');
  roomIdDisplay.textContent = room;
  var topic = `/chat-app/chat/${room}`;
  stompClient.subscribe(`/chat-room/${room}`, onMessageReceived);
  stompClient.send(`${topic}/addUser`,
    {},
    JSON.stringify({sender: name, type: 'JOIN'})
  );
}

function sendMessage(event) {
    var messageContent = $("#message").val().trim();
    var topic = `/chat-app/chat/${room}`;
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: name,
            content: messageContent,
            type: 'CHAT'
        };

        stompClient.send(`${topic}/sendMessage`, {}, JSON.stringify(chatMessage));
        document.querySelector('#message').value = '';
    }

    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('span');
    var divCard = document.createElement('div');
  //  divCard.className = 'card';

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        message.content = message.sender + ': ' + message.content;
    }

    var divCardBody = document.createElement('div');
  //  divCardBody.className = 'card-body';

    divCardBody.appendChild(messageElement);
    divCard.appendChild(divCardBody);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    var messageArea = document.querySelector('#messageArea');
    messageArea.appendChild(divCard);

    var objDiv = document.getElementById("chatWindow");
    objDiv.scrollTop = objDiv.scrollHeight;
}

$(document).ready(function() {
    connect();
    messagebox.addEventListener('submit', sendMessage, true);
});