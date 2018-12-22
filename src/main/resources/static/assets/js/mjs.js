/**
 * 
 */
var id1 = setInterval(function() {
	var date = new Date();
	document.getElementById("lt").innerHTML = date.toLocaleString();
}, 1000);
