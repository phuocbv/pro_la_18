var linkLevelJapannes = document.getElementById('linkLevelJapannes');
var fieldToggles = document.getElementsByClassName("fieldToggle");
linkLevelJapannes.addEventListener('click', formLevelJapan);

function formLevelJapan() {
	var length = fieldToggles.length;
	for (var i = 0; i < length; i++) {
		fieldToggles[i].style.display = fieldToggles[i].style.display == 'table-row' ? 'none'
				: 'table-row';
	}

	fieldToggles[0].value = 0;
}
