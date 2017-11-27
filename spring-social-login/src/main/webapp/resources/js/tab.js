function tabFunction(evt) {
	var i, tablinks;
    tablinks = document.getElementsByClassName("todos-button");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    evt.currentTarget.className += " active";

}

