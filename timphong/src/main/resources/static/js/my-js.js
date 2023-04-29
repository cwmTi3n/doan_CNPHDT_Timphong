/**
 * 
 */
//dsDathen.jsp
	//Hiện đánh giá
 	function danhgia(id_p) {
		document.getElementById('dg_id_p').value = id_p;
		var dg = document.getElementById('dg');
		if(dg.style.display == "none"){
			dg.style.display = "block";
		}
		else{
			dg.style.display = "none";
		}
	};
	//Active
	function active(){
		var link1 = document.querySelector("a[href='/timphong/listdathen?hanhdong=1']");
		var char1 = link1.getAttribute("href").charAt(link1.getAttribute("href").length-1);
		var link2 = document.querySelector("a[href='/timphong/listdathen?hanhdong=2']");
		var char2 = link2.getAttribute("href").charAt(link2.getAttribute("href").length-1);
		var link3 = document.querySelector("a[href='/timphong/listdathen?hanhdong=3']");
		var char3 = link3.getAttribute("href").charAt(link3.getAttribute("href").length-1);
		
		var currentUrl = window.location.href;
		var temp = currentUrl.charAt(currentUrl.length-1);
		
		if(char1.localeCompare(temp) == 0){
			link1.classList.add("text-white", "bg-dark");
			link1.classList.remove("text-dark", "a-hanhdong");
		} else if(char2.localeCompare(temp) == 0){
			link2.classList.add("text-white", "bg-dark");
			link2.classList.remove("text-dark", "a-hanhdong");
		} else if(char3.localeCompare(temp) == 0){
			link3.classList.add("text-white", "bg-dark");
			link3.classList.remove("text-dark", "a-hanhdong");
		} else {
			link1.classList.add("text-white", "bg-dark");
			link1.classList.remove("text-dark", "a-hanhdong");
		}
	}
//quanlyphong.jsp
	//Reder images
	function previewFile1(input) {
		var file = $("#hinhanh1").get(0).files[0];
		if (file) {
			var reader = new FileReader();

			reader.onload = function() {
				$("#previewImg1").attr("src", reader.result);
			}
			reader.readAsDataURL(file);
		}
	}
	function previewFile2(input) {
		var file = $("#hinhanh2").get(0).files[0];
		if (file) {
			var reader = new FileReader();

			reader.onload = function() {
				$("#previewImg2").attr("src", reader.result);
			}
			reader.readAsDataURL(file);
		}
	}
	function previewFile3(input) {
		var file = $("#hinhanh3").get(0).files[0];
		if (file) {
			var reader = new FileReader();

			reader.onload = function() {
				$("#previewImg3").attr("src", reader.result);
			}
			reader.readAsDataURL(file);
		}
	}
	//Reder xã, huyện cho bộ lọc
		function loadLocHuyen() {
		var id_t = $('#blcity').find(":selected").val();
		var labelXa = "<option value='0' selected>Chọn phường xã</option>"
		var citis = document.getElementById("blcity");
		var districts = document.getElementById("bldistrict");
		var wards = document.getElementById("blward");
		console.log(id_t);
		if (id_t != 0) {
			districts.disabled = false;
			//wards.disabled = true;
			wards.innerHTML = labelXa;
			$.ajax({
				url : "/timphong/listhuyen", //send to Controller
				type : "get", //send it through get method
				data : {
					exits : id_t
				},
				success : function(data) {
					/* 					removeData(districts);
					 removeData(ward);
					 $("#district").append(data); */

					districts.innerHTML = data;
				}
			});
		} else {
			districts.disabled = true;
			wards.disabled = true;

			districts.value = 0;
			wards.value = 0;
		}

	};

	function loadLocXa() {
		var id_h = $('#bldistrict').find(":selected").val();

		var citis = document.getElementById("blcity");
		var districts = document.getElementById("bldistrict");
		var wards = document.getElementById("blward");

		if (id_h != 0) {
			wards.disabled = false;
			$.ajax({
				url : "/timphong/listxa", //send to Controller
				type : "get", //send it through get method
				data : {
					exits : id_h
				},
				success : function(data) {
					/* 					removeData(wards);
					 $("#ward").append(data); */
					wards.innerHTML = data;
				}
			});
		} else {
			wards.disabled = true;
			wards.value = 0;
		}
	};
	//Reder thêm phòng
		function themPhong() {
		var them = document.getElementById('them-phong');
		if (them.style.display == "block") {
			them.style.display = "none";
		} else {
			them.style.display = "block";
		}

	};
	
	//Xem thêm
		function loadMore() {
		/* tạo viên amount để Gọi và đếm classname là product */
		var amount = document.getElementsByClassName("phong").length;
		var resultSearch = document.getElementById('load')
		var keyword = document.getElementById('keyword').value;
		var lp = document.getElementById('loaiphong').value;
		var city = document.getElementById('city').value;
		var district = document.getElementById('district').value;
		var ward = document.getElementById('ward').value;
		var thutu = document.getElementById('thutu').value;
		/* 		var loading = document.getElementById('loading');
		 loading.style.display="block"; */
		$.ajax({
			url : "/timphong/xemthem", //send to Controller
			type : "post", //send it through get method
			data : {
				exits : amount,
				key : keyword,
				loaiphong : lp,
				tinh : city,
				huyen : district,
				xa : ward,
				thutu : thutu
			},
			success : function(data) {
				$("#load").append(data);
			}
		});
		/* 		loading.style.display="none"; */
	};
	
	