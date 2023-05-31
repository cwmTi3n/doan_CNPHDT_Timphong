// Function to scroll to the bottom of the message frame
function scrollToBottom() {
    var messageFrame = document.querySelector('.message-frame');
    messageFrame.scrollTop = messageFrame.scrollHeight;
    }
// Call the function after the page loads
window.onload = scrollToBottom;
var stompClient = null;
var notificationCount = 0;
var nguoigui;
var nguoinhan;
$(document).ready(function() {
    console.log("Index page is ready");
    countTinnhanChuaxem();
    connect();

    $("#send-private").click(function() {
        sendPrivateMessage();
    });

    // Xử lý sự kiện khi người dùng nhấn phím Enter
    $("#private-message").keydown(function(event) {
        if (event.key === "Enter") {
          event.preventDefault(); // Ngăn chặn hành vi mặc định của phím Enter
          // Gửi tin nhắn
          sendPrivateMessage();
        }
      });
    replaceLink();
});

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        updateNotificationDisplay();
        nguoigui = frame.headers['user-name'];
        nguoinhan = $("#nguoinhan").text();
        stompClient.subscribe('/room/private', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function showMessage(message) {
    let pathname = window.location.pathname;
    if(pathname.includes('/room-chat') && message.nguoigui == nguoinhan) {
        let noidung = toLink(message.noidung);
        let html = "                      <div class=\"message receiver\">\r\n"
        + "                        <div class=\"message-content\">\r\n"
        + "                          <p>" + noidung + "</p>\r\n"
        + "                        </div>\r\n"
        + "                        <div class=\"message-time\">\r\n"
        + "                          <p>" + message.thoigian + "</p>\r\n"
        + "                        </div>\r\n"
        + "                      </div>";
        document.getElementById('content-chat').insertAdjacentHTML('beforeend', html);
        scrollToBottom();
        notificationToBackend(message.id);
    }
    else {
        notificationCount = notificationCount + 1;
    }
    updateNotificationDisplay();
}

function sendPrivateMessage() {
    let noidung = $("#private-message").val();
    let message = {
        nguoigui: nguoigui,
        nguoinhan: nguoinhan,
        noidung: noidung
    };
    let time = getTimeNow();
    let msg = toLink(noidung);
    let html = "                      <div class=\"message sender\">\r\n"
        + "                        <div class=\"message-content\">\r\n"
        + "                          <p>" + msg + "</p>\r\n"
        + "                        </div>\r\n"
        + "                        <div class=\"message-time\">\r\n"
        + "                          <span>" + time + "</span>\r\n"
        + "                        </div>\r\n"
        + "                      </div>";
    document.getElementById('content-chat').insertAdjacentHTML('beforeend', html);
    document.getElementById("private-message").value = "";
    scrollToBottom();
    stompClient.send("/app/private-message", {}, JSON.stringify(message));
}

function updateNotificationDisplay() {
    if (notificationCount == 0) {
        $('#notification-icon').hide();
    } else {
        $('#notification-icon').show();
        $('#notification-count').text(notificationCount);
    }
}

function resetNotificationCount() {
    
    if(pathname == '/account/list-chat') {
        notificationCount = 0;
        updateNotificationDisplay();
    }
}

function getTimeNow() {
    // Lấy thời gian hiện tại
    var currentDate = new Date();

    // Lấy thông tin về ngày, tháng và năm
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1; // Lưu ý rằng tháng trong JavaScript bắt đầu từ 0
    var year = currentDate.getFullYear();

    // Định dạng lại chuỗi ngày, tháng, năm
    if (day < 10) {
    day = '0' + day; // Thêm '0' phía trước nếu ngày nhỏ hơn 10
    }
    if (month < 10) {
    month = '0' + month; // Thêm '0' phía trước nếu tháng nhỏ hơn 10
    }

    // Hiển thị kết quả theo định dạng yyyy-mm-dd
    return year + '-' + month + '-' + day;
}

function notificationToBackend(id) {
    $.ajax({
        url: "/notification-chat",
        method: "post",
        data: {
            id: id
        },
        success: function (data) {
        },
        error: function (xhr, status, error) {
            console.log("error");
        },
      });
}

function countTinnhanChuaxem() {
    $.ajax({
        url: "/count-tn-chuaxem",
        method: "get",
        data: {

        },
        success: function (data) {
            notificationCount = parseInt(data);
        },
        error: function (xhr, status, error) {
            console.log("error");
        },
      });
}

function replaceLink() {
    // Lặp qua các phần tử <p> có class "msg"
    const msgElements = document.getElementsByClassName("msg");
    for (let i = 0; i < msgElements.length; i++) {
        const messageElement = msgElements[i];
        const messageText = messageElement.textContent;
        messageElement.innerHTML = toLink(messageText);
    }
}

function toLink(msg) {
    const linkRegex = /(https?:\/\/[^\s]+)/g;
    return replacedText = msg.replace(linkRegex, '<a href="$&">$&</a>');
}