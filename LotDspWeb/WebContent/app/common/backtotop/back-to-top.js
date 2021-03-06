
/**
 * prerequisite:
 * css/back-to-top.css
 * img/up-arrow.png
 */
$(document).ready(function(){
	
	$('body').prepend('<a href="#" class="back-to-top" title="トップへ">Back to Top</a>');

	var amountScrolled = 100;

	$(window).scroll(function() {
		if ( $(window).scrollTop() > amountScrolled ) {
			$('a.back-to-top').fadeIn('slow');
		} else {
			$('a.back-to-top').fadeOut('slow');
		}
	});

	$('a.back-to-top').click(function() {
		$('html, body').animate({
			scrollTop: 0
		}, 700);
		return false;
	});
	
});
