
jQuery(document).ready(function ($) {
	$('#mySubmit').click(function() {
		$(".container").load("favoritos.xhtml");
	    return false;
	});

	$('#myTxt').on('keyup', function(e) {
	    if (e.keyCode === 13) {
	        $('#mySubmit').click();
	    }
	});

});