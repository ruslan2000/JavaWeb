function loadDoc() {
	
	var xhttp = new XMLHttpRequest();;

	if (window.XMLHttpRequest) { //new browser
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("demo").innerHTML = this.getResponseHeader("Last-Modified");//this.responseText;
			}
		};

	} else { //Old browser
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("demo").innerHTML = this.getResponseHeader("Last-Modified");//this.responseText;
			}
		};

	}
	xhttp.open("GET", "C:/Temp/test.txt", true);
	xhttp.send();
}