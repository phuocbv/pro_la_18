function formLevelJapan() {
	var fieldToggles = document.querySelectorAll(".fieldToggle");
	var length = fieldToggles.length;
	for (var i = 0; i < length; i++) {
		fieldToggles[i].style.display = fieldToggles[i].style.display == 'table-row' ? 'none'
				: 'table-row';
	}
}
