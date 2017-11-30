function tabFunction(evt) {
	var i, tablinks;
    tablinks = document.getElementsByClassName("tab-button");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", " border");
    }
    evt.currentTarget.className =  evt.currentTarget.className.replace(" border", " active");

}

