
function countTime(){
    // Đặt giá trị thời gian kết thúc là 0
    var countDownDate = new Date().getTime() + 60 * 1000;

    // Cập nhật đồng hồ mỗi giây
    var x = setInterval(function() {

    // Lấy thời gian hiện tại
    var now = new Date().getTime();
    
    // Tính toán khoảng cách giữa thời gian hiện tại và thời gian kết thúc
    var distance = countDownDate - now;
    
    // Tính toán các giá trị giờ, phút và giây
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
    // Hiển thị giá trị trên đồng hồ
    document.getElementById("countdown").innerHTML = seconds + "s";
    
    // Kiểm tra nếu thời gian kết thúc, dừng đồng hồ
    if (distance < 0) {
        clearInterval(x);
        document.getElementById("countdown").innerHTML = "0s";
    }
    }, 1000);
}


function sendCode(btn) {
    btn.textContent = "Send...";
    var us = document.getElementById('username').value;
    var fmSendcode = document.getElementById('send-code');
    var fmChangePw = document.getElementById('change-pw');
    $.ajax({
        url : "/forgot-pw", //send to Controller
        type : "post", //send it through get method
        data : {
            username : us
        },
        success : function(data) {
            fmSendcode.classList.toggle('hidden');
            fmChangePw.classList.toggle('hidden');
            btn.textContent = "Xác nhận";
            countTime();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Xử lý lỗi
        }
    });
}

function changePw() {
    var code = document.getElementById('code').value;
    var password = document.getElementById('password').value;
    $.ajax({
        url : "/change-pw", //send to Controller
        type : "post", //send it through get method
        data : {
            code : code,
            password : password
        },
        success : function(data) {
            alert("Thay đổi mật khẩu thành công");
            window.location.href="/login";
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra");
            window.location.href="/forgot-pw";
        }
    });
}

function dangKySeller() {
    var role = document.getElementById("role");
    role.value = "SELLER";
    var form = document.getElementById("form-role");
    form.submit();
}

function previewFile(input) {
    var btnLuuAnh = document.getElementById("btn-luu-anh");
    var file = $("input[type=file]").get(0).files[0];
    if (file) {
        var reader = new FileReader();

        reader.onload = function() {
            $("#previewImg").attr("src", reader.result);
            btnLuuAnh.removeAttribute("hidden");
        }
        reader.readAsDataURL(file);
    }
}

function previewImage(input, idImg) {
    idImg = '#' + idImg;
    if (input.files && input.files[0]) {
      var reader = new FileReader();
  
      reader.onload = function(e) {
        $(idImg).attr('src', e.target.result);
      }
  
      reader.readAsDataURL(input.files[0]);
    }
  }

function themPhong() {
    var them = document.getElementById('them-phong');
    if (them.style.display == "block") {
        them.style.display = "none";
    } else {
        them.style.display = "block";
    }
};

function booking() {
    var phongId = document.getElementById('phongId').textContent;
    var ngay = document.getElementById('ngay').value;
    var gioSelect = document.getElementById('gio');
    var gio = gioSelect[gioSelect.selectedIndex].value;
    if(gio.indexOf(":") < 0) {
        if(gio < 10) {
            gio = gio.padStart(2, "0");
        }
        gio = gio + ":00:00";
    }
    else {
        gio = gio + ":00";
    }
    $.ajax({
        url : "/dathen", //send to Controller
        type : "post", //send it through get method
        data : {
            phongId : phongId,
            ngay: ngay,
            gio: gio
        },
        success : function(data) {
            alert("Đặt lịch thành công");
            window.location.href="/account/list-lichhen";
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Xử lý lỗi
            var error = document.getElementById('error');
            error.textContent = "Có lỗi xảy ra khi đặt lịch";
        }
    });
};

function editLichhen(lichhenId) {
    var ngay = document.getElementById('ngay').value;
    var gioSelect = document.getElementById('gio');
    var gio = gioSelect[gioSelect.selectedIndex].value;
    if(gio.indexOf(":") < 0) {
        if(gio < 10) {
            gio = gio.padStart(2, "0");
        }
        gio = gio + ":00:00";
    }
    else {
        gio = gio + ":00";
    }
    $.ajax({
        url : "/chinhsua-lichhen", //send to Controller
        type : "post", //send it through get method
        data : {
            ngay: ngay,
            gio: gio,
            lichhenId: lichhenId
        },
        success : function(data) {
            alert("Thay đổi lịch thành công");
            window.location.href="/account/list-lichhen";
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Xử lý lỗi
            var error = document.getElementById('error');
            error.textContent = "Có lỗi xảy ra khi thay đổi lịch";
        }
    });
};

function quantam() {
    var phongId = document.getElementById('phongId').textContent;
    var btnAddWishList = document.getElementById('btn-add-wishlist');
    $.ajax({
        url : "/quantam", //send to Controller
        type : "post", //send it through get method
        data : {
            phongId : phongId
        },
        success : function(data) {
            btnAddWishList.innerHTML = data;
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Xử lý lỗi
            console.log("Lỗi: " + textStatus + " - " + errorThrown);
        }
    });
    
};

function active() {
    var pathname = window.location.pathname;
    var thongke = document.getElementById('thongke');
    var trangchu = document.getElementById('trangchu');
    var timkiem = document.getElementById('timkiem');
    var dangnhap = document.getElementById('dangnhap');
    var trangcanhan = document.getElementById('avatar');
    var adminQlphong = document.getElementById('ql-phong');
    var adminQllh = document.getElementById('ql-loaiphong');
    var adminQltk = document.getElementById('ql-tk');

    if (pathname == '/timkiem') {
        timkiem.style.backgroundColor="#5a6763";
    }
    else if(pathname == '/login') {
        dangnhap.style.backgroundColor="#5a6763";;
    }
    else if(pathname == '/' || pathname == '/trangchu') {
        trangchu.style.backgroundColor="#5a6763";;
    }
    else if(pathname =='/profile' || pathname == '/account/list-quantam' || pathname == '/account/list-lichhen' || pathname == '/seller/phong' || pathname == '/seller/quanly-lichhen'){
        trangcanhan.classList.add('active');
    }
    else if(pathname == '/admin/phong') {
        adminQlphong.classList.add('active-admin');
    }
    else if (pathname == '/admin/loaiphong' || pathname == '/admin/chinhsua-loaiphong') {
        adminQllh.classList.add('active-admin');
    }
    else if (pathname == '/admin/taikhoan' || pathname == '/admin/chinhsua-taikhoan' || pathname == '/admin/them-taikhoan') {
        adminQltk.classList.add('active-admin');
    }
    else if (pathname == '/admin/thongke') {
        thongke.classList.add('active-admin');
    }
}
active();