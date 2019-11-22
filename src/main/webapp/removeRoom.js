
var stompClient = null;


function connect() {
    var socket = new SockJS('/sock');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);


}

function onConnected() {
    assignButtons();
}

function onError(error) {
}

function assignButtons(){
    $("button[name='removeButton']").each(function() {
        stompClient.subscribe(`/chat-room/` + this.id);
        var topic = `/chat-app/chat/` + this.id ;
        this.addEventListener('click', function (){
            stompClient.send(`${topic}/sendMessage`,
                {},
                JSON.stringify({sender: 'system', content: 'all', type: 'BAN'})
            );
        });
    });

}
$(document).ready(function() {
    connect();
});

