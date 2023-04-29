function callAPI(url, method, ctView){
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