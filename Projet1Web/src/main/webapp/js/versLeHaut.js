$(document).ready(function() { 

    // $('a[href="#toggle-search"], .navbar-bootsnipp .bootsnipp-search .input-group-btn > .btn[type="reset"]').on('click', function(event) {
	// 	event.preventDefault();
	// 	$('.navbar-bootsnipp .bootsnipp-search .input-group > input').val('');
	// 	$('.navbar-bootsnipp .bootsnipp-search').toggleClass('open');
	// 	$('a[href="#toggle-search"]').closest('li').toggleClass('active');

	// 	if ($('.navbar-bootsnipp .bootsnipp-search').hasClass('open')) {
	// 		/* I think .focus dosen't like css animations, set timeout to make sure input gets focus */
	// 		setTimeout(function() { 
	// 			$('.navbar-bootsnipp .bootsnipp-search .form-control').focus();
	// 		}, 100);
	// 	}			
	// });

	// $(document).on('keyup', function(event) {
	// 	if (event.which == 27 && $('.navbar-bootsnipp .bootsnipp-search').hasClass('open')) {
	// 		$('a[href="#toggle-search"]').trigger('click');
	// 	}
	// });

	$('body').append('<a href="#top" class="top_link" title="Revenir en haut de page"><img src="../pics/website/hautPage48.png" /></a>');
	$('.top_link').css({
		'position'				:	'fixed',
		'right'					:	'20px',
		'bottom'				:	'50px',
		'display'				:	'none',
		'padding'				:	'5px',
		'background'			:	'#b2b2b2',
		'-moz-border-radius'	:	'50%',
		'-webkit-border-radius'	:	'50%',
		'border-radius'			:	'50%',
		'opacity'				:	'0.6',
		'z-index'				:	'2000'
	});
	$(window).scroll(function(){
		posScroll = $(document).scrollTop();
		if(posScroll >=550) 
			$('.top_link').fadeIn(600);
		else
			$('.top_link').fadeOut(600);
	});
	
});



