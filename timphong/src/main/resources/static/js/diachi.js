function diachi(c, d, w) {
  let citis = document.getElementById(c);
  let districts = document.getElementById(d);
  let wards = document.getElementById(w);
  var Parameter = {
    url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json", 
    method: "GET", 
    responseType: "application/json", 
  };
  var promise = axios(Parameter);
  promise.then(function (result) {
    renderCity(result.data);
  });

  function renderCity(data) {
    for (const x of data) {
      citis.options[citis.options.length] = new Option(x.Name, x.Id);
    }
    citis.onchange = function () {  	
      districts.length = 1;
      wards.length = 1;
      if(this.value != ""){
        const result = data.filter(n => n.Id === this.value);

        for (const k of result[0].Districts) {
          districts.options[districts.options.length] = new Option(k.Name, k.Id);
        }
      }
      if(c == "tinh") {
        var dc_tinh = document.getElementById(c);
        var inputDiachi = document.getElementById("diachi");
        inputDiachi.value = dc_tinh.options[dc_tinh.selectedIndex].text;
      }
    };
    districts.onchange = function () {

      wards.length = 1;
      const dataCity = data.filter((n) => n.Id === citis.value);
      if (this.value != "") {
        const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;

        for (const w of dataWards) {
          wards.options[wards.options.length] = new Option(w.Name, w.Id);
        }
      }
      if(d == "huyen") {
        var dc_tinh = document.getElementById(c);
        var dc_huyen = document.getElementById(d);
        var inputDiachi = document.getElementById("diachi");
        inputDiachi.value = dc_tinh.options[dc_tinh.selectedIndex].text + ", " + dc_huyen.options[dc_huyen.selectedIndex].text;
      }
    };
    wards.onchange = function() {
      if(w == "xa") {
        var dc_tinh = document.getElementById(c);
        var dc_huyen = document.getElementById(d);
        var dc_xa = document.getElementById(w);
        var inputDiachi = document.getElementById("diachi");
        inputDiachi.value = dc_tinh.options[dc_tinh.selectedIndex].text + ", " 
          + dc_huyen.options[dc_huyen.selectedIndex].text 
          + ", " 
          + dc_xa.options[dc_xa.selectedIndex].text;
      }
    };
  }
}


diachi("city", "district", "ward")
diachi("tinh", "huyen", "xa")

// function setDiachi(key) {
//   var dc = document.getElementById(key);
//   var inputDiachi = document.getElementById("diachi");
//   if(key != 'xa') {
//     inputDiachi.value = inputDiachi.value + dc.value + ", ";
//   }
//   else {
//     inputDiachi.value = inputDiachi.value + dc.value;
//   } 
// }