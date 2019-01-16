
function displayable() {
	console.log('entrée displayable');
	var closeMessage = document.getElementById("displayCheck");
	if (closeMessage !== null) {
		console.log('lancement de displayed(event)');
		var closeScreen = document.getElementById("closeScreen");
		closeScreen.style.display = "flex";
	}
}

function closed(event) {
	console.log('lancement de closed(event)');
	var target = event.target || event.currentTarget;
	var closeScreen = document.getElementById("closeScreen");
	closeScreen.style.display = "none";
	window.location = "http://localhost:8080/proxibanquev4/index.html";
}