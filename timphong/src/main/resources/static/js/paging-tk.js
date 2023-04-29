function pagingUser(page) {
    var url = '/admin/paging-taikhoan?page=' + page + "&role=USER";
    method = 'GET';
    callAPI(url, method, ctvUserTable);
    var link = document.getElementsByClassName('paging-user');
    var i = 0;
    while(!link[i].classList.contains('active')) {
        i++;
    };
    link[i].classList.remove('active');
    document.getElementById('u' + page).classList.add('active');
}

function pagingSeller(page) {
    var url = '/admin/paging-taikhoan?page=' + page + "&role=SELLER";
    method = 'GET';
    callAPI(url, method, ctvSellerTable);
    var link = document.getElementsByClassName('paging-seller');
    var i = 0;
    while(!link[i].classList.contains('active')) {
        i++;
    };
    link[i].classList.remove('active');
    document.getElementById('s' + page).classList.add('active');
}

function pagingSearchSeller(page, keyword) {
    var url = '/admin/search-taikhoan?page=' + page + "&role=SELLER" + "&keyword=" + keyword;
    method = 'GET';
    callAPI(url, method, ctvSellerTable);
    var link = document.getElementsByClassName('paging-seller');
    var i = 0;
    while(!link[i].classList.contains('active')) {
        i++;
    };
    link[i].classList.remove('active');
    document.getElementById('s' + page).classList.add('active');
}

function pagingSearchUser(page, keyword) {
    var url = '/admin/search-taikhoan?page=' + page + "&role=USER" + "&keyword=" + keyword;
    method = 'GET';
    callAPI(url, method, ctvUserTable);
    var link = document.getElementsByClassName('paging-user');
    var i = 0;
    while(!link[i].classList.contains('active')) {
        i++;
    };
    link[i].classList.remove('active');
    document.getElementById('u' + page).classList.add('active');
}


function ctvUserTable(data) {
    html = getHtmlTaikhoan(data);
    // Gán HTML vào một phần tử trên trang web
    document.getElementById('table-user').innerHTML = html;
}

function ctvSellerTable(data) {
    html = getHtmlTaikhoan(data);
    // Gán HTML vào một phần tử trên trang web
    document.getElementById('table-seller').innerHTML = html;
}


function getHtmlTaikhoan(data) {
    var i = 0;
    var html = '';
    data.forEach(function(item) {
       html += '<tr>'
       html += '<td scope="row">' + String(++i) + '</td>';
       html += '<td class="col-1 text-center">' + item.username + '</td>';
       html += '<td class="col-2 text-center">' + item.hoten + '</td>';
       html += '<td class="col-2 text-center">' + item.sdt + '</td>';
       html += '<td class="col-4 text-center">' + item.email + '</td>';
       html += '<td>'
        //edit icon
       html += '<a class="btn btn-success mr-4" href="/admin/chinhsua-taikhoan?id=' + item.taikhoanId + '">';
       html +=       '<button class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Edit">';
       html +=           '<i class="bi bi-pencil-square"></i>';
       html +=       '</button>';
       html += '</a>';
        //delete icon
       html += '<a class="btn btn-danger" href="/admin/xoa-taikhoan?id=' + item.taikhoanId +'">';
       html +=       '<button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete">';
       html +=           '<i class="bi bi-trash"></i>';
       html +=       '</button>';
       html += '</a>';
       html += '</td';
       html += '</tr>';
    });
    return html;
}