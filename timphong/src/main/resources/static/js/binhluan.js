function getThoigian(thoigian) {
    return new Date(thoigian);
}

var formatter = new Intl.DateTimeFormat('en-GB', { // Định dạng theo ngôn ngữ và định dạng mong muốn
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
});

var html = '';
function loadBinhluan() {
    html = '';
    var phongId = document.getElementById('phongId').textContent;
    var url = '/binhluan?id=' + phongId;
    method = 'GET';
    CallAPI(url, method, ctvBinhluan);
}

function getHtmlBinhluan(data) {
    var phongId = document.getElementById('phongId').textContent;
    data.forEach(function(item) {
        html += "                        <div class=\"comment\">\r\n"
        + "                            <div style=\"background-color: #efeeee;\">\r\n"
        + "                                <span class=\"glyphicon glyphicon-calendar\" aria-hidden=\"true\"></span>\r\n"
        + "                                <meta itemprop=\"datePublished\" content=\"01-01-2016\">" + formatter.format(getThoigian(item.thoigian)) + ' by ' + item.taikhoan.username + "\r\n"
        + "                                <p style=\"margin-bottom: 0;\">" + item.noidung + "</p>\r\n"
        + "                            </div>\r\n"
        + "                            <span class=\"reply-trigger\">Trả lời</span>\r\n"
        + "                            <form class=\"reply-form hidden\" action=\"/account/traloi\" method=\"POST\">\r\n"
        + "                              <textarea name=\"noidung\" class=\"comment-input\" placeholder=\"Nhập bình luận của bạn\"></textarea>\r\n"
        + "                              <input type=\"password\" value=\"" + item.binhluanId +"\" name=\"binhluanId\" hidden>\r\n"
        + "                              <input type=\"password\" name=\"phongId\" value=\"" + phongId + "\" hidden>\r\n"
        + "                              <button type=\"submit\">Gửi</button>\r\n"
        + "                            </form>\r\n";
        var tralois = item.tralois;
        tralois.forEach(function(item) {
            getHtmlTraloi(item);
        });
        html += "                        </div>";
    });

}

function getHtmlTraloi(traloi) {
    if(traloi !== null) { 
        html += "                        <div class=\"reply\">\r\n"
        + "                            <div class=\"comment\" style=\"background-color: #efeeee;\">\r\n"
        + "                                <span class=\"glyphicon glyphicon-calendar\" aria-hidden=\"true\"></span>\r\n"
        + "                                <meta itemprop=\"datePublished\" content=\"01-01-2016\">" + formatter.format(getThoigian(traloi.thoigian)) + ' by ' + traloi.taikhoan.username + "\r\n"
        + "                                <p style=\"margin-bottom: 0;\">" + traloi.noidung + "</p>\r\n"
        + "                            </div>\r\n"
        + "                            <span class=\"reply-trigger\">Trả lời</span>\r\n"
        + "                            <form class=\"reply-form hidden\" action=\"/account/traloi\" method=\"POST\">\r\n"
        + "                              <textarea name=\"noidung\" class=\"comment-input\" placeholder=\"Nhập bình luận của bạn\"></textarea>\r\n"
        + "                              <input hidden type=\"text\" name=\"parent_traloiId\" value=\"" + traloi.traloiId + "\">\r\n"
        + "                              <input hidden type=\"text\" name=\"phongId\" value=\"" + document.getElementById('phongId').textContent + "\">\r\n"
        + "                              <button type=\"submit\">Gửi</button>\r\n"
        + "                            </form>\r\n";
        var tralois = traloi.tralois;
        tralois.forEach(function(item) {
            getHtmlTraloi(item);
        });
        html         += "                        </div>";
    }
}

function ctvBinhluan(data) {
    getHtmlBinhluan(data);
    document.getElementById('binhluans').innerHTML = html;
    // Lặp qua các nút "Trả lời" để thêm sự kiện click
    var replyTriggers = document.getElementsByClassName('reply-trigger');
    for (var i = 0; i < replyTriggers.length; i++) {
        var replyForm = replyTriggers[i].nextElementSibling; // Lấy đối tượng form kế tiếp của nút "Trả lời"
        replyForm.addEventListener('submit', function(event) {
            event.preventDefault(); // Ngăn chặn hành vi mặc định của form
          
            // Tạo đối tượng XMLHttpRequest
            var xhr = new XMLHttpRequest();
          
            // Cấu hình yêu cầu
            xhr.open('POST', this.action, true);
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
          
            // Lấy dữ liệu từ form
            var formData = new FormData(this);
            var jsonData = {};
          
            // Chuyển đổi dữ liệu từ FormData sang JSON
            for (var pair of formData.entries()) {
              jsonData[pair[0]] = pair[1];
            }
          
            // Chuyển đổi JSON sang chuỗi JSON
            var jsonString = JSON.stringify(jsonData);
            console.log(jsonString);
            // Xử lý sự kiện load
            xhr.onload = function() {
              if (xhr.status === 200) {
                // Xử lý kết quả trả về từ máy chủ
                location.reload();
              } else {
                console.error('Có lỗi xảy ra khi submit form:', xhr.statusText);
              }
            };
          
            // Gửi yêu cầu
            xhr.send(jsonString);
          });
    };
    for (var i = 0; i < replyTriggers.length; i++) {
    replyTriggers[i].addEventListener('click', function() {
        var replyForm = this.nextElementSibling; // Lấy đối tượng form kế tiếp của nút "Trả lời"
        replyForm.classList.toggle('hidden'); // Thêm hoặc xóa class 'hidden' để hiển thị hoặc ẩn form
    });
    }
}

function CallAPI(url, method, ctView){
    // Tạo một XMLHttpRequest object
var xhr = new XMLHttpRequest();

// Gọi API endpoint
xhr.open(method, url);

// Xử lý response từ API endpoint
xhr.onload = function() {
if (xhr.status === 200) {
    // Nếu response thành công
    var data = JSON.parse(xhr.responseText);
    ctView(data);
} else {
    // Nếu có lỗi khi gọi API
    console.log('Error: ' + xhr.statusText);
}
};

// Xử lý lỗi khi gọi API
xhr.onerror = function() {
console.log('Error: ' + xhr.statusText);
};

// Gửi request đến API endpoint
xhr.send();
}