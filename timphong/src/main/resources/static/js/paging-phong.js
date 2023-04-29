//Hàm gọi 
function callFromXemtiep(role) {
    let page = document.getElementById('page');
    let index = page.value;
    pagingPhong(index, '', role);
    page.value = ++index;
}

function callFromLoc(role) {
    let page = document.getElementById('page');
    if(role == 'web')
        document.getElementById('load').innerHTML = "<div class='spinner-border' role='status'><span class='visually-hidden'>Loading...</span></div>";
    else
        document.getElementById('table-phong').innerHTML = "<div class='spinner-border' role='status'><span class='visually-hidden'>Loading...</span></div>";
    page.value = 1;
    pagingPhong(1, 'loc', role);
    page.value = 2;
}

//Hàm xử lý url
function pagingPhong(page, chucnang, role) {
    var url = '';
    if(role == 'index') {
        url = url + '/web/filter-phong?page='+ page;
    }
    else {
        let keyword = document.getElementById('keyword').value;
        let loaiphong = document.getElementById('loaiphong').value;
        let tinh = getTextSelected("city");
        let huyen = getTextSelected("district");
        let xa = getTextSelected("ward");
        let orderby = document.getElementById('orderby').value;
        if(role == 'seller')
        {
            url = url + '/seller/filter-phong?page='; 
        }
        if(role == 'admin') {
            url = url + '/admin/filter-phong?page=';
        }
        if(role == 'web') {
            url = url + '/web/filter-phong?page=';
        }
        url = url
        + page
        + '&keyword=' + keyword
        + '&loaiphongId=' + loaiphong
        + '&tinh=' + tinh
        + '&huyen=' + huyen
        + '&xa=' + xa
        + '&orderby=' + orderby;
    }
    let trangthai = getRadioTrangthai();
    method = 'GET';
    if(chucnang == 'loc') {
        if(role == 'admin')
        {
            url += '&trangthai=' + trangthai;
            callAPI(url, method, ctvLocPhongTable);
        }
        else if (role == 'seller') {
            callAPI(url, method, ctvFilterSellerPhongTable);
        }
        else {
            callAPI(url, method, ctvFilterUserPhongTable)
        }
    }
    else {
        if(role == 'admin')
        {
            url += '&trangthai=' + trangthai;
            callAPI(url, method, ctvPagingPhongTable);
        }
        else if(role == "seller")
            callAPI(url, method, ctvPagingSellerPhongTable);
        else
        {
            callAPI(url, method, ctvPagingUserPhongTable);
        }
    }
}

//Xử lý đưa html ra giao diện
function ctvPagingPhongTable(data) {
    html = getHtmlPhongTable(data);
    document.getElementById('table-phong').insertAdjacentHTML('beforeend', html);
}

function ctvLocPhongTable(data) {
    html = getHtmlPhongTable(data);
    document.getElementById('table-phong').innerHTML = html;
}

function ctvFilterSellerPhongTable(data) {
    html = getHtmlSellerPhongTable(data);
    document.getElementById('table-phong').innerHTML = html;
}

function ctvPagingSellerPhongTable(data) {
    html = getHtmlSellerPhongTable(data);
    document.getElementById('table-phong').insertAdjacentHTML('beforeend', html);
}

function ctvPagingUserPhongTable(data) {
    html = getHtmlUserPhongTable(data);
    document.getElementById('load').insertAdjacentHTML('beforeend', html);
}

function ctvFilterUserPhongTable(data) {
    html = getHtmlUserPhongTable(data);
    document.getElementById('load').innerHTML = html;
}

//Chuyển data sang html
function getHtmlSellerPhongTable(data) {
    html = '';
    data.forEach(function(item) {
        html += "										<tr class=\"phong\">\r\n"
        + "											<td>\r\n"
        + "												<a href=\"/seller/chinhsua-phong?id=" + item.phongId + "\">\r\n"
        + "													<img height=\"120\" width=\"160\" src=\"" + item.anhchinh + "\" />\r\n"
        + "												</a>\r\n"
        + "											</td>\r\n"
        + "											<td>" + item.ten + "</td>\r\n"
        + "											<td>" + item.gia + "</td>\r\n"
        + "											<td>" + item.chieudai + '*' + item.chieurong + "</td>\r\n"
        + "											<td>" + item.diachi + ',' + item.diachict + "</td>\r\n"
        + "											<td>\r\n"
        + "												<a href=\"/seller/chinhsua-phong?id=" + item.phongId + "\">\r\n"
        + "													<button class=\"btn btn-success btn-sm rounded-0\" type=\"button\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Edit\">\r\n"
        + "															<i class=\"fa fa-edit\"></i>\r\n"
        + "													</button>\r\n"
        + "												</a>\r\n"
        + "											</td>\r\n"
        + "											<td>\r\n"
        + "												<a href=\"/seller/xoa-phong?id=" + item.phongId + "\">\r\n"
        + "													<button class=\"btn btn-danger btn-sm rounded-0\" type=\"button\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Delete\">\r\n"
        + "															<i class=\"fa fa-trash\"></i>\r\n"
        + "													</button>\r\n"
        + "												</a>\r\n"
        + "											</td>\r\n"
        + "										</tr>"
    });
    return html;
}

function getHtmlPhongTable(data) {
    let i = document.getElementsByClassName('phong').length;
    let html = '';
    data.forEach(function(item) {
        html += "<tr class=\"phong\">"
        + "<td class=\"col-1\">" + ++i + "</td>"
        + "												<td class=\"col-4\">\r\n"
        + "													<img style = \"height:120px; width:160px\" class=\"img-thumbnail\" src=\"" + item.anhchinh +"\" /></td>\r\n"
        + "												<td class=\"col-2\">" + item.ten + "</td>\r\n"
        + "												<td class=\"col-1\">" + item.gia + "</td>\r\n"
        + "												<td class=\"col-2\">" + item.diachi + "</td>\r\n"
        + "												<td class=\"col-2 p-5\">\r\n";
        if (item.taikhoan == null) {
            html += "<a class=\"main-name-object\" href=\"\">" + "Chưa có" + "</a></td>\r\n";
        }
        else {
            html += "<a class=\"main-name-object\" href=\"\">" + item.taikhoan.username + "</a></td>\r\n";

        }
        html += "<td class=\"col-1\">\r\n"
        + "													<a href=\"\">\r\n"
        + "														<button class=\"btn btn-danger btn-sm rounded-0\" type=\"button\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Report\">\r\n"
        + "															<i class=\"bi bi-exclamation-triangle\"></i>\r\n"
        + "														</button>\r\n"
        + "													</a>\r\n"
        + "												</td>\r\n"
        + "											</tr>"
    });
    return html;
}

function getHtmlUserPhongTable(data) {
    var html = '';
    data.forEach(function(item) {
        html += "						<div class=\"phong col-md-6 col-lg-4 mb-1 mb-lg-4 \">\r\n"
        + "							<div class=\"ftco-media-1\">\r\n"
        + "								<div class=\"ftco-media-1-inner\">\r\n"
        + "									<a href=\"/detail-phong/" + item.phongId + "\" class=\"d-inline-block mb-4\"> \r\n"
        + "										<img src=\"" + item.anhchinh + "\" alt=\"Image\" class=\"img-fluid\">\r\n"
        + "										<div class=\"ftco-media-details text-dark\">\r\n"
        + "											<h3 class=\"mt-3 mb-1\" style=\"font-weight: 400!important\">" + item.ten + "</h3>\r\n"
        + "											<strong>Giá: " + item.gia + " VNĐ</strong>\r\n"
        + "											<p class=\"pr-3\">Đ/c: " + item.diachi + ", " + item.diachict + "</p>\r\n"
        + "										</div>\r\n"
        + "									</a>\r\n"
        + "								</div>\r\n"
        + "							</div>\r\n"
        + "						</div>"
    });
    return html;
}

//Lấy giá trị của các thẻ

function getTextSelected(key) {
    let diachis = document.getElementById(key);
    if(diachis.value == "") {
        return "";
    }
    return diachis.options[diachis.selectedIndex].text;
}

function getRadioTrangthai() {
    var rdoTrangthai = document.getElementsByName('trangthai');
    for (const tt of rdoTrangthai) {
        if (tt.checked) {
            return tt.value;
        }
      }
}